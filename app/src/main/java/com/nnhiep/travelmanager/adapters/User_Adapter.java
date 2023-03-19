package com.nnhiep.travelmanager.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.models.User_Contact;

import java.util.ArrayList;

public class User_Adapter extends BaseAdapter {
    //nguon du lieu cho adapter
    private ArrayList<User_Contact> data;
    //ngu canh cua ung dung
    private Activity context;
    //doi tuong su dung layout
    private LayoutInflater inflater;
    public User_Adapter(ArrayList<User_Contact> data, Activity activity) {
        this.data = data;
        this.context = activity;
        this.inflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if(v==null)
            v= inflater.inflate(R.layout.user_listview,null);
        ImageView imgprofile = v.findViewById(R.id.imglv);
        TextView tvname = v.findViewById(R.id.txtName);
        tvname.setText(data.get(i).getName());
        TextView tvphone = v.findViewById(R.id.txtPhone);
        tvphone.setText(data.get(i).getPhone());
        TextView tvaddress = v.findViewById(R.id.txtAddress);
        tvaddress.setText(data.get(i).getAddress());
        TextView tvGmail = v.findViewById(R.id.txtGmail);
        tvGmail.setText(data.get(i).getGmail());
        return null;
    }
}
