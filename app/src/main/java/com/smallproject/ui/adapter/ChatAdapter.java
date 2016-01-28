package com.smallproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.smallproject.R;
import com.smallproject.domain.model.User;
import com.smallproject.domain.model.UserChat;
import com.smallproject.ui.util.CircleTransform;
import com.squareup.picasso.Picasso;

public class ChatAdapter extends AbstractAdapter<UserChat> {

  public ChatAdapter(Context context) {
    super(context);
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {

    UserChat post = getItem(position);
    ViewHolder holder;

    if (convertView != null) {
      holder = (ViewHolder) convertView.getTag();
    } else {
      convertView = LayoutInflater.from(context).inflate(R.layout.adapter_post, parent, false);
      holder = new ViewHolder(convertView);
      convertView.setTag(holder);
    }

    holder.content.setText(post.getContent());
    User user = post.getUser();
    if (user != null) {
      holder.userName.setText(user.getName());
      String userImage = user.getProfilePicture();
      if(userImage != null) {
        Picasso.with(context).load(userImage).fit()
            .centerCrop()
            .transform(new CircleTransform())
            .into(holder.userImage);
      }
    }
    return convertView;
  }

  static class ViewHolder {

    private ImageView userImage;
    private TextView userName;
    private TextView content;

    public ViewHolder(View view) {
      userImage = (ImageView) view.findViewById(R.id.user_image);
      userName = (TextView) view.findViewById(R.id.user_name);
      content = (TextView) view.findViewById(R.id.content);
    }

  }

}
