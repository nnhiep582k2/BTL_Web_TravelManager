package com.nnhiep.travelmanager.fragments;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.adapters.EmployeeAdapter;
import com.nnhiep.travelmanager.database.Database;
import com.nnhiep.travelmanager.models.Employee;
import com.nnhiep.travelmanager.views.AddEmployeeActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * Employee - Hiệp
 */
public class EmployeeFragment extends Fragment {
    RecyclerView rViewEmployee;
    Database db;
    EmployeeAdapter adapter;
    List<Employee> dataSource;
    Button btnChangeView;
    TextView txtNoData;
    LinearLayout layoutItemEmployee;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employee, container, false);
        View childView = inflater.inflate(R.layout.employee_item, container, false);
        layoutItemEmployee = childView.findViewById(R.id.layoutItemEmployee);
        db = new Database(this.getContext());

        // Thông báo - nnhiep 25.03.2023
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("EmployeeFragment", "Employee Fragment", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = this.getContext().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        rViewEmployee = view.findViewById(R.id.rViewEmployee);
        btnChangeView = view.findViewById(R.id.btnGridView);
        txtNoData = view.findViewById(R.id.txtNoData);

        getDataEmployee("employee_modified_date", "DESC", false, "");
        buildRecyclerView(view);
        buildDropdownSort(view);

        // Xử lý click thêm - nnhiep 20.03.2023
        view.findViewById(R.id.btnAddEmployee).setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddEmployeeActivity.class);
            startActivityForResult(intent, 250);
        });

        // Xử lý đổi dạng layout - nnhiep 20.03.2023
        btnChangeView.setOnClickListener(v -> {
            // Chuyển về dạng grid view - GridLayout
            if(btnChangeView.getText().equals(getContext().getResources().getString(R.string.grid_view))) {
                rViewEmployee.setLayoutManager(new GridLayoutManager(getContext(), 3));
                layoutItemEmployee.setOrientation(LinearLayout.VERTICAL);

                btnChangeView.setText(getContext().getResources().getString(R.string.list_view));
                btnChangeView.setCompoundDrawablesWithIntrinsicBounds(this.getResources().getDrawable(R.drawable.ic_baseline_view_list_24), null, null, null);
            } else {
                // Chuyển về dạng list view - LinearLayout
                rViewEmployee.setLayoutManager(new LinearLayoutManager(getContext()));
                layoutItemEmployee.setOrientation(LinearLayout.HORIZONTAL);

                btnChangeView.setText(getContext().getResources().getString(R.string.grid_view));
                btnChangeView.setCompoundDrawablesWithIntrinsicBounds(this.getResources().getDrawable(R.drawable.ic_baseline_grid_view_24), null, null, null);
            }
        });

        // Xử lý sự kiện tìm kiếm nhân viên - nnhiep 20.03.2023
        EditText eTxtSearchEmployee = view.findViewById(R.id.eTxtSearchEmployee);
        eTxtSearchEmployee.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                filterEmployee(s.toString());
            }
        });

        return view;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        String phoneNumber = adapter.getItem(item.getGroupId()).getPhone();
        switch (item.getItemId()) {
            // Xử lý click gửi tin nhắn - nnhiep 22.03.2023
            case 1:
                Intent intentMessage = new Intent(Intent.ACTION_SENDTO);
                intentMessage.setData(Uri.parse("smsto:" + phoneNumber));
                startActivity(intentMessage);
                break;
            // Xử lý click gọi điện - nnhiep 22.03.2023
            case 2:
                Intent intentCall = new Intent(Intent.ACTION_DIAL);
                intentCall.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(intentCall);
                break;
            // Xử lý click sửa - nnhiep 22.03.2023
            case 3:
                Intent intent = new Intent(this.getContext(), AddEmployeeActivity.class);
                intent.putExtra("ID", adapter.getItem(item.getGroupId()).getId());
                startActivityForResult(intent, 200);
                break;
            // Xử lý click xóa - nnhiep 22.03.2023
            case 4:
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle(getContext().getResources().getString(R.string.delete));
                dialog.setMessage(getContext().getResources().getString(R.string.sure_to_delete) + adapter.getItem(item.getGroupId()).getName() + "?");
                dialog.setPositiveButton(getContext().getResources().getString(R.string.delete), (dialog1, which) -> {
                    db.deleteEmployee(adapter.getItem(item.getGroupId()).getId());
                    notifyToUser(2);
                    refreshData("employee_modified_date","DESC", false, "");
                });
                dialog.setNegativeButton(getContext().getResources().getString(R.string.edit), (dialog2, which) -> {
                });
                dialog.show();
        }
        return super.onContextItemSelected(item);
    }

    /**
     * Hàm lấy dữ liệu nhân viên
     * @author nnhiep 30.03.2023
     */
    private void getDataEmployee(String sortBy, String order, boolean isFilter, String searchValue) {
        if(!isFilter) {
            dataSource = db.getDataEmployee(sortBy, order);
        } else {
            dataSource = db.getDataEmployeeByFilter(searchValue);
        }
        if(dataSource.size() == 0) {
            dataSource = new ArrayList<>();
            rViewEmployee.setVisibility(View.GONE);
            txtNoData.setVisibility(View.VISIBLE);
        } else {
            txtNoData.setVisibility(View.GONE);
            rViewEmployee.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Hàm thiết lập recycle view
     * @author nnhiep 30.03.2023
     */
    private void buildRecyclerView(View view) {
        adapter = new EmployeeAdapter(dataSource, view.getContext());
        rViewEmployee.setLayoutManager(new LinearLayoutManager(getContext()));
        rViewEmployee.setAdapter(adapter);
    }

    /**
     * Hàm thiết lập dropdown cho chức năng sort
     * @author nnhiep 30.03.2023
     */
    private void buildDropdownSort(View view) {
        String[] dropdownSort = view.getResources().getStringArray(R.array.sort_employee);
        AutoCompleteTextView aCompleteSort =  view.findViewById(R.id.aCompleteSort);
        ArrayAdapter sortAdapter = new ArrayAdapter(requireContext(), R.layout.dropdown_employee_item, dropdownSort);
        aCompleteSort.setAdapter(sortAdapter);
        aCompleteSort.setOnItemClickListener((parent, view1, position, id) -> {
            switch (position) {
                case 0:
                    refreshData("employee_modified_date","DESC", false, "");
                    break;
                case 1:
                    refreshData("employee_name", "ASC", false, "");
                    break;
                case 2:
                    refreshData("employee_age", "ASC", false, "");
                    break;
                case 3:
                    refreshData("employee_gender", "ASC", false, "");
                    break;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200 && resultCode == 150) {
            notifyToUser(1);
            refreshData("employee_modified_date", "DESC", false, "");
        }
        if(requestCode == 250 && resultCode == 150) {
            notifyToUser(0);
            refreshData("employee_modified_date", "DESC", false, "");
        }
    }

    /**
     * Gửi thông báo đến người dùng khi thêm thành công nhân viên
     * @author nnhiep 24.03.2023
     */
    private void notifyToUser(int mode) {
        // Tạo đối tượng NotificationCompat.Builder - nnhiep 25.03.2023
        NotificationCompat.Builder notification = new NotificationCompat.Builder(getContext(), "EmployeeFragment")
                .setAutoCancel(true);
        // Thêm
        if(mode == 0) {
            notification
                    .setSmallIcon(R.drawable.ic_baseline_notification_add_24)
                    .setContentTitle(getContext().getResources().getString(R.string.add_success_title))
                    .setContentText(getContext().getResources().getString(R.string.add_success_content));
        }
        // Sửa
        else if(mode == 1) {
            notification
                    .setSmallIcon(R.drawable.ic_baseline_edit_notifications_24)
                    .setContentTitle(getContext().getResources().getString(R.string.edit_success_title))
                    .setContentText(getContext().getResources().getString(R.string.edit_success_content));
        } else {
            notification
                    .setSmallIcon(R.drawable.ic_baseline_notification_important_24)
                    .setContentTitle(getContext().getResources().getString(R.string.delete_success_title))
                    .setContentText(getContext().getResources().getString(R.string.delete_success_content));
        }
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this.getContext());
        managerCompat.notify(1, notification.build());
    }

    /**
     * Lấy lại dữ liệu
     * @author nnhiep 24.03.2023
     */
    private void refreshData(String sortBy, String order, boolean isFilter, String searchValue) {
        if(!isFilter) {
            getDataEmployee(sortBy, order, false, "");
        } else {
            getDataEmployee(sortBy, order, true, searchValue);
        }
        adapter = new EmployeeAdapter(dataSource, getContext());
        rViewEmployee.setLayoutManager(new LinearLayoutManager(getContext()));
        rViewEmployee.setAdapter(adapter);
    }

    /**
     * Hàm lọc dữ liệu tìm kiếm
     * @author nnhiep 30.03.2023
     */
    private void filterEmployee(String searchValue) {
        refreshData("employee_modified_date", "DESC", true, searchValue);
    }
}