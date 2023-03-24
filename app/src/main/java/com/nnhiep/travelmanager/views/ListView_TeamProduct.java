package com.nnhiep.travelmanager.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.databinding.ActivityMainBinding;
import com.nnhiep.travelmanager.databinding.ListviewTeamproductMainBinding;

import java.util.ArrayList;

public class ListView_TeamProduct extends AppCompatActivity {
    //ActivityMainBinding binding;
    private ListviewTeamproductMainBinding binding;
    private ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ListviewTeamproductMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        int[] imageId= {R.drawable.shigeotokuda,R.drawable.bac,R.drawable.thuan,
                R.drawable.hiep,R.drawable.quoc};
         String[] name = {"Le Thi Phuong","Nguyen Phuong Bac","Nguyen Duc Thuan","Nguyen Ngoc Hiep","Tran Bao Quoc"};

        String[] lastMessage ={"Heya","Supp","Let's Catchup","Dinner tonight","Getcha"};
        String[] lastmsgTime ={"8:45 pm","8:45 pm","8:45 pm","8:45 pm","8:45 pm"};
        String[] phone = {"7788880023","01488880023","2228880023","7888880023","7789980023"};
        String[] country = {"Ha Noi","Singapore","Campuchia","ThaiLan","Korea"};


        ArrayList<User_List_Team> user_arrayList = new ArrayList<>();
        for (int i =0;i<imageId.length;i++){
            User_List_Team user = new User_List_Team(name[i],lastMessage[i],lastmsgTime[i],phone[i],country[i],imageId[i]);
            user_arrayList.add(user);

        }


        ListAdapter_TeamPro_Set lstAdapter = new ListAdapter_TeamPro_Set(ListView_TeamProduct.this, user_arrayList);
//       lst.setAdapter(lstAdapter);
        binding.listview.setAdapter(lstAdapter);
//       lst.setClickable(true);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent m = new Intent(ListView_TeamProduct.this,UserActivity.class);
                m.putExtra("name",name[position]);
                m.putExtra("phone",phone[position]);
                m.putExtra("country",country[position]);
                m.putExtra("imageid",imageId[position]);

                startActivity(m);

            }
        });



    }
}
