package com.nnhiep.travelmanager.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.databinding.ActivityMainBinding;
import com.nnhiep.travelmanager.fragments.HomeFragment;
import com.nnhiep.travelmanager.fragments.ScheduleFragment;
import com.nnhiep.travelmanager.fragments.SettingsFragment;
import com.nnhiep.travelmanager.fragments.TourFragment;
import com.nnhiep.travelmanager.fragments.UserFragment;

public class MainActivity extends AppCompatActivity {
    // Khởi tạo đối tượng ánh xạ các thành phần UI - nnhiep 17.03.2023
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Gán giá trị cho đối tượng view binding - nnhiep 17.03.2023
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        // Thiết lập layout cho activity - nnhiep 17.03.2023
        setContentView(binding.getRoot());

        // Khởi tạo giá trị mặc định lúc mới vào là Trang chủ - nnhiep 17.03.2023
        replaceFragment(new HomeFragment());

        // Lắng nghe sự kiện chọn phần tử trong navigation bottom - nnhiep 17.03.2023
        binding.navigationBottom.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menuHome:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.menuTour:
                    replaceFragment(new TourFragment());
                    break;
                case R.id.menuSchedule:
                    replaceFragment(new ScheduleFragment());
                    break;
                case R.id.menuUser:
                    replaceFragment(new UserFragment());
                    break;
                case R.id.menuSettings:
                    replaceFragment(new SettingsFragment());
                    break;
            }
            return true;
        });
    }

    /**
     * Thay đổi giữa các màn (fragment)
     * @author nnhiep 17.03.2023
     */
    private void replaceFragment(Fragment fragment) {
        // Khởi tạo đối tượng quản lý fragment - dùng cho việc chuyển fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Khởi tạo đối tượng dùng cho việc thay thế fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // Thực hiện thay thế fragment
        fragmentTransaction.replace(R.id.layoutFrame, fragment);
        // Xác nhận thay đổi và áp dụng vào activity
        fragmentTransaction.commit();
    }
}