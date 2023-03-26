package com.nnhiep.travelmanager.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.models.DSTour;
import java.util.ArrayList;

public class TourAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<DSTour> TourList;

    public TourAdapter(Context context, ArrayList<DSTour> tourList) {
        this.context = context;
        TourList = tourList;
    }

    @Override
    public int getCount() {
        return TourList.size();
    }

    @Override
    public Object getItem(int position) {
        return TourList.get(position);
    }

    public void searchFilter(ArrayList<DSTour> searchList) {
        TourList = new ArrayList<>();
        TourList.addAll(searchList);
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_search, null);
        ImageView img_tour = view.findViewById(R.id.imageView_flag);
        TextView txtTitle = view.findViewById(R.id.Nametour);
        TextView txtPrice = view.findViewById(R.id.fee);
        TextView txtStar = view.findViewById(R.id.Star);
        TextView txtdate = view.findViewById(R.id.txtdate);
        DSTour dsTour = TourList.get(position);
        String title = dsTour.getTitle();
        String price = dsTour.getPrice();
        String star = dsTour.getStar();
        String date = dsTour.getDate();
        byte[] image = dsTour.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        txtTitle.setText(title);
        txtPrice.setText(price);
        txtStar.setText(star);
        txtdate.setText(date);
        img_tour.setImageBitmap(bitmap);

        return view;
    }
}

