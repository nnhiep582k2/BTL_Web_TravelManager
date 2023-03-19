package com.nnhiep.travelmanager.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.views.Add_Tour;
import com.nnhiep.travelmanager.views.User_TTin;

/**
 * Trang quản lý thông tin cá nhân - Quốc
 */
public class UserFragment extends Fragment {
    public UserFragment() {}

    private TextView tvTTin;
    private TextView tvTourLove;
    private TextView tvTBao;
    private TextView tvDKhoan;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_user, container, false);
        //khai bao

        View v = inflater.inflate(R.layout.fragment_user, container, false);
        tvTTin = v.findViewById(R.id.txtuserttin);
        tvTourLove = v.findViewById(R.id.txtuserlove);
        tvTBao = v.findViewById(R.id.txtTbao);
        tvDKhoan = v.findViewById(R.id.txtDKhoan);
        //bat su kien text view
        tvTTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getContext(), User_TTin.class);
                startActivityForResult(intent,100);
            }
        });
        return v;

    }
}