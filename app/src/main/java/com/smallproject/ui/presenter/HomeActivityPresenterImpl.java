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
package com.smallproject.ui.presenter;

import android.os.Bundle;
import com.smallproject.domain.interactor.GetPost;
import com.smallproject.domain.model.Post;
import com.smallproject.ui.util.Tags;
import java.util.ArrayList;
import javax.inject.Inject;

public class HomeActivityPresenterImpl implements HomeActivityPresenter {

  private ArrayList<Post> posts;
  private View view;
  private GetPost getPost;

  @Inject public HomeActivityPresenterImpl(GetPost getPost) {
    this.getPost = getPost;
  }

  @Override public void setView(View view) {
    if (view == null) {
      throw new IllegalArgumentException("The view must not be null!");
    }
    this.view = view;
  }

  @Override public void resume() {

  }

  @Override public void initialize() {
    getPost();
  }

  @Override public Bundle saveInstance(Bundle instance) {
    if (posts != null) {
      instance.putParcelableArrayList(Tags.POST, posts);
    }
    return instance;
  }

  @Override public void restoreInstance(Bundle instance) {
    if (instance.containsKey(Tags.POST)) {
      posts = instance.getParcelableArrayList(Tags.POST);
      instance.remove(Tags.POST);
    }
  }

  @Override public void destroy() {
    this.posts = null;
  }

  @Override public Post getPostAtPosition(int position)  {
    return posts.get(position);
  }

  private void getPost() {
    getPost.execute(new GetPost.Callback() {
      @Override public void onSuccess(ArrayList<Post> posts) {
        notifySuccess(posts);
      }
      @Override public void onError() {
        notifyError();
      }
    });
  }

  private void notifySuccess(ArrayList<Post> posts) {
    this.posts = posts;
    if(view != null && view.isReady()) {
      view.onSuccess(posts);
    }
  }

  private void notifyError() {
    if(view != null && view.isReady()) {
      view.onError();
    }
  }

}
