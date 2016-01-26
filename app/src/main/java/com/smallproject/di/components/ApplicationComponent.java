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
package com.smallproject.di.components;

import android.app.Application;
import com.smallproject.SmallProjectApplication;
import com.smallproject.di.ApplicationModule;
import com.smallproject.di.NetModule;
import com.smallproject.executor.InteractorExecutor;
import com.smallproject.executor.MainThread;
import dagger.Component;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;

/**
 * ApplicationComponent is the top level component for this architecture. It provides generic
 * dependencies ic_like {@link InteractorExecutor} or {@link MainThread} and exposes them to
 * subcomponents and to external dependant classes.
 *
 * Scope {@link Singleton} is used to limit dependency instances across whole execution.
 *
 * @author Pedro Paulo de Amorim
 */
@Singleton @Component(modules = { ApplicationModule.class, NetModule.class })
public interface ApplicationComponent {
  void inject(SmallProjectApplication smallProjectApplication);
  Application application();
  InteractorExecutor executor();
  MainThread mainThread();
  OkHttpClient provideOkHttpClient();
}
