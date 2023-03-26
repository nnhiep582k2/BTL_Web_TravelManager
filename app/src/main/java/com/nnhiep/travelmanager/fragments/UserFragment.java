package com.nnhiep.travelmanager.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.database.Database;
import com.nnhiep.travelmanager.views.LoginActivity;
import com.nnhiep.travelmanager.views.User_TTin;

/**
 * Trang quản lý thông tin cá nhân - Quốc
 */
public class UserFragment extends Fragment {
    private TextView tvTTin,txtuser;
    private TextView tvTourLove;
    private TextView tvTBao;
    private TextView tvDKhoan;
    private static Database db;
    private Button btnLogout;
    public UserFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // khai bao
        View v = inflater.inflate(R.layout.fragment_user, container, false);
        tvTTin = v.findViewById(R.id.txtuserttin);
        tvTourLove = v.findViewById(R.id.txtuserlove);
        tvTBao = v.findViewById(R.id.txtTbao);
        tvDKhoan = v.findViewById(R.id.txtDKhoan);
        txtuser = v.findViewById(R.id.txtuser);
        btnLogout = v.findViewById(R.id.btnLogout);

        tvTTin.setOnClickListener(view -> {
            Intent intent= new Intent(getContext(), User_TTin.class);
            startActivityForResult(intent,100);
        });

        btnLogout.setOnClickListener(v1 -> {
            // Hiển thị hộp thoại xác nhận
            new AlertDialog.Builder(getActivity())
                    .setTitle("Đăng xuất")
                    .setMessage("Bạn có muốn đăng xuất không?")
                    .setPositiveButton("Đăng xuất", (dialog, which) -> {
                        // Thoát ứng dụng về màn hình đăng nhập
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        getActivity().finish();
                    })
                    .setNegativeButton("Không", null)
                    .show();
        });
        return v;
    }
}