package com.smallproject.domain.interactor;

import android.location.Location;
import com.bluelinelabs.logansquare.LoganSquare;
import com.smallproject.domain.model.Temp;
import com.smallproject.domain.service.TempService;
import com.smallproject.executor.Interactor;
import com.smallproject.executor.InteractorExecutor;
import com.smallproject.executor.MainThread;
import javax.inject.Inject;
import okhttp3.OkHttpClient;

public class GetTempImpl implements Interactor, GetTemp {

  private final InteractorExecutor interactorExecutor;
  private final MainThread mainThread;
  private Callback callback;
  private OkHttpClient okHttpClient;
  private Location location;

  @Inject public GetTempImpl(InteractorExecutor interactorExecutor,
      MainThread mainThread, OkHttpClient okHttpClient) {
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

  @Override public void setLocation(Location location) {
   this.location = location;
  }

  @Override public void run() {
    try {
      TempService tempService = new TempService(okHttpClient);
      Temp temp = LoganSquare.parse(tempService.getActualTemperature(location), Temp.class);
      if(temp != null) {
        notifySuccess();
      }
    } catch (Exception e) {
      e.printStackTrace();
      notifyError();
    }
    this.location = null;
  }

  private void notifySuccess() {
    mainThread.post(new Runnable() {
      @Override public void run() {
        callback.onSuccess();
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
