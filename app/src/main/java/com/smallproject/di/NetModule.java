package com.smallproject.di;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;

@Module public class NetModule {

  @Provides @Singleton OkHttpClient provideOkHttpClient() {
    return new OkHttpClient();
  }

}
