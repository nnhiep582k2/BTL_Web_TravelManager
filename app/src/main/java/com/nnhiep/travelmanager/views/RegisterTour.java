package com.nnhiep.travelmanager.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
<<<<<<< HEAD
<<<<<<< HEAD
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
    DSTour dsTour;
    int Tid;

=======
=======
import android.widget.ListView;
>>>>>>> 443aaf1 (truoc khi nop)

import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.adapters.TourAdapter;
import com.nnhiep.travelmanager.database.Tour_database;
import com.nnhiep.travelmanager.models.DSTour;

import java.util.ArrayList;

public class RegisterTour extends AppCompatActivity {

<<<<<<< HEAD
>>>>>>> a186555 (truoc khi merge ndthuan)
=======
    ListView listView;
    Tour_database db;
    TourAdapter adapter;
    ArrayList<DSTour> arrayList;
    DSTour dsTour;
    int Tid;
>>>>>>> 443aaf1 (truoc khi nop)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_tour);
<<<<<<< HEAD
<<<<<<< HEAD
        Tour_database db = new Tour_database(this);

        listView = findViewById(R.id.listview_register_tour);
        arrayList = db.getRegisterData();
        adapter = new TourAdapter(this, arrayList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
=======
>>>>>>> a186555 (truoc khi merge ndthuan)
=======
        Tour_database db=new Tour_database(this);
        listView=findViewById(R.id.listview_register_tour);
        arrayList=db.getRegisterData();
        adapter=new TourAdapter(this,arrayList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

>>>>>>> 443aaf1 (truoc khi nop)
    }

}