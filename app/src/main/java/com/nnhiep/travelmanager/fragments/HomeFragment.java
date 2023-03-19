package com.nnhiep.travelmanager.fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.nnhiep.travelmanager.R;

/**
 * Trang chủ - Thuận
 */
public class HomeFragment extends Fragment {
    LinearLayout search, callManager, notification;
    LinearLayout map,note, message, report, setting, shareImage, weather;
    ImageView ivUser;
    public HomeFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_home, container, false);
        search = v.findViewById(R.id.search);
        callManager = v.findViewById(R.id.callManager);
        notification = v.findViewById(R.id.notification);
        map = v.findViewById(R.id.map);
        note = v.findViewById(R.id.note);
        message = v.findViewById(R.id.message);
        report = v.findViewById(R.id.report);
        setting = v.findViewById(R.id.setting);
        shareImage = v.findViewById(R.id.shareImage);
        weather = v.findViewById(R.id.weather);
        ivUser = v.findViewById(R.id.ivUser);

        registerForContextMenu(ivUser);
        search.setOnClickListener(v1 -> {
            Fragment fragment = new TourFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.layoutFrame,fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
        callManager.setOnClickListener(v1 -> {
            Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel: " + "180000"));
            startActivity(intent);
        });

        return v;
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.ivUser) {
            getActivity().getMenuInflater().inflate(R.menu.image_user_context, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.image_user_menu_item:
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 200);
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            ivUser.setImageURI(uri);
        }
    }
}