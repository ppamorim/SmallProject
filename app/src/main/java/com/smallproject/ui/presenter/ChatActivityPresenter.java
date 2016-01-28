package com.smallproject.ui.presenter;

import android.content.Context;
import com.smallproject.domain.model.UserChat;
import java.util.ArrayList;

public interface ChatActivityPresenter extends Presenter {
  void setView(View view);
  UserChat getUserChatAtPosition(int position);
  interface View {
    boolean isReady();
    void onSuccess(ArrayList<UserChat> userChats);
    void onError();
  }
}
