package com.smallproject.ui.presenter;

import android.os.Bundle;
/**
 * Generic use of the presenter
 */
public interface Presenter {
  void initialize();
  Bundle saveInstance(Bundle instance);
  void restoreInstance(Bundle instance);
  void destroy();
}