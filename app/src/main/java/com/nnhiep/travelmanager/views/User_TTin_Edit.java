package com.nnhiep.travelmanager.views;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.database.Database;
import com.nnhiep.travelmanager.databinding.UserLviewBinding;
import com.nnhiep.travelmanager.databinding.UserThongtinBinding;
import com.nnhiep.travelmanager.models.Employee;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class User_TTin_Edit extends AppCompatActivity {
    private ImageView imgavatar;
    private EditText edtId, edtName, edtAge, edtGender, edtAccount, edtPass, edtPhone;
    private Button btnBack, btnOK;
    private ArrayList<String> user_name, user_account, user_password, user_phone;
    private UserThongtinBinding binding;
    private ArrayList<Number> user_age, user_gender;
    private ArrayList<byte[]> user_avatar;
    private static Database db;
    private Bitmap bitmap;
    private Employee employee;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_thongtin);
        imgavatar = findViewById(R.id.imgavatar);
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtAge = findViewById(R.id.edtAge);
        edtGender = findViewById(R.id.edtGender);
        edtAccount = findViewById(R.id.edtAcount);
        edtPass = findViewById(R.id.edtPass);
        edtPhone = findViewById(R.id.edtPhone);
        btnBack = findViewById(R.id.btnBack);
        btnOK = findViewById(R.id.btnOK);
        db = new Database(User_TTin_Edit.this);
        String query = "SELECT employee_avatar,employee_id,employee_name, employee_age, employee_gender," +
                "employee_account,employee_password,employee_phone FROM employee";
        Cursor c = User_TTin_Edit.db.SelectData(query);
        c.moveToFirst();
        byte[] image = c.getBlob(0);
        String id = c.getString(1);
        String name = c.getString(2);
        int age = c.getInt(3);
        int gender = c.getInt(4);
        String account = c.getString(5);
        String pass = c.getString(6);
        String phone = c.getString(7);

        edtId.setText(id);
        edtName.setText(name);
        edtAge.setText(String.valueOf(age));
        edtGender.setText(String.valueOf(gender));
        edtAccount.setText(account);
        edtPass.setText(pass);
        edtPhone.setText(phone);
        if(image==null){
            imgavatar.setImageBitmap(null);
        }else{
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            imgavatar.setImageBitmap(bitmap);
        }
        ActivityResultLauncher<Intent> activityPickerResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Uri uri = result.getData().getData();
                            try {
                                InputStream inputStream = getContentResolver().openInputStream(uri);
                                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                imgavatar.setImageBitmap(bitmap);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            imgavatar = (ImageView) findViewById(R.id.imgavatar);
            imgavatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    activityPickerResultLauncher.launch(intent);
                }
            });
            btnBack = (Button) findViewById(R.id.btnBack);
            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            btnOK = (Button) findViewById(R.id.btnOK);
            btnOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id = edtId.getText().toString().trim();
                    String name = edtName.getText().toString().trim();
                    int age = Integer.parseInt(edtAge.getText().toString().trim());
                    int gender = Integer.parseInt(edtGender.getText().toString().trim());
                    String account = edtAccount.getText().toString().trim();
                    String pass = edtPass.getText().toString().trim();
                    String phone = edtPhone.getText().toString().trim();


                    BitmapDrawable bitmapDrawable = (BitmapDrawable) imgavatar.getDrawable();
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 1, byteArrayOutputStream);
                    byte[] image = byteArrayOutputStream.toByteArray();


                    db.updateAnEmployee(image, id, name, age, gender, account, pass, phone);
                    Intent intent = new Intent(User_TTin_Edit.this, User_TTin.class);
                    startActivityForResult(intent, 100);
                }
            });
            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name = s.toString().trim();
                if (name.isEmpty()) {
                    edtName.setError("Họ và tên không được để trống!");
                } else {
                    edtName.setError(null);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        edtAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name = s.toString().trim();
                if (name.isEmpty()) {
                    edtAge.setError("Tuổi không được để trống!");
                } else {
                    edtAge.setError(null);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        edtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String phoneNumber = s.toString().trim();
                if (phoneNumber.isEmpty()) {
                    edtPhone.setError("Số điện thoại không được để trống!");
                } else if (phoneNumber.length() != 10) {
                    edtPhone.setError("Số điện thoại phải đủ 10 số!");
                } else {
                    edtPhone.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        }



//    @Override
//    protected void onResume() {
//        super.onResume();
//        //byte[] avatar = Employee.getBlob(1);
//        Employee employee = new Employee(null,"","",0,0,user_account.get(0), user_password.get(0),"");
//        edtId.requestFocus();
//        byte[] image =employee.getAvatar();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
//        imgavatar.setImageBitmap(bitmap);
//        edtId.setText(employee.getId());
//        edtName.setText(employee.getName());
//        edtGender.setText(String.valueOf(employee.getAge()));
//        edtGender.setText(String.valueOf(employee.getGender()));
//        edtAccount.setText(employee.getAccount());
//        edtPass.setText(employee.getPassword());
//        edtPhone.setText(employee.getPhone());
//        setup_ui();
//    }
//        /**
//         * Hàm khởi tạo các biến
//         */
//        private void setup_ui() {
//            user_account = new ArrayList<>();
//            user_password = new ArrayList<>();
//            user_name = new ArrayList<>();
//            user_age = new ArrayList<>();
//            user_gender = new ArrayList<>();
//            user_phone = new ArrayList<>();
//            user_avatar = new ArrayList<>();
//            imgavatar = binding.imgavatar;
//            edtId = binding.edtId;
//            edtName = binding.edtName;
//            edtAccount=binding.edtAcount;
//            edtAge = binding.edtAge;
//            edtPass = binding.edtPass;
//            edtGender=binding.edtGender;
//            edtPhone = binding.edtPhone;
//
//
//            getDataUser();
//
//        }
//
//        /**
//         * Lấy thông tin người dùng
//         */
//        private void getDataUser() {
//            try {
//                Cursor cursor = db.getDataEmployee();
//                if(cursor.getCount() == 0) {
//                    Toast.makeText(this, "User has no data", Toast.LENGTH_SHORT).show();
//                } else {
//                    while (cursor.moveToNext()) {
//                        user_name.add(cursor.getString(1));
//                        user_age.add(cursor.getInt(2));
//                        user_gender.add(cursor.getInt(3));
//                        user_account.add(cursor.getString(4));
//                        user_password.add(cursor.getString(5));
//                        user_phone.add(cursor.getString(6));
//                        user_avatar.add(cursor.getBlob(7));
//
//                    }
//                    cursor.close();
//                }
//            } catch (Exception e) {
//                Toast.makeText(this, "An error occur!", Toast.LENGTH_SHORT).show();
//                Log.d("Error DB", e.getMessage());
//            }
//        }
    }

