package com.smallproject.di;

import android.app.Application;
import android.view.LayoutInflater;
import com.smallproject.executor.InteractorExecutor;
import com.smallproject.executor.MainThread;
import com.smallproject.executor.MainThreadImpl;
import com.smallproject.executor.ThreadExecutor;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module public class ApplicationModule {

  private final Application application;

  public ApplicationModule(Application application) {
    this.application = application;
  }

  @Provides @Singleton Application provideApplicationContext() {
    return this.application;
  }

  @Provides @Singleton InteractorExecutor provideThreadExecutor(ThreadExecutor executor) {
    return executor;
  }

  @Provides @Singleton MainThread providePostExecutionThread(MainThreadImpl mainThread) {
    return mainThread;
  }

}
