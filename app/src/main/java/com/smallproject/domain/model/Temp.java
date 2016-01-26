package com.smallproject.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Temp implements Parcelable {

  public static final String TAG = "temp";

  public static final Parcelable.Creator<Temp> CREATOR
      = new Parcelable.Creator<Temp>() {
    public Temp createFromParcel(Parcel in) {
      return new Temp(in);
    }
    public Temp[] newArray(int size) {
      return new Temp[size];
    }
  };

  public Temp() {
    super();
  }

  public Temp(Parcel in) {

  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {

  }

}
