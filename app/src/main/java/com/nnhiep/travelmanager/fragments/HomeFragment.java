package com.nnhiep.travelmanager.fragments;

<<<<<<< HEAD
import static android.app.Activity.RESULT_OK;
import android.content.Intent;
import android.net.Uri;
=======
>>>>>>> 443aaf1 (truoc khi nop)
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
=======
>>>>>>> 443aaf1 (truoc khi nop)
import com.nnhiep.travelmanager.R;

/**
 * Trang chủ - Thuận
 */
public class HomeFragment extends Fragment {
    public HomeFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}