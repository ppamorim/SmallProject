package com.smallproject.domain.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class User implements Parcelable {

  @JsonField private int id;
  @JsonField private String name;
  @JsonField private String profilePicture;

  public static final Parcelable.Creator<User> CREATOR
      = new Parcelable.Creator<User>() {
    public User createFromParcel(Parcel in) {
      return new User(in);
    }
    public User[] newArray(int size) {
      return new User[size];
    }
  };

  public User() {
    super();
  }

  public User(Parcel in) {
    id = in.readInt();
    name = in.readString();
    profilePicture = in.readString();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getProfilePicture() {
    return profilePicture;
  }

  public void setProfilePicture(String profilePicture) {
    this.profilePicture = profilePicture;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeString(name);
    dest.writeString(profilePicture);
  }

}
