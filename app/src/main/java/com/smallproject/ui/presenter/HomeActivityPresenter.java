package com.smallproject.ui.presenter;

import android.content.Context;
import com.smallproject.domain.model.Temp;

public interface HomeActivityPresenter extends Presenter {
  void setView(View view);
  void resume();
  interface View {
    Context getContext();
    boolean isReady();
    void onSuccess(Temp temp);
    void onError();
  }

}
