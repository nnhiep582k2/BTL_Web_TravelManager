package com.nnhiep.travelmanager.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.adapters.ScheduleAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Schedule - Hiệp
 */
public class ScheduleFragment extends Fragment {
    String[] dataSchedule = {"Schedule 1", "Schedule 2", "Schedule 3"};
    int counter = 0;
    RecyclerView rViewSchedule;
    ScheduleAdapter adapter;
    ArrayList<String> dataSource;
    Button btnChangeView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_schedule, container, false);

        getDataSchedule();
        buildRecyclerView(view);

        // Xử lý click thêm - nnhiep 20.03.2023
        view.findViewById(R.id.btnAddSchedule).setOnClickListener(v -> {
            dataSource.add(dataSchedule[counter%3]);
            counter++;
            adapter.notifyItemInserted(dataSource.size() - 1);
        });

        // Xử lý đổi dạng layout - nnhiep 20.03.2023
        btnChangeView.setOnClickListener(v -> {
            // Chuyển về dạng grid view - GridLayout
            if(btnChangeView.getText().equals("Grid")) {
                rViewSchedule.setLayoutManager(new GridLayoutManager(getContext(), 2));
                btnChangeView.setText("List");
                btnChangeView.setCompoundDrawablesWithIntrinsicBounds(this.getResources().getDrawable(R.drawable.ic_baseline_view_list_24), null, null, null);
            } else {
                // Chuyển về dạng list view - LinearLayout
                rViewSchedule.setLayoutManager(new LinearLayoutManager(getContext()));
                btnChangeView.setText("Grid");
                btnChangeView.setCompoundDrawablesWithIntrinsicBounds(this.getResources().getDrawable(R.drawable.ic_baseline_grid_view_24), null, null, null);
            }
        });

        EditText eTxtSearchSchedule = view.findViewById(R.id.eTxtSearchSchedule);
        eTxtSearchSchedule.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().trim().isEmpty()) {
                    adapter.filter(dataSource);
                } else filterSchedule(s.toString());
            }
        });

        return view;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // Xử lý click button xóa - nnhiep 20.03.2023
            case 1:
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("Delete");
                dialog.setMessage("Are you sure to delete schedule " + adapter.getItem(item.getGroupId()) + "?");
                dialog.setPositiveButton("Delete", (dialog1, which) -> {
                    adapter.getItems().remove(item.getGroupId());
                    adapter.notifyItemRemoved(item.getGroupId());
                    dataSource.remove(item.getGroupId());
                });
                dialog.setNegativeButton("Cancel", (dialog2, which) -> {
                });
                dialog.show();
        }
        return super.onContextItemSelected(item);
    }

    /**
     * Hàm lấy dữ liệu tour của user
     * @author nnhiep 30.03.2023
     */
    private void getDataSchedule() {
        dataSource = new ArrayList<>();
        dataSource.add("Tour 1");
        dataSource.add("Tour 2");
        dataSource.add("Tour 3");
        dataSource.add("Tour 4");
        dataSource.add("Tour 5");
    }

    /**
     * Hàm thiết lập recycle view
     * @author nnhiep 30.03.2023
     */
    private void buildRecyclerView(View view) {
        rViewSchedule = view.findViewById(R.id.rViewSchedule);
        btnChangeView = view.findViewById(R.id.btnGridView);
        adapter = new ScheduleAdapter(dataSource);
        rViewSchedule.setLayoutManager(new LinearLayoutManager(getContext()));
        rViewSchedule.setAdapter(adapter);
    }

    /**
     * Hàm lọc dữ liệu tìm kiếm
     * @author nnhiep 30.03.2023
     */
    private void filterSchedule(String searchValue) {
        ArrayList<String> filteredList = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>(dataSource);
        for(int i = 0; i < temp.size(); i++) {
            if(temp.get(i).toLowerCase().contains(searchValue)) {
                filteredList.add(temp.get(i));
            }
        }

        adapter.filter(filteredList);
    }
}