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
package com.smallproject.domain.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class Post implements Parcelable {

  @JsonField private int id;
  @JsonField private String content;
  @JsonField private String image;
  @JsonField(name = "user") private User user;

  public static final Parcelable.Creator<Post> CREATOR
      = new Parcelable.Creator<Post>() {
    public Post createFromParcel(Parcel in) {
      return new Post(in);
    }
    public Post[] newArray(int size) {
      return new Post[size];
    }
  };

  public Post() {
    super();
  }

  public Post(Parcel in) {
    id = in.readInt();
    content = in.readString();
    image = in.readString();
    user = in.readParcelable(User.class.getClassLoader());
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
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
    dest.writeInt(id);
    dest.writeString(content);
    dest.writeString(image);
    dest.writeParcelable(user, flags);
  }

}
