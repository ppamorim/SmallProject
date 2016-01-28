package com.smallproject.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.smallproject.R;
import com.smallproject.ui.util.Tags;

public class ChatActivity extends AbstractActivity {

  private ImageButton returnButton;

  @Override protected int getLayoutId() {
    return R.layout.activity_chat;
  }

  @Override protected void bindViews() {
    returnButton = (ImageButton) findViewById(R.id.return_button);
  }

  @Override protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    configButtons();
  }

  private void configButtons() {
    returnButton.setOnClickListener(onClickListener);
    returnButton.setTag(Tags.RETURN_BUTTON);
  }

  private View.OnClickListener onClickListener = new View.OnClickListener() {
    @Override public void onClick(View view) {
      switch((Integer) view.getTag()) {
        case Tags.RETURN_BUTTON:
          onBackPressed();
          break;
      }
    }
  };

}
