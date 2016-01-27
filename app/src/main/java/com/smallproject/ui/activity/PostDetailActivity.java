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
import android.widget.ImageView;
import com.smallproject.R;
import com.smallproject.domain.model.Post;
import com.squareup.picasso.Picasso;

public class PostDetailActivity extends AbstractActivity {

  private ImageView postImage;

  @Override protected int getLayoutId() {
    return R.layout.activity_detail;
  }

  @Override protected void bindViews() {
    postImage = (ImageView) findViewById(R.id.post_image);
  }

  @Override protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    Post post = getIntent().getParcelableExtra(Post.TAG);
    Picasso.with(this).load(post.getImage()).fit().centerInside().into(postImage);
  }
}
