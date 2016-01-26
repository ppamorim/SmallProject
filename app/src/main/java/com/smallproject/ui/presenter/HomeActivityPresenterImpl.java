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
package com.smallproject.ui.presenter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import com.smallproject.domain.interactor.GetTemp;
import com.smallproject.domain.model.Temp;
import javax.inject.Inject;

public class HomeActivityPresenterImpl implements HomeActivityPresenter {

  private Temp temp;
  private View view;
  private GetTemp getTemp;
  private LocationManager locationManager;

  @Inject public HomeActivityPresenterImpl(GetTemp getTemp) {
    this.getTemp = getTemp;
  }

  @Override public void setView(View view) {
    if (view == null) {
      throw new IllegalArgumentException("The view must not be null!");
    }
    this.view = view;
  }

  @Override public void resume() {
    configLocationManager();
  }

  @Override public void initialize() {

  }

  @Override public Bundle saveInstance(Bundle instance) {
    if (temp != null) {
      instance.putParcelable(Temp.TAG, temp);
    }
    return instance;
  }

  @Override public void restoreInstance(Bundle instance) {
    if (instance.containsKey(Temp.TAG)) {
      temp = instance.getParcelable(Temp.TAG);
      instance.remove(Temp.TAG);
    }
  }

  @Override public void destroy() {
    this.view = null;
    this.temp = null;
  }

  private LocationListener mLocationListener = new LocationListener() {
    @Override public void onLocationChanged(final Location location) {
      System.out.println("LOCATION FOUND! " + location);
      getTemp();
    }

    @Override public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override public void onProviderEnabled(String provider) {

    }

    @Override public void onProviderDisabled(String provider) {

    }
  };

  private void configLocationManager() {

    Context context = view.getContext();

    this.locationManager =
        (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
      if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
          != PackageManager.PERMISSION_GRANTED
          && context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
          != PackageManager.PERMISSION_GRANTED) {
        // TODO: Consider calling
        //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for Activity#requestPermissions for more details.
        return;
      }
      if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
          != PackageManager.PERMISSION_GRANTED
          && context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
          != PackageManager.PERMISSION_GRANTED) {
        // TODO: Consider calling
        //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for Activity#requestPermissions for more details.
        return;
      }
    }
    System.out.println("locationManager ");
    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 100,
        mLocationListener);
  }

  private void getTemp() {
    getTemp.execute(new GetTemp.Callback() {
      @Override public void onSuccess() {
        notifySuccess();
      }

      @Override public void onError() {
        notifyError();
      }
    });
  }

  private void notifySuccess() {
    if(view != null && view.isReady()) {
      view.onSuccess(temp);
    }
  }

  private void notifyError() {
    if(view != null && view.isReady()) {
      view.onError();
    }
  }

}
