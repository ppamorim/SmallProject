package com.smallproject.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.smallproject.R;
import com.smallproject.SmallProjectApplication;
import com.smallproject.di.ActivityModule;
import com.smallproject.di.HomeActivityModule;
import com.smallproject.di.components.DaggerHomeActivityComponent;
import com.smallproject.di.components.HomeActivityComponent;
import com.smallproject.domain.model.Temp;
import com.smallproject.ui.presenter.HomeActivityPresenter;
import com.smallproject.ui.util.Images;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;

public class HomeActivity extends Activity implements HomeActivityPresenter.View {

  private HomeActivityComponent homeActivityComponent;

  private ImageView backgroundImage;
  private TextView actualTemp;

  @Inject HomeActivityPresenter homeActivityPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    homeActivityComponent().inject(this);
    setContentView(R.layout.activity_home);
    homeActivityPresenter.setView(this);
    backgroundImage = (ImageView) findViewById(R.id.background_image);
    actualTemp = (TextView) findViewById(R.id.actual_temp);
  }

  @Override protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    Picasso.with(this).load(Images.SNOW_DAY_SD).into(backgroundImage);
  }

  @Override protected void onResume() {
    super.onResume();
    homeActivityPresenter.resume();
  }

  @Override public boolean isReady() {
    return !isFinishing();
  }

  @Override public void onSuccess(Temp temp) {

  }

  @Override public void onError() {

  }

  @Override public Context getContext() {
    return this;
  }

  private HomeActivityComponent homeActivityComponent() {
    if(homeActivityComponent == null) {
      homeActivityComponent = DaggerHomeActivityComponent.builder()
          .applicationComponent(((SmallProjectApplication) getApplication()).component())
          .activityModule(new ActivityModule(this))
          .homeActivityModule(new HomeActivityModule())
          .build();
    }
    return homeActivityComponent;
  }

}
