package com.nnhiep.travelmanager.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nnhiep.travelmanager.R;

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
        return inflater.inflate(R.layout.fragment_user, container, false);
    }
}