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