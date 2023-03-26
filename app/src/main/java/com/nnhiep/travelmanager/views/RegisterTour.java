package com.nnhiep.travelmanager.views;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.adapters.TourAdapter;
import com.nnhiep.travelmanager.database.Tour_database;
import com.nnhiep.travelmanager.models.DSTour;
import java.util.ArrayList;

public class RegisterTour extends AppCompatActivity {
    ListView listView;
    Tour_database db;
    TourAdapter adapter;
    ArrayList<DSTour> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_tour);
        Tour_database db = new Tour_database(this);
        listView = findViewById(R.id.listview_register_tour);
        arrayList = db.getRegisterData();
        adapter = new TourAdapter(this, arrayList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
