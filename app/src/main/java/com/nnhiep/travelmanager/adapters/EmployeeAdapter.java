package com.nnhiep.travelmanager.adapters;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.models.Employee;
import java.util.ArrayList;
import java.util.List;

/**
 * Adapter hiển thị lên recycle view
 * @author nnhiep 20.03.2023
 */
public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeViewHolder> {
    List<Employee> items;

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_item, parent, false);
        return new EmployeeViewHolder(view).linkAdapter(this);
    }

    public EmployeeAdapter(List<Employee> items) {
        this.items = items;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        holder.txtEmployeeName.setText(items.get(position).getName());
        holder.txtEmployeeAge.setText(formatAge(items.get(position).getAge()));
        holder.txtEmployeeGender.setText(formatGender(items.get(position).getGender()));
        holder.txtEmployeePhone.setText(items.get(position).getPhone());
        holder.imgAvatar.setImageResource(items.get(position).getAvatar());
    }

    // Hàm định dạng tuổi - nnhiep 22.03.2023
    private String formatAge(int age) {
        return age + " tuổi";
    }

    // Hàm định dạng giới tính - nnhiep 22.03.2023
    private String formatGender(int gender) {
        String sex = "";
        switch (gender) {
            case 0:
                sex = "Nữ";
                break;
            case 1:
                sex = "Nam";
                break;
            case 2:
                sex = "Khác";
                break;
        }
        return sex;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // Lấy một phần tử trong mảng - nnhiep 20.03.2023
    public Employee getItem(int position) { return items.get(position); }

    // Lấy tất cả phần tử trong mảng - nnhiep 20.03.2023
    public List<Employee> getItems() { return this.items; }

    // Hàm lọc dữ liệu - nnhiep 20.03.2023
    public void filter(ArrayList<Employee> filteredList) {
        items = filteredList;
        notifyDataSetChanged();
    }
}

class EmployeeViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
    private EmployeeAdapter employeeAdapter;
    TextView txtEmployeeName, txtEmployeeAge, txtEmployeeGender, txtEmployeePhone;
    ImageView imgAvatar;
    CardView cViewEmployee;

    public EmployeeViewHolder(@NonNull View itemView) {
        super(itemView);
        imgAvatar = itemView.findViewById(R.id.imgAvatar);
        txtEmployeeName = itemView.findViewById(R.id.txtEmployeeName);
        txtEmployeeAge = itemView.findViewById(R.id.txtEmployeeAge);
        txtEmployeeGender = itemView.findViewById(R.id.txtEmployeeGender);
        txtEmployeePhone = itemView.findViewById(R.id.txtEmployeePhone);
        cViewEmployee = itemView.findViewById(R.id.cViewEmployee);
        cViewEmployee.setOnCreateContextMenuListener(this);
    }

    /**
     * Liên kết với adapter
     * @param adapter - adapter muốn liên kết
     * @return instance của view holder
     * @author nnhiep 20.03.2023
     */
    public EmployeeViewHolder linkAdapter(EmployeeAdapter adapter) {
        this.employeeAdapter = adapter;
        return this;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Action");
        menu.add(getAdapterPosition(), 1, 0, "Send Message");
        menu.add(getAdapterPosition(), 2, 1, "Call");
        menu.add(getAdapterPosition(), 3, 2, "Edit");
        menu.add(getAdapterPosition(), 4, 3, "Delete");
    }
}
