package com.smallproject.ui.presenter;

import android.os.Bundle;
import com.smallproject.domain.interactor.GetPost;
import com.smallproject.domain.model.Post;
import java.util.ArrayList;
import javax.inject.Inject;

public class PostDetailActivityPresenterImpl implements PostDetailActivityPresenter {

  private ArrayList<Post> posts;
  private HomeActivityPresenter.View view;
  private GetPost getPost;

  @Inject public PostDetailActivityPresenterImpl(GetPost getPost) {
    this.getPost = getPost;
  }

  @Override public void initialize() {

  }

  @Override public Bundle saveInstance(Bundle instance) {
    return null;
  }

  @Override public void restoreInstance(Bundle instance) {

  }

  @Override public void destroy() {

  }

}
