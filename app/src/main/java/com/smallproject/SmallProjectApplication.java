package com.smallproject;

import android.app.Application;
import com.smallproject.di.ApplicationModule;
import com.smallproject.di.components.ApplicationComponent;
import com.smallproject.di.components.DaggerApplicationComponent;

public class SmallProjectApplication extends Application {

  private ApplicationComponent applicationComponent;

  public SmallProjectApplication() {
    super();
  }

  @Override public void onCreate() {
    super.onCreate();
    initializeDependencyInjector().inject(this);
  }

  private ApplicationComponent initializeDependencyInjector() {
    if (applicationComponent == null) {
      applicationComponent = DaggerApplicationComponent.builder()
          .applicationModule(new ApplicationModule(this))
          .build();
    }
    return applicationComponent;
  }

  public ApplicationComponent component() {
    return applicationComponent;
  }

}
