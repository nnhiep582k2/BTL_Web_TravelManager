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
import android.widget.Button;
import android.widget.EditText;
import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.adapters.EmployeeAdapter;
import java.util.ArrayList;

/**
 * Employee - Hiệp
 */
public class EmployeeFragment extends Fragment {
    String[] dataEmployee = {"Employee A", "Employee B", "Employee C"};
    int counter = 0;
    RecyclerView rViewEmployee;
    EmployeeAdapter adapter;
    ArrayList<String> dataSource;
    Button btnChangeView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_employee, container, false);

        getDataEmployee();
        buildRecyclerView(view);

        // Xử lý click thêm - nnhiep 20.03.2023
        view.findViewById(R.id.btnAddEmployee).setOnClickListener(v -> {
            dataSource.add(dataEmployee[counter%3]);
            counter++;
            adapter.notifyItemInserted(dataSource.size() - 1);
        });

        // Xử lý click sắp xếp - nnhiep 20.03.2023
        view.findViewById(R.id.btnSortEmployee).setOnClickListener(v -> {

        });

        // Xử lý đổi dạng layout - nnhiep 20.03.2023
        btnChangeView.setOnClickListener(v -> {
            // Chuyển về dạng grid view - GridLayout
            if(btnChangeView.getText().equals("Grid")) {
                rViewEmployee.setLayoutManager(new GridLayoutManager(getContext(), 2));
                btnChangeView.setText("List");
                btnChangeView.setCompoundDrawablesWithIntrinsicBounds(this.getResources().getDrawable(R.drawable.ic_baseline_view_list_24), null, null, null);
            } else {
                // Chuyển về dạng list view - LinearLayout
                rViewEmployee.setLayoutManager(new LinearLayoutManager(getContext()));
                btnChangeView.setText("Grid");
                btnChangeView.setCompoundDrawablesWithIntrinsicBounds(this.getResources().getDrawable(R.drawable.ic_baseline_grid_view_24), null, null, null);
            }
        });

        EditText eTxtSearchEmployee = view.findViewById(R.id.eTxtSearchEmployee);
        eTxtSearchEmployee.addTextChangedListener(new TextWatcher() {
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
                } else filterEmployee(s.toString());
            }
        });

        return view;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // Xử lý click gửi tin nhắn - nnhiep 22.03.2023
            case 1:
                break;
            // Xử lý click gọi điện - nnhiep 22.03.2023
            case 2:
                break;
            // Xử lý click sửa - nnhiep 22.03.2023
            case 3:
                break;
            // Xử lý click xóa - nnhiep 22.03.2023
            case 4:
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("Delete");
                dialog.setMessage("Are you sure to delete employee " + adapter.getItem(item.getGroupId()) + "?");
                dialog.setPositiveButton("Delete", (dialog1, which) -> {
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
     * Hàm lấy dữ liệu nhân viên
     * @author nnhiep 30.03.2023
     */
    private void getDataEmployee() {
        dataSource = new ArrayList<>();
        dataSource.add("Employee 1");
        dataSource.add("Employee 2");
        dataSource.add("Employee 3");
        dataSource.add("Employee 4");
        dataSource.add("Employee 5");
    }

    /**
     * Hàm thiết lập recycle view
     * @author nnhiep 30.03.2023
     */
    private void buildRecyclerView(View view) {
        rViewEmployee = view.findViewById(R.id.rViewEmployee);
        btnChangeView = view.findViewById(R.id.btnGridView);
        adapter = new EmployeeAdapter(dataSource);
        rViewEmployee.setLayoutManager(new LinearLayoutManager(getContext()));
        rViewEmployee.setAdapter(adapter);
    }

    /**
     * Hàm lọc dữ liệu tìm kiếm
     * @author nnhiep 30.03.2023
     */
    private void filterEmployee(String searchValue) {
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