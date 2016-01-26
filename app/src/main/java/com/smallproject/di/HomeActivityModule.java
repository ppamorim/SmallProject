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
