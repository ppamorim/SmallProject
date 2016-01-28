package com.smallproject.domain.interactor;

import com.smallproject.domain.model.Post;
import com.smallproject.domain.model.UserChat;
import java.util.ArrayList;

public interface GetChats {
  void execute(Callback callback);
  interface Callback {
    void onSuccess(ArrayList<UserChat> userChats);
    void onError();
  }
}
