package com.smallproject.di.components;

import android.app.Activity;
import com.smallproject.di.ActivityModule;
import com.smallproject.di.HomeActivityModule;
import com.smallproject.di.scopes.ActivityScope;
import com.smallproject.ui.activity.HomeActivity;
import com.smallproject.ui.presenter.HomeActivityPresenter;
import dagger.Component;

@ActivityScope @Component(dependencies = ApplicationComponent.class,
    modules = { ActivityModule.class, HomeActivityModule.class })
public interface HomeActivityComponent extends AbstractActivityComponent {
  void inject(HomeActivity homeActivity);
  Activity activityContext();
  HomeActivityPresenter homeActivityPresenter();
}
