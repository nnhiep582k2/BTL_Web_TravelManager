package com.nnhiep.travelmanager.views;
import android.content.Intent;
import android.database.Cursor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.database.Database;
import com.nnhiep.travelmanager.databinding.ActivityLoginBinding;
import com.nnhiep.travelmanager.databinding.UserLviewBinding;
import com.nnhiep.travelmanager.fragments.UserFragment;
import com.nnhiep.travelmanager.models.Employee;
import com.nnhiep.travelmanager.models.User;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class User_TTin extends AppCompatActivity {
    private Button btnEdit;
    private TextView txtid, txtName, txtAge, txtGender, txtAccount, txtPass, txtSDT;

    private ImageView avatar;
    Database db;
    private ArrayList<String> user_id, user_name, user_account, user_password, user_phone;
    private UserLviewBinding binding;
    private Button btn_edit, btn_back;

    private ArrayList<Number> user_age, user_gender;
    private ArrayList<byte[]> user_avatar;
    Cursor cursor = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Gán giá trị cho đối tượng view binding
        binding = UserLviewBinding.inflate(getLayoutInflater());
        // Thiết lập layout cho activity
        setContentView(binding.getRoot());
        db = new Database(this);
        setup_ui();


        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User_TTin.this, User_TTin_Edit.class);
                startActivity(intent);
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Đóng màn hình hiện tại
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        User user = new User( user_id.get(0), user_name.get(0), user_phone.get(0), user_account.get(0), user_password.get(0), user_age.get(0).intValue(), user_gender.get(0).intValue(),user_avatar.get(0));
        txtid.requestFocus();
        byte[] imageBytes = user.getAvatar(); // Lấy dữ liệu ảnh dưới dạng byte từ đối tượng Employee
        if (imageBytes == null || imageBytes.length == 0) {
            avatar.setImageBitmap(null); // Không có ảnh thì set ImageView thành null
        } else {
            Bitmap bitmap1 = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length); // Chuyển đổi dữ liệu byte sang bitmap
            avatar.setImageBitmap(bitmap1); // Hiển thị ảnh
        }


        txtid.setText(user.getId());
        txtName.setText(user.getName());
        txtAge.setText(String.valueOf(user.getAge()));
        String genderText = "";
        if (user.getGender() == 0) {
            genderText = "Male";
        } else if (user.getGender() == 1) {
            genderText = "Female";
        }
        txtGender.setText(genderText);

        txtAccount.setText(user.getAccount());
        txtPass.setText(user.getPassword());
        txtSDT.setText(user.getPhone());
        setup_ui();
    }

    /**
     * Hàm khởi tạo các biến
     */
    private void setup_ui() {
        user_id = new ArrayList<>();
        user_account = new ArrayList<>();
        user_password = new ArrayList<>();
        user_name = new ArrayList<>();
        user_age = new ArrayList<>();
        user_gender = new ArrayList<>();
        user_phone = new ArrayList<>();
        user_avatar = new ArrayList<>();
        avatar = binding.imgavatar;
        txtid = binding.txtId;
        txtName = binding.txtTen;
        txtAccount = binding.txtAccount;
        txtAge = binding.txtTuoi;
        txtPass = binding.txtPass;
        txtGender = binding.txtGender;
        txtSDT = binding.txtSDT;
        btn_edit = binding.btnEdit;
        btn_back = binding.btnQlai;


        getDataUser();

    }

    /**
     * Lấy thông tin người dùng
     */
    private void getDataUser() {
        try {
            Cursor cursor = db.getDataUser();
            if (cursor.getCount() == 0) {
                Toast.makeText(this, "User has no data", Toast.LENGTH_SHORT).show();
            } else {
                while (cursor.moveToNext()) {
                    user_id.add(cursor.getString(0));
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
}
