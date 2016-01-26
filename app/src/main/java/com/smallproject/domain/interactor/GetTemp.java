package com.smallproject.domain.interactor;

import android.location.Location;

public interface GetTemp {
  void execute(Callback callback);
  void setLocation(Location location);
  interface Callback {
    void onSuccess();
    void onError();
  }
}
