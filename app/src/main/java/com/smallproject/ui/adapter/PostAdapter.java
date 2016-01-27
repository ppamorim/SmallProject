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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.smallproject.R;
import com.smallproject.domain.model.Post;
import com.smallproject.domain.model.User;
import com.smallproject.ui.util.CircleTransform;
import com.smallproject.ui.util.Tags;
import com.squareup.picasso.Picasso;

public class PostAdapter extends AbstractAdapter<Post> {

  public PostAdapter(Context context) {
    super(context);
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {

    Post post = getItem(position);
    String image = post.getImage();
    boolean imageIsvalid = image != null;

    ViewHolder holder;

    if(imageIsvalid) {
      if (convertView != null) {
        holder = (ViewHolderImage) convertView.getTag();
      } else {
        convertView = LayoutInflater.from(context).inflate(R.layout.adapter_post_image, parent, false);
        holder = new ViewHolderImage(convertView);
        convertView.setTag(holder);
      }
      Picasso.with(context).load(image).fit().centerCrop().into(((ViewHolderImage) holder).image);
    } else {
      if (convertView != null) {
        holder = (ViewHolder) convertView.getTag();
      } else {
        convertView = LayoutInflater.from(context).inflate(R.layout.adapter_post, parent, false);
        holder = new ViewHolder(convertView);
        convertView.setTag(holder);
      }
    }

    holder.content.setText(post.getContent());
    User user = post.getUser();
    if (user != null) {
      holder.userName.setText(user.getName());
      String userImage = user.getProfilePicture();
      if(userImage != null) {
        Picasso.with(context).load(userImage).fit()
            .centerCrop()
            .transform(new CircleTransform()).into(holder.userImage);
      }
    }
    return convertView;
  }

  static class ViewHolderImage extends ViewHolder {
    private ImageView image;
    public ViewHolderImage(View view) {
      super(view);
      image = (ImageView) view.findViewById(R.id.post_image);
      image.setTag(Tags.POST_CONTENT_TAG);
    }
  }

  static class ViewHolder {

    private ImageView userImage;
    private TextView userName;
    private TextView content;

    public ViewHolder(View view) {
      userImage = (ImageView) view.findViewById(R.id.user_image);
      userName = (TextView) view.findViewById(R.id.user_name);
      content = (TextView) view.findViewById(R.id.content);

      userImage.setTag(Tags.POST_USER_TAG);
      userName.setTag(Tags.POST_USER_TAG);
      content.setTag(Tags.POST_CONTENT_TAG);
    }

  }

}
