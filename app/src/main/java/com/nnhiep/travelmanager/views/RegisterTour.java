package com.nnhiep.travelmanager.views;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

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
//     AbsListView.MultiChoiceModeListener modeListener= new AbsListView.MultiChoiceModeListener() {
//         @Override
//         public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//              getMenuInflater().inflate(R.menu.abs_menu,menu);
//            return true;
//         }
//
//         @Override
//         public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
//             return false;
//         }
//
//         @Override
//         public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//             if (item.getTitle().equals("delete")) {
//                 AlertDialog.Builder builder = new AlertDialog.Builder(RegisterTour.this);
//                 builder.setTitle("delete")
//                         .setMessage("Are you sure?")
//                         .setIcon(R.drawable.delete)
//                         .setPositiveButton("delete", new DialogInterface.OnClickListener() {
//                             @Override
//                             public void onClick(DialogInterface dialog, int which) {
//                                 int res = db.deleteRegisterTour(String.valueOf(Tid));
//                                 if (res > 0) {
//                                     // showTourdata();
//                                     mode.finish();
//                                     Toast.makeText(RegisterTour.this, "deleted", Toast.LENGTH_SHORT).show();
//                                 } else {
//                                     Toast.makeText(RegisterTour.this, "Not deleted", Toast.LENGTH_SHORT).show();
//                                 }
//                             }
//                         })
//                         .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                             @Override
//                             public void onClick(DialogInterface dialog, int which) {
//
//                             }
//                         });
//                 builder.create().show();
//             }
//             return true;
//         }
//
//         @Override
//         public void onDestroyActionMode(ActionMode mode) {
//
//         }
//
//         @Override
//         public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
//
//             dsTour = arrayList.get(position);
//             Tid = dsTour.getId();
//         }
//     };

}