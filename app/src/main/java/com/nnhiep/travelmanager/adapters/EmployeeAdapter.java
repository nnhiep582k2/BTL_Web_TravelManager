package com.nnhiep.travelmanager.adapters;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.nnhiep.travelmanager.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Adapter hiển thị lên recycle view
 * @author nnhiep 20.03.2023
 */
public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeViewHolder> {
    List<String> items;

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_item, parent, false);
        return new EmployeeViewHolder(view).linkAdapter(this);
    }

    public EmployeeAdapter(List<String> items) {
        this.items = items;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        holder.txtTitleEmployee.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // Lấy một phần tử trong mảng - nnhiep 20.03.2023
    public String getItem(int position) { return items.get(position); }

    // Lấy tất cả phần tử trong mảng - nnhiep 20.03.2023
    public List<String> getItems() { return this.items; }

    // Hàm lọc dữ liệu - nnhiep 20.03.2023
    public void filter(ArrayList<String> filteredList) {
        items = filteredList;
        notifyDataSetChanged();
    }
}

class EmployeeViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
    private EmployeeAdapter employeeAdapter;
    TextView txtTitleEmployee;
    CardView cViewEmployee;

    public EmployeeViewHolder(@NonNull View itemView) {
        super(itemView);
        txtTitleEmployee = itemView.findViewById(R.id.txtTitleEmployee);
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
