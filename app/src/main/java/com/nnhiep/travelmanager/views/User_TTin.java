package com.nnhiep.travelmanager.views;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.database.Database;
import com.nnhiep.travelmanager.databinding.ActivityLoginBinding;
import com.nnhiep.travelmanager.databinding.Listview2Binding;
import com.nnhiep.travelmanager.models.Employee;

import java.util.ArrayList;

public class User_TTin extends AppCompatActivity {
    Button btnEdit,btnBack;
    TextView txtName,txtAge,txtGender,txtAccount,txtPass,txtSDT;
    ImageView imglv;
    ListView lstshow;
    private Listview2Binding binding;
    private Database db;
    private ArrayList<String> user_name, user_account, user_password, user_phone;
    private ArrayList<Number> user_age, user_gender;
    private ArrayList<byte[]> user_avatar;
    private ArrayList<com.nnhiep.travelmanager.models.Employee> ContactList;
    private Employee ListAdapter;
    int SelectedId;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_listview_show);
        // Gán giá trị cho đối tượng view binding
        binding = Listview2Binding.inflate(getLayoutInflater());
        // Thiết lập layout cho activity
        setContentView(binding.getRoot());
        db = new Database(this);
        setup_ui();


        show();
        //ContactList.add(new Contact(1,"img1", "Trần Hoàng Long", "0829309678","nam định","quoc@gmail.com"));
        lstshow = (ListView) findViewById(R.id.lstContact);
        //Them du lieu Lan dau vao db



        //bat su kien nut sua
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(User_TTin.this, User_TTin_Edit.class);
                Employee employee =ContactList.get(SelectedId);
                Bundle b = new Bundle();
                b.putString("Id", employee.getId());
                b.putString("Name", employee.getName());
                b.putInt("Age", employee.getAge());
                b.putInt("Gender", employee.getGender());
                b.putString("Account", employee.getAccount());
                b.putString("Password", employee.getPassword());
                b.putString("Phone", employee.getPhone());
                intent.putExtras(b);

                startActivityForResult(intent,100);
            }
        });
    }

    /**
     * Hàm khởi tạo các biến
     */
    private void setup_ui() {
        user_account = new ArrayList<>();
        user_password = new ArrayList<>();
        user_name = new ArrayList<>();
        user_age = new ArrayList<>();
        user_gender = new ArrayList<>();
        user_phone = new ArrayList<>();
        user_avatar = new ArrayList<>();
        txtName = binding.txtTen;
        txtAge = binding.txtAge;
        txtGender = binding.txtGender;
        txtAccount = binding.txtAccount;
        txtPass = binding.txtPass;
        txtSDT = binding.txtSDT;
        getDataUser();
    }
    /**
     * Lấy thông tin người dùng
     */
    private void getDataUser() {
        try {
            Cursor cursor = db.getDataEmployee();
            if(cursor.getCount() == 0) {
                Toast.makeText(this, "User has no data", Toast.LENGTH_SHORT).show();
            } else {
                while (cursor.moveToNext()) {
                    user_name.add(cursor.getString(1));
                    user_age.add(cursor.getInt(2));
                    user_gender.add(cursor.getInt(3));
                    user_account.add(cursor.getString(4));
                    user_password.add(cursor.getString(5));
                    user_phone.add(cursor.getString(6));
                    user_avatar.add(cursor.getBlob(7));
                }
                cursor.close();
            }
        } catch (Exception e) {
            Toast.makeText(this, "An error occur!", Toast.LENGTH_SHORT).show();
            Log.d("Error DB", e.getMessage());
        }
    }


    private void show() {
          btnEdit=(Button) findViewById(R.id.btnEdit);

    }
}
