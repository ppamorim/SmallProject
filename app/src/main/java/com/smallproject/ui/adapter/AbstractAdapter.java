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
package com.smallproject.ui.adapter;

import android.content.Context;
import android.widget.BaseAdapter;
import java.util.List;

public abstract class AbstractAdapter<T> extends BaseAdapter {

  public final Context context;
  public List<T> data;

  public AbstractAdapter(Context context) {
    this.context = context;
  }

  @Override
  public int getCount() {
    return data != null ? data.size() : -1;
  }

  @Override
  public T getItem(int i) {
    return data.get(i);
  }

  public void setData(List<T> data) {
    this.data = data;
  }

  @Override
  public long getItemId(int position) {
    return getItem(position).hashCode();
  }

}