package com.nnhiep.travelmanager.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.adapters.SlideAdapter;
import com.nnhiep.travelmanager.models.SlideItem;
import com.nnhiep.travelmanager.views.SearchTourActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * Trang quản lý Tour - Phương
 */
public class TourFragment extends Fragment {
    private ListView listView;
    ViewPager2 viewpager2;
    Button btnSearch;
    Handler SlideHandler=new Handler();


    public TourFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    //@SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // return inflater.inflate(R.layout.fragment_tour, container, false);
        View v = inflater.inflate(R.layout.fragment_tour, container, false);
        //anh xa
        viewpager2=v.findViewById(R.id.viewpager);
        btnSearch=(Button) v.findViewById(R.id.btnSearch);



        //slider images
        List<SlideItem> sliderItem = new ArrayList<>();
        sliderItem.add(new SlideItem(R.drawable.banner_bien));
        sliderItem.add(new SlideItem(R.drawable.banner_du_lich));
        sliderItem.add(new SlideItem(R.drawable.banner_hanquoc));
        sliderItem.add(new SlideItem(R.drawable.banner_he));
        sliderItem.add(new SlideItem(R.drawable.banner_mua_he));
        sliderItem.add(new SlideItem(R.drawable.banner_30_4));
        viewpager2.setAdapter(new SlideAdapter(sliderItem, viewpager2));
        //chế độ xem cuộn
        viewpager2.setClipToPadding(false);

        viewpager2.setClipChildren(false);
        //Đặt số trang sẽ được giữ lại ở hai bên của (các) trang hiện đang hiển thị.limit=5
        viewpager2.setOffscreenPageLimit(5);

        viewpager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        //Thêm một biến trang vào danh sách.

        //Transformers sẽ được thực hiện theo thứ tự mà chúng đã được thêm vào.
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            //Áp dụng phép biến đổi thuộc tính cho trang đã cho.
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        viewpager2.setPageTransformer(compositePageTransformer);
        viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {

                super.onPageSelected(position);
                //Xóa mọi bài đăng đang chờ xử lý của Runnable r bằng mã thông báo Đối tượng trong hàng đợi tin nhắn.
                SlideHandler.removeCallbacks(slideerRunnable);
                SlideHandler.postDelayed(slideerRunnable, 2000);
            }
        });

        //bắt sự kiện cho nút Search
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent= new Intent(getContext(), SearchTourActivity.class);
                 startActivity(intent);
            }
        });
        return v;
    }

    private Runnable slideerRunnable=new Runnable() {
        @Override
        public void run() {

            viewpager2.setCurrentItem(viewpager2.getCurrentItem()+1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        SlideHandler.removeCallbacks(slideerRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        SlideHandler.postDelayed(slideerRunnable,3000);
    }
}