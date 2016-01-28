package com.smallproject.di.components;

import android.app.Activity;
import com.smallproject.di.ActivityModule;
import com.smallproject.di.ChatActivityModule;
import com.smallproject.di.scopes.ActivityScope;
import com.smallproject.domain.interactor.GetChats;
import com.smallproject.ui.activity.ChatActivity;
import com.smallproject.ui.presenter.ChatActivityPresenter;
import dagger.Component;

@ActivityScope @Component(dependencies = ApplicationComponent.class,
    modules = { ActivityModule.class, ChatActivityModule.class })
public interface ChatActivityComponent extends AbstractActivityComponent {
  void inject(ChatActivity chatActivity);
  Activity activityContext();
  ChatActivityPresenter chatActivityPresenter();
  GetChats getChats();
}
