package com.nnhiep.travelmanager.adapters;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.models.EmployeeTable;
import java.util.List;

/**
 * Adapter hiển thị lên recycle view
 * @author nnhiep 20.03.2023
 */
public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeViewHolder> {
    List<EmployeeTable> items;
    Context context;

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_item, parent, false);
        return new EmployeeViewHolder(view).linkAdapter(this);
    }

    public EmployeeAdapter(List<EmployeeTable> items, Context context) {
        this.context = context;
        this.items = items;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        holder.txtEmployeeName.setText(items.get(position).getName());
        holder.txtEmployeeAge.setText(formatAge(items.get(position).getAge()));
        holder.txtEmployeeGender.setText(formatGender(items.get(position).getGender()));
        holder.txtEmployeePhone.setText(items.get(position).getPhone());
        holder.imgAvatar.setImageBitmap(items.get(position).getAvatar());
    }

    // Hàm định dạng tuổi - nnhiep 22.03.2023
    private String formatAge(int age) {
        return age + context.getResources().getString(R.string.adapter_age);
    }

    // Hàm định dạng giới tính - nnhiep 22.03.2023
    private String formatGender(int gender) {
        String sex = "";
        switch (gender) {
            case 0:
                sex = context.getResources().getString(R.string.female);
                break;
            case 1:
                sex = context.getResources().getString(R.string.male);
                break;
            case 2:
                sex = context.getResources().getString(R.string.other);
                break;
        }
        return sex;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // Lấy một phần tử trong mảng - nnhiep 20.03.2023
    public EmployeeTable getItem(int position) { return items.get(position); }

    // Lấy tất cả phần tử trong mảng - nnhiep 20.03.2023
    public List<EmployeeTable> getItems() { return this.items; }
}

class EmployeeViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
    private EmployeeAdapter employeeAdapter;
    TextView txtEmployeeName, txtEmployeeAge, txtEmployeeGender, txtEmployeePhone;
    de.hdodenhof.circleimageview.CircleImageView imgAvatar;
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
        menu.setHeaderTitle(v.getContext().getResources().getString(R.string.action));
        menu.add(getAdapterPosition(), 1, 0, v.getContext().getResources().getString(R.string.send_message));
        menu.add(getAdapterPosition(), 2, 1, v.getContext().getResources().getString(R.string.call));
        menu.add(getAdapterPosition(), 3, 2, v.getContext().getResources().getString(R.string.edit));
        menu.add(getAdapterPosition(), 4, 3, v.getContext().getResources().getString(R.string.delete));
    }
}
