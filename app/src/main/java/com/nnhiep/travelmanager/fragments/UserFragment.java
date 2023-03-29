package com.nnhiep.travelmanager.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.database.Database;
import com.nnhiep.travelmanager.views.LoginActivity;
import com.nnhiep.travelmanager.views.RegisterTour;
import com.nnhiep.travelmanager.views.RulesSet;
import com.nnhiep.travelmanager.views.User_TTin;

/**
 * Trang quản lý thông tin cá nhân - Quốc
 */
public class UserFragment extends Fragment {
    private TextView tvTTin,txtuser;
    private TextView tvTourLove;
    private TextView tvTBao;
    private TextView tvDKhoan;
    private TextView imguserlove;
    private static Database db;
    private Button btnLogout;
    private ConstraintLayout privacy, lovedTour;

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
        lovedTour = v.findViewById(R.id.lovedTour);
        privacy = v.findViewById(R.id.privacy);

        tvTTin.setOnClickListener(view -> {
            Intent intent= new Intent(getContext(), User_TTin.class);
            startActivityForResult(intent,100);
        });

        // Quay về trang tour yêu thích
        lovedTour.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), RegisterTour.class);
            startActivity(intent);
        });

        // Quay về trang điều khoản
        privacy.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), RulesSet.class);
            startActivity(intent);
        });

        btnLogout.setOnClickListener(v1 -> {
            // Hiển thị hộp thoại xác nhận
            new AlertDialog.Builder(getActivity())
                    .setTitle(getContext().getResources().getString(R.string.logout))
                    .setMessage(getContext().getResources().getString(R.string.sure_to_exit))
                    .setPositiveButton(getContext().getResources().getString(R.string.logout), (dialog, which) -> {
                        // Thoát ứng dụng về màn hình đăng nhập
                        Database db = new Database(this.getContext());
                        db.updateDataSystem("1", false);
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        getActivity().finish();
                    })
                    .setNegativeButton(getContext().getResources().getString(R.string.cancel), null)
                    .show();
        });
        return v;
    }
}