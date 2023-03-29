package com.nnhiep.travelmanager.fragments;

import static android.app.Activity.RESULT_OK;
import android.annotation.SuppressLint;
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
import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.views.ManageNoteActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * Trang chủ - Thuận
 */
public class HomeFragment extends Fragment {
    LinearLayout search, callManager, notification;
    LinearLayout map,note, message, report, setting, shareImage, weather;
    ImageView ivUser, imgSettings;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_home, container, false);
        search = v.findViewById(R.id.search);
        callManager = v.findViewById(R.id.callManager);
        notification = v.findViewById(R.id.notification);
        map = v.findViewById(R.id.map);
        note = v.findViewById(R.id.noteTable);
        message = v.findViewById(R.id.message);
        report = v.findViewById(R.id.report);
        setting = v.findViewById(R.id.setting);
        shareImage = v.findViewById(R.id.shareImage);
        weather = v.findViewById(R.id.weather);
        ivUser = v.findViewById(R.id.ivUser);
        imgSettings = v.findViewById(R.id.imgSettings);

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
        note.setOnClickListener(v1 -> {
            Intent intent = new Intent(getContext(), ManageNoteActivity.class);
            startActivity(intent);
        });
        imgSettings.setOnClickListener(v1 -> {
            replaceFragment(new SettingsFragment());
        });

        return v;
    }

    /**
     * Thay đổi giữa các màn (fragment)
     * @author nnhiep 17.03.2023
     */
    private void replaceFragment(Fragment fragment) {
        // Khởi tạo đối tượng quản lý fragment - dùng cho việc chuyển fragment
        FragmentManager fragmentManager = getParentFragmentManager();
        // Khởi tạo đối tượng dùng cho việc thay thế fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // Thực hiện thay thế fragment
        fragmentTransaction.replace(R.id.layoutFrame, fragment);
        // Xác nhận thay đổi và áp dụng vào activity
        fragmentTransaction.commit();
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