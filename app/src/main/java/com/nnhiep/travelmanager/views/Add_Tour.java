package com.nnhiep.travelmanager.views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.fragments.TourFragment;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Add_Tour extends AppCompatActivity {
    Button btnAdd_tour,btnCancel_tour;
    EditText IdTour,NameTour,FeeTour;
    ImageButton ImgCamera,ImgFolder;
    ImageView img_Tour;
    int request_code_camera=200;
    int request_code_folder=300;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //khi chụp ảnh
        if(requestCode==request_code_camera && resultCode == RESULT_OK && data !=null)
        {
            Bitmap bitmap=(Bitmap)data.getExtras().get("data");
            img_Tour.setImageBitmap(bitmap);
        }
        //khi mở folder
        if(resultCode== request_code_folder && resultCode ==RESULT_OK && data !=null)
        {
            Uri uri=data.getData();
            try {
                //mở chỗ đọc và lấy đường dẫn ảnh
                InputStream inputStream=getContentResolver().openInputStream(uri);
                //chuyển về bitmap
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                img_Tour.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tour);
        Anhxa();

        //bắt sự kiện khi click vào camera  -ltphuong 18/03/2023
        ImgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,request_code_camera);
            }
            //đổ dữ liệu ra imageview
        });

        //bắt sự kiện khi click vào folder -ltphuong 18/03/2023
        ImgFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,request_code_folder);
            }
        });

        //bắt sự kiện khi nhấn nút Add
        btnAdd_tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chuyển data của imageview sang byte[]
                BitmapDrawable bitmapDrawble=(BitmapDrawable)img_Tour.getDrawable();
                //chuyển BitmapDrwable chuyển về bitmap
                Bitmap bitmap=bitmapDrawble.getBitmap();
                //chuyển về mảng byte
                ByteArrayOutputStream byteArray=new ByteArrayOutputStream();
                //định lại dữ liệu
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                //chuyển về mảng Byte
                byte[] hinhAnh=byteArray.toByteArray();
                //Thêm Tour
//                db.insertATour(IdTour.getText().toString().trim(),NameTour.getText().toString().trim(),
//                               FeeTour.getText().toString().trim(),null,
//                        null,null,null,null,hinhAnh);
                Toast.makeText(Add_Tour.this, "Thêm Tour Thành Công ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Add_Tour.this, TourFragment.class));
            }
        });
}

    private void Anhxa() {
        btnAdd_tour=(Button) findViewById(R.id.btnAdd_Tour);
            btnCancel_tour=(Button) findViewById(R.id.btnCancel);
            IdTour= (EditText) findViewById(R.id.Id_tour);
            NameTour= (EditText) findViewById(R.id.Name_tour);
            FeeTour= (EditText) findViewById(R.id.Fee_Tour);
            img_Tour= (ImageView) findViewById(R.id.img_tour);
            ImgCamera= (ImageButton) findViewById(R.id.img_camera);
            ImgFolder=(ImageButton) findViewById(R.id.img_folder);
    }
}