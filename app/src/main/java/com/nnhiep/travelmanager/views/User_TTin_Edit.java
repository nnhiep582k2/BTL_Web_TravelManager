package com.nnhiep.travelmanager.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nnhiep.travelmanager.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class User_TTin_Edit extends AppCompatActivity {
    Button btnSua,btnBack;
    EditText edtName,edtPhone,edtAddress,edtGmail;
    ImageButton imgTTin;
    int request_code_img=300;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== request_code_img && resultCode ==RESULT_OK && data !=null)
        {
            Uri uri=data.getData();
            try {
                //mở chỗ đọc và lấy đường dẫn ảnh
                InputStream inputStream=getContentResolver().openInputStream(uri);
                //chuyển về bitmap
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                imgTTin.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_thongtin);
        show();
        imgTTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,request_code_img);

            }
        });
        //bat su kien nut sua
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //chuyển data của imageview sang byte[]
                BitmapDrawable bitmapDrawble=(BitmapDrawable)imgTTin.getDrawable();
                //chuyển BitmapDrwable chuyển về bitmap
                Bitmap bitmap=bitmapDrawble.getBitmap();
                //chuyển về mảng byte
                ByteArrayOutputStream byteArray=new ByteArrayOutputStream();
                //định lại dữ liệu
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                //chuyển về mảng Byte
                byte[] hinhAnh=byteArray.toByteArray();

                Intent intent= new Intent(User_TTin_Edit.this, User_TTin_Edit.class);
                startActivityForResult(intent, 100);
            }
        });
    }
    private void show() {
        imgTTin= (ImageButton) findViewById(R.id.imgTTin);
        btnSua=(Button) findViewById(R.id.btnOK);
        btnBack=(Button) findViewById(R.id.btnBack);
        edtName= (EditText) findViewById(R.id.edtName);
        edtPhone= (EditText) findViewById(R.id.edtPhone);
        edtAddress= (EditText) findViewById(R.id.edtAddress);
        edtGmail= (EditText) findViewById(R.id.edtGmail);

    }
}
