package com.nnhiep.travelmanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.models.UserListTeam;
import java.util.ArrayList;

public class ListAdapterTeamProSet extends ArrayAdapter<UserListTeam> {
    public ListAdapterTeamProSet(Context context, ArrayList<UserListTeam> userArrayList){
          super(context,R.layout.list_item_teamproduct,userArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
          UserListTeam user = getItem(position);
          if (convertView == null)
          {
              convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_teamproduct,parent,false);
          }
        ImageView imageView = convertView.findViewById(R.id.profile_pic);
        TextView userName =   convertView.findViewById(R.id.persionName);
        TextView lastMsg =   convertView.findViewById(R.id.lastMessage);
        TextView time =   convertView.findViewById(R.id.msgtime);
        imageView.setImageResource(user.immgeeId);
        userName.setText(user.name);
        lastMsg.setText(user.lastMessage);
        time.setText(user.lastMsgTime);

        return convertView;
    }
}
