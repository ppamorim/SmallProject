/*
* Copyright (C) 2015 Pedro Paulo de Amorim
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.smallproject.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import com.smallproject.R;
import com.smallproject.SmallProjectApplication;
import com.smallproject.di.ActivityModule;
import com.smallproject.di.HomeActivityModule;
import com.smallproject.di.components.DaggerHomeActivityComponent;
import com.smallproject.di.components.HomeActivityComponent;
import com.smallproject.domain.model.Post;
import com.smallproject.ui.adapter.PostAdapter;
import com.smallproject.ui.presenter.HomeActivityPresenter;
import com.smallproject.ui.util.Tags;
import java.util.ArrayList;
import javax.inject.Inject;

public class HomeActivity extends AbstractActivity implements HomeActivityPresenter.View {

  private HomeActivityComponent homeActivityComponent;

  private ListView listView;
  private ImageButton chatButton;
  private ImageButton settingsButton;

  @Inject HomeActivityPresenter homeActivityPresenter;

  @Override protected int getLayoutId() {
    return R.layout.activity_home;
  }

  @Override protected void bindViews() {
    listView = (ListView) findViewById(R.id.list_view);
    chatButton = (ImageButton) findViewById(R.id.chat_button);
    settingsButton = (ImageButton) findViewById(R.id.settings_button);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    homeActivityComponent().inject(this);
    super.onCreate(savedInstanceState);
    homeActivityPresenter.setView(this);
  }

  @Override protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    configButtons();
    configListView();
    homeActivityPresenter.initialize();
  }

  @Override protected void onResume() {
    super.onResume();
    homeActivityPresenter.resume();
  }

  @Override public boolean isReady() {
    return !isFinishing();
  }

  @Override public void onSuccess(ArrayList<Post> posts) {
    PostAdapter adapter = ((PostAdapter) listView.getAdapter());
    adapter.setData(posts);
    adapter.notifyDataSetChanged();
  }

  @Override public void onError() {

  }

  @Override public Context getContext() {
    return this;
  }

  private void configButtons() {
    chatButton.setOnClickListener(onClickListener);
    settingsButton.setOnClickListener(onClickListener);
    chatButton.setTag(Tags.CHAT_BUTTON);
    settingsButton.setTag(Tags.SETTINGS_BUTTON);
  }

  private void configListView() {
    listView.setOnItemClickListener(onItemClickListener);
    listView.setAdapter(new PostAdapter(this));
  }

  private View.OnClickListener onClickListener = new View.OnClickListener() {
    @Override public void onClick(View view) {
      Intent intent;
      switch((Integer) view.getTag()) {
        case Tags.CHAT_BUTTON:
          intent = new Intent(getApplicationContext(), ChatActivity.class);
          break;
        default:
        case Tags.SETTINGS_BUTTON:
          intent = new Intent(getApplicationContext(), SettingsActivity.class);
          break;
      }
      startActivity(intent);
    }
  };

  private ListView.OnItemClickListener onItemClickListener =
      new AdapterView.OnItemClickListener() {
    @Override public void onItemClick(AdapterView<?> parent,
        View view, int position, long id) {
      startPostDetailActivity(position);
    }
  };

  private void startPostDetailActivity(int position) {
    Post post = homeActivityPresenter.getPostAtPosition(position);
    if(post != null) {
      Intent intent = new Intent(getApplicationContext(), PostDetailActivity.class);
      intent.putExtra(Post.TAG, post);
      startActivity(intent);
    }
  }

  private HomeActivityComponent homeActivityComponent() {
    if(homeActivityComponent == null) {
      homeActivityComponent = DaggerHomeActivityComponent.builder()
          .applicationComponent(((SmallProjectApplication) getApplication()).component())
          .activityModule(new ActivityModule(this))
          .homeActivityModule(new HomeActivityModule())
          .build();
    }
    return homeActivityComponent;
  }

}
