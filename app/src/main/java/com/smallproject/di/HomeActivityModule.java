package com.smallproject.di;

import com.smallproject.di.scopes.ActivityScope;
import com.smallproject.domain.interactor.GetTemp;
import com.smallproject.domain.interactor.GetTempImpl;
import com.smallproject.ui.presenter.HomeActivityPresenter;
import com.smallproject.ui.presenter.HomeActivityPresenterImpl;
import dagger.Module;
import dagger.Provides;

@Module public class HomeActivityModule {

  @Provides @ActivityScope HomeActivityPresenter provideHomeActivityPresenter(
      HomeActivityPresenterImpl getPresenter) {
    return getPresenter;
  }

  @Provides @ActivityScope GetTemp provideGetTemp(
      GetTempImpl getTemp) {
    return getTemp;
  }

}
