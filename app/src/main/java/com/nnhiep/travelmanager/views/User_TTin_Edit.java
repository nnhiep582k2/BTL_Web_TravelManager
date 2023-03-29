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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.database.Database;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class User_TTin_Edit extends AppCompatActivity {
    private ImageView imgavatar;
    private EditText edtId, edtName, edtAge, edtAccount, edtPass, edtPhone;
    private Button btnBack, btnOK;
    Spinner spinnerGender;
    private static Database db;

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
                result -> {
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
                });
        imgavatar = (ImageView) findViewById(R.id.imgavatar);
        imgavatar.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            activityPickerResultLauncher.launch(intent);
        });
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> finish());
        btnOK = (Button) findViewById(R.id.btnOK);
        btnOK.setOnClickListener(view -> {
            String id1 = edtId.getText().toString().trim();
            String name1 = edtName.getText().toString().trim();
            int age1 = Integer.parseInt(edtAge.getText().toString().trim());
            String genderStr = spinnerGender.getSelectedItem().toString().trim();
            int gender1 = genderStr.equals(getResources().getString(R.string.male)) ? 0 : 1;
            String account1 = edtAccount.getText().toString().trim();
            String pass1 = edtPass.getText().toString().trim();
            String phone1 = edtPhone.getText().toString().trim();
            BitmapDrawable bitmapDrawable = (BitmapDrawable) imgavatar.getDrawable();
            Bitmap bitmap = bitmapDrawable.getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 1, byteArrayOutputStream);
            byte[] image1 = byteArrayOutputStream.toByteArray();
            db.updateAnUser(id1, name1, age1, gender1, account1, pass1, phone1, image1);
            Intent intent = new Intent(User_TTin_Edit.this, User_TTin.class);
            startActivityForResult(intent, 100);
        });
        btnBack.setOnClickListener(view -> finish());
        edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name = s.toString().trim();
                if (name.isEmpty()) {
                    edtName.setError(getResources().getString(R.string.name_empty));
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
                    edtAge.setError(getResources().getString(R.string.age_empty));
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
                    edtPhone.setError(getResources().getString(R.string.phone_empty));
                } else if (phoneNumber.length() != 10) {
                    edtPhone.setError(getResources().getString(R.string.phone_invalid));
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
                if (selectedGender.equals(getResources().getString(R.string.male))) {
                    gender[0] = 0;
                } else if (selectedGender.equals(getResources().getString(R.string.female))) {
                    gender[0] = 1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}

