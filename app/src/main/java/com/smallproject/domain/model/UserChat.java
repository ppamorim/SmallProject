package com.smallproject.domain.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class UserChat implements Parcelable {

  @JsonField private String content;
  @JsonField(name = "user") private User user;

  public static final Parcelable.Creator<UserChat> CREATOR
      = new Parcelable.Creator<UserChat>() {
    public UserChat createFromParcel(Parcel in) {
      return new UserChat(in);
    }
    public UserChat[] newArray(int size) {
      return new UserChat[size];
    }
  };

  public UserChat() {
    super();
  }

  public UserChat(Parcel in) {
    content = in.readString();
    user = in.readParcelable(User.class.getClassLoader());
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(content);
    dest.writeParcelable(user, flags);
  }

}
