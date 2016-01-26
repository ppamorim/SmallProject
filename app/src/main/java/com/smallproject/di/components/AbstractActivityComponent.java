package com.smallproject.di.components;

import android.app.Activity;
import com.smallproject.di.ActivityModule;
import com.smallproject.di.scopes.ActivityScope;
import dagger.Component;

/**
 * Components linked to the activity context or to the activity lifecycle will be depending or
 * extending this one. Common dependencies linked to activity context or lifecycles would be
 * exposed by this component.
 *
 * Fragment components may depend on this component. Activity-level components should extend this
 * component.
 *
 * @author Pedro Paulo de Amorim
 */
@ActivityScope @Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface AbstractActivityComponent {
  Activity activityContext();
}