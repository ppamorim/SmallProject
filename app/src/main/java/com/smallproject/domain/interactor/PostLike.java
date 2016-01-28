package com.smallproject.domain.interactor;


public interface PostLike {
  void execute(Callback callback);
  interface Callback {
    void onSuccess(boolean liked);
    void onError();
  }
}
