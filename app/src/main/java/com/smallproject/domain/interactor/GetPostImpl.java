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
package com.smallproject.domain.interactor;

import com.bluelinelabs.logansquare.LoganSquare;
import com.smallproject.domain.model.Post;
import com.smallproject.domain.service.PostService;
import com.smallproject.executor.Interactor;
import com.smallproject.executor.InteractorExecutor;
import com.smallproject.executor.MainThread;
import java.util.ArrayList;
import javax.inject.Inject;
import okhttp3.OkHttpClient;

public class GetPostImpl implements Interactor, GetPost {

  private final InteractorExecutor interactorExecutor;
  private final MainThread mainThread;
  private Callback callback;
  private OkHttpClient okHttpClient;

  @Inject public GetPostImpl(InteractorExecutor interactorExecutor, MainThread mainThread,
      OkHttpClient okHttpClient) {
    this.interactorExecutor = interactorExecutor;
    this.mainThread = mainThread;
    this.okHttpClient = okHttpClient;
  }

  @Override public void execute(Callback callback) {
    if (callback == null) {
      throw new IllegalArgumentException("Callback parameter can't be null");
    }
    this.callback = callback;
    this.interactorExecutor.run(this);
  }

  @Override public void run() {
    try {
      PostService postService = new PostService(okHttpClient);
      ArrayList<Post> posts = (ArrayList<Post>) LoganSquare.parseList(
          postService.getRecentPosts(), Post.class);
      if(posts != null) {
        notifySuccess(posts);
      } else {
        notifyError();
      }
    } catch (Exception e) {
      e.printStackTrace();
      notifyError();
    }
  }

  private void notifySuccess(final ArrayList<Post> posts) {
    mainThread.post(new Runnable() {
      @Override public void run() {
        callback.onSuccess(posts);
      }
    });
  }

  private void notifyError() {
    mainThread.post(new Runnable() {
      @Override public void run() {
        callback.onError();
      }
    });
  }

}
