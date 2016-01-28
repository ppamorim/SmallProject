package com.smallproject.di;

import com.smallproject.di.scopes.ActivityScope;
import com.smallproject.domain.interactor.GetChats;
import com.smallproject.domain.interactor.GetChatsImpl;
import com.smallproject.ui.presenter.ChatActivityPresenter;
import com.smallproject.ui.presenter.ChatActivityPresenterImpl;
import dagger.Module;
import dagger.Provides;

@Module public class ChatActivityModule {

  @Provides @ActivityScope ChatActivityPresenter provideChatActivityPresenter(
      ChatActivityPresenterImpl getPresenter) {
    return getPresenter;
  }

  @Provides @ActivityScope GetChats provideGetChats(
      GetChatsImpl getChats) {
    return getChats;
  }

}
