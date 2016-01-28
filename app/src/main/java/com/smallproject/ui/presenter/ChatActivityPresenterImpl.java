package com.smallproject.ui.presenter;

import android.os.Bundle;
import com.smallproject.domain.interactor.GetChats;
import com.smallproject.domain.model.UserChat;
import com.smallproject.ui.util.Tags;
import java.util.ArrayList;
import javax.inject.Inject;

public class ChatActivityPresenterImpl implements ChatActivityPresenter {

  private ArrayList<UserChat> userChats;
  private View view;
  private GetChats getChats;

  @Inject public ChatActivityPresenterImpl(GetChats getChats) {
    this.getChats = getChats;
  }

  @Override public void setView(View view) {
    if (view == null) {
      throw new IllegalArgumentException("The view must not be null!");
    }
    this.view = view;
  }

  @Override public UserChat getUserChatAtPosition(int position) {
    return userChats != null ? userChats.get(position) : null;
  }

  @Override public void initialize() {
    getChats();
  }

  @Override public Bundle saveInstance(Bundle instance) {
    if (userChats != null) {
      instance.putParcelableArrayList(Tags.USER_CHAT, userChats);
    }
    return instance;
  }

  @Override public void restoreInstance(Bundle instance) {
    if (instance.containsKey(Tags.USER_CHAT)) {
      userChats = instance.getParcelableArrayList(Tags.USER_CHAT);
      instance.remove(Tags.USER_CHAT);
    }
  }

  @Override public void destroy() {
    this.userChats = null;
  }

  public void getChats() {
    getChats.execute(new GetChats.Callback() {
      @Override public void onSuccess(ArrayList<UserChat> userChats) {
        notifySuccess(userChats);
      }
      @Override public void onError() {
        notifyError();
      }
    });
  }

  private void notifySuccess(ArrayList<UserChat> userChats) {
    this.userChats = userChats;
    if(view != null && view.isReady()) {
      view.onSuccess(userChats);
    }
  }

  private void notifyError() {
    if(view != null && view.isReady()) {
      view.onError();
    }
  }

}
