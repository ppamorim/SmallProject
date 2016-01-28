package com.smallproject.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import com.smallproject.R;
import com.smallproject.SmallProjectApplication;
import com.smallproject.di.ActivityModule;
import com.smallproject.di.ChatActivityModule;
import com.smallproject.di.components.ChatActivityComponent;
import com.smallproject.di.components.DaggerChatActivityComponent;
import com.smallproject.domain.model.UserChat;
import com.smallproject.ui.adapter.ChatAdapter;
import com.smallproject.ui.presenter.ChatActivityPresenter;
import com.smallproject.ui.util.Tags;
import java.util.ArrayList;
import javax.inject.Inject;

public class ChatActivity extends AbstractActivity implements ChatActivityPresenter.View {

  private ChatActivityComponent chatActivityComponent;

  private ImageButton returnButton;
  private ListView listView;
  private TextView warningView;

  @Inject ChatActivityPresenter chatActivityPresenter;

  @Override protected int getLayoutId() {
    return R.layout.activity_chat;
  }

  @Override protected void bindViews() {
    returnButton = (ImageButton) findViewById(R.id.return_button);
    listView = (ListView) findViewById(R.id.list_view);
    warningView = (TextView) findViewById(R.id.warning_view);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    chatActivityComponent().inject(this);
    super.onCreate(savedInstanceState);
    chatActivityPresenter.setView(this);
  }

  @Override protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    configButtons();
    configListView();
    chatActivityPresenter.initialize();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    chatActivityPresenter.destroy();
  }

  @Override public boolean isReady() {
    return !isFinishing();
  }

  @Override public void onSuccess(ArrayList<UserChat> userChats) {
    listView.setVisibility(View.VISIBLE);
    warningView.setVisibility(View.GONE);
    ChatAdapter adapter = ((ChatAdapter) listView.getAdapter());
    adapter.setData(userChats);
    adapter.notifyDataSetChanged();
  }

  @Override public void onError() {
    listView.setVisibility(View.GONE);
    warningView.setVisibility(View.VISIBLE);
  }

  private void configButtons() {
    returnButton.setOnClickListener(onClickListener);
    returnButton.setTag(Tags.RETURN_BUTTON);
    warningView.setOnClickListener(onClickListener);
    warningView.setTag(Tags.TRY_AGAIN);
  }

  private void configListView() {
    listView.setOnItemClickListener(onItemClickListener);
    listView.setAdapter(new ChatAdapter(this));
  }

  private View.OnClickListener onClickListener = new View.OnClickListener() {
    @Override public void onClick(View view) {
      switch((Integer) view.getTag()) {
        case Tags.RETURN_BUTTON:
          onBackPressed();
          break;
        case Tags.TRY_AGAIN:
          chatActivityPresenter.initialize();
          break;
      }
    }
  };

  private ListView.OnItemClickListener onItemClickListener =
      new AdapterView.OnItemClickListener() {
        @Override public void onItemClick(AdapterView<?> parent,
            View view, int position, long id) {
          //startPostDetailActivity(position);
        }
      };

  private ChatActivityComponent chatActivityComponent() {
    if(chatActivityComponent == null) {
      chatActivityComponent = DaggerChatActivityComponent.builder()
          .applicationComponent(((SmallProjectApplication) getApplication()).component())
          .activityModule(new ActivityModule(this))
          .chatActivityModule(new ChatActivityModule())
          .build();
    }
    return chatActivityComponent;
  }

}
