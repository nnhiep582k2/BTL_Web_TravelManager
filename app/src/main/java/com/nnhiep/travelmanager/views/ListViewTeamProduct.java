package com.nnhiep.travelmanager.views;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.adapters.ListAdapterTeamProSet;
import com.nnhiep.travelmanager.databinding.ListviewTeamproductMainBinding;
import com.nnhiep.travelmanager.models.UserListTeam;

import java.util.ArrayList;

public class ListViewTeamProduct extends AppCompatActivity {
    private ListviewTeamproductMainBinding binding;

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

        ArrayList<UserListTeam> user_arrayList = new ArrayList<>();

        for (int i =0;i<imageId.length;i++){
            UserListTeam user = new UserListTeam(name[i],lastMessage[i],lastmsgTime[i],phone[i],country[i],imageId[i]);
            user_arrayList.add(user);
        }

        ListAdapterTeamProSet lstAdapter = new ListAdapterTeamProSet(ListViewTeamProduct.this, user_arrayList);
        binding.listview.setAdapter(lstAdapter);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener((parent, view, position, id) -> {
            Intent m = new Intent(ListViewTeamProduct.this,UserActivity.class);
            m.putExtra("name",name[position]);
            m.putExtra("phone",phone[position]);
            m.putExtra("country",country[position]);
            m.putExtra("imageid",imageId[position]);
            startActivity(m);
        });
    }
}
