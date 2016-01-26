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
