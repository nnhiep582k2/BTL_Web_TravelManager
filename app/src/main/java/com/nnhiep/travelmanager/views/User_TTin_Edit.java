package com.nnhiep.travelmanager.views;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.database.Database;
import com.nnhiep.travelmanager.databinding.ActivityAddEmployeeBinding;
import com.nnhiep.travelmanager.databinding.UserLviewBinding;
import com.nnhiep.travelmanager.databinding.UserThongtinBinding;
import com.nnhiep.travelmanager.fragments.EmployeeFragment;
import com.nnhiep.travelmanager.models.Employee;
import com.nnhiep.travelmanager.models.User;

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
    Spinner spinnerGender;
    private static Database db;
    private Bitmap bitmap;
    private User user;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_thongtin);
        imgavatar = findViewById(R.id.imgavatar);
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtAge = findViewById(R.id.edtAge);
        spinnerGender = findViewById(R.id.spinnerGender);
        edtAccount = findViewById(R.id.edtAcount);
        edtPass = findViewById(R.id.edtPass);
        edtPhone = findViewById(R.id.edtPhone);
        btnBack = findViewById(R.id.btnBack);
        btnOK = findViewById(R.id.btnOK);
        setupSpinner();
        db = new Database(User_TTin_Edit.this);
        String query = "SELECT user_id,user_name, user_age, user_gender," +
                "user_account,user_password,user_phone,user_avatar FROM user";
        Cursor c = User_TTin_Edit.db.SelectData(query);
        c.moveToFirst();
        String id = c.getString(0);
        String name = c.getString(1);
        int age = c.getInt(2);
        int gender = c.getInt(3);
        String account = c.getString(4);
        String pass = c.getString(5);
        String phone = c.getString(6);
        byte[] image = c.getBlob(7);

        edtId.setText(id);
        edtName.setText(name);
        edtAge.setText(String.valueOf(age));
        if(gender==0){
            spinnerGender.setSelection(0);
        }
        else {
            spinnerGender.setSelection(gender);
        }
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
                String genderStr = spinnerGender.getSelectedItem().toString().trim();
                int gender = genderStr.equals("Male") ? 0 : 1;

                String account = edtAccount.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();


                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgavatar.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 1, byteArrayOutputStream);
                byte[] image = byteArrayOutputStream.toByteArray();


                db.updateAnUser( id, name, age, gender, account, pass, phone,image);
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
    private void setupSpinner() {
        ArrayList<String> arrayGender = new ArrayList<>();
        arrayGender.add(this.getResources().getString(R.string.male));
        arrayGender.add(this.getResources().getString(R.string.female));


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrayGender);
        spinnerGender.setAdapter(adapter);
        final int[] gender = {0}; // 0 là male, 1 là female

        // Xử lý sự kiện khi chọn giới tính từ Spinner
        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedGender = adapterView.getItemAtPosition(position).toString();

                if (selectedGender.equals("Male")) {
                    gender[0] = 0;
                } else if (selectedGender.equals("Female")) {
                    gender[0] = 1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    
}

