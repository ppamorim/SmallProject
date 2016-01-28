package com.smallproject.domain.interactor;

import com.smallproject.executor.Interactor;
import com.smallproject.executor.InteractorExecutor;
import com.smallproject.executor.MainThread;
import javax.inject.Inject;
import okhttp3.OkHttpClient;

public class PostLikeImpl implements Interactor, PostLike {

  private final InteractorExecutor interactorExecutor;
  private final MainThread mainThread;
  private Callback callback;
  private OkHttpClient okHttpClient;

  @Inject public PostLikeImpl(InteractorExecutor interactorExecutor, MainThread mainThread,
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

  }

  private void notifySuccess(final boolean liked) {
    mainThread.post(new Runnable() {
      @Override public void run() {
        callback.onSuccess(liked);
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
