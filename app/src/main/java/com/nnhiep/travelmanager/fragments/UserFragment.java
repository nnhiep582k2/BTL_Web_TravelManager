package com.nnhiep.travelmanager.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nnhiep.travelmanager.R;

import com.nnhiep.travelmanager.database.Database;
import com.nnhiep.travelmanager.views.User_TTin;
import com.nnhiep.travelmanager.views.User_TTin_Edit;

import java.util.ArrayList;

/**
 * Trang quản lý thông tin cá nhân - Quốc
 */
public class UserFragment extends Fragment {
    public UserFragment() {}

    private TextView tvTTin,txtuser;
    private TextView tvTourLove;
    private TextView tvTBao;
    private TextView tvDKhoan;
    private static Database db;
    @Override
    public void onCreate( Bundle savedInstanceState) {
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
        txtuser = v.findViewById(R.id.txtuser);

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