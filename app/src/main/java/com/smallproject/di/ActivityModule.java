package com.smallproject.di;

import android.app.Activity;
import com.smallproject.di.scopes.ActivityScope;
import dagger.Module;
import dagger.Provides;

/**
 * Dependency injection module used to provide activity scope context and satisfy activity/fragment
 * dependency needs
 *
 * @author Pedro Paulo de Amorim
 */
@Module public class ActivityModule {

  private final Activity activityContext;

  public ActivityModule(Activity activityContext) {
    this.activityContext = activityContext;
  }

  @Provides @ActivityScope Activity getActivityContext() {
    return activityContext;
  }

}
