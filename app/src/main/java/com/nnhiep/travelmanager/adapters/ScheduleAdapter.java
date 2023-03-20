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
public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleViewHolder> {
    List<String> items;

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_item, parent, false);
        return new ScheduleViewHolder(view).linkAdapter(this);
    }

    public ScheduleAdapter(List<String> items) {
        this.items = items;
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        holder.txtTitleSchedule.setText(items.get(position));
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

class ScheduleViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
    private ScheduleAdapter scheduleAdapter;
    TextView txtTitleSchedule;
    CardView cViewSchedule;

    public ScheduleViewHolder(@NonNull View itemView) {
        super(itemView);
        txtTitleSchedule = itemView.findViewById(R.id.txtTitleSchedule);
        cViewSchedule = itemView.findViewById(R.id.cViewSchedule);
        cViewSchedule.setOnCreateContextMenuListener(this);
    }

    /**
     * Liên kết với adapter
     * @param adapter - adapter muốn liên kết
     * @return instance của view holder
     * @author nnhiep 20.03.2023
     */
    public ScheduleViewHolder linkAdapter(ScheduleAdapter adapter) {
        this.scheduleAdapter = adapter;
        return this;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Action");
        menu.add(getAdapterPosition(), 1, 0, "Delete");
    }
}
