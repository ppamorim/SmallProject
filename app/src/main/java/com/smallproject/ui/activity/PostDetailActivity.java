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

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.smallproject.R;
import com.smallproject.domain.model.Post;
import com.smallproject.ui.util.Tags;
import com.squareup.picasso.Picasso;

public class PostDetailActivity extends AbstractActivity implements View.OnClickListener {

  private ImageView postImage;
  private ImageButton likeButton;

  @Override protected int getLayoutId() {
    return R.layout.activity_post_detail;
  }

  @Override protected void bindViews() {
    postImage = (ImageView) findViewById(R.id.post_image);
    likeButton = (ImageButton) findViewById(R.id.like_button);
  }

  @Override protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    configButtons();
    Post post = getIntent().getParcelableExtra(Tags.POST);
    Picasso.with(this).load(post.getImage()).fit().centerInside().into(postImage);
  }

  @Override public void onClick(View view) {
    switch ((Integer) view.getTag()) {
      case Tags.LIKE_POST:
        break;
    }
  }

  private void configButtons() {
    likeButton.setOnClickListener(this);
    likeButton.setTag(Tags.LIKE_POST);
  }

}
