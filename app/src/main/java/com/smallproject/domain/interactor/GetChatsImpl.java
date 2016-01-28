package com.smallproject.domain.interactor;

import com.bluelinelabs.logansquare.LoganSquare;
import com.smallproject.domain.model.UserChat;
import com.smallproject.domain.service.ChatsService;
import com.smallproject.domain.service.PostService;
import com.smallproject.executor.Interactor;
import com.smallproject.executor.InteractorExecutor;
import com.smallproject.executor.MainThread;
import java.util.ArrayList;
import javax.inject.Inject;
import okhttp3.OkHttpClient;

public class GetChatsImpl implements Interactor, GetChats {

  private final InteractorExecutor interactorExecutor;
  private final MainThread mainThread;
  private Callback callback;
  private OkHttpClient okHttpClient;

  @Inject public GetChatsImpl(InteractorExecutor interactorExecutor, MainThread mainThread,
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
      ChatsService chatsService = new ChatsService(okHttpClient);
      ArrayList<UserChat> userChats = (ArrayList<UserChat>) LoganSquare.parseList(
          chatsService.getChats(), UserChat.class);
      if(userChats != null) {
        notifySuccess(userChats);
      } else {
        notifyError();
      }
    } catch (Exception e) {
      e.printStackTrace();
      notifyError();
    }
  }

  private void notifySuccess(final ArrayList<UserChat> userChats) {
    mainThread.post(new Runnable() {
      @Override public void run() {
        callback.onSuccess(userChats);
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
