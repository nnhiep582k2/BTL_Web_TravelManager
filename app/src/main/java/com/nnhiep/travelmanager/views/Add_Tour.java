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
import com.nnhiep.travelmanager.database.Tour_database;
import com.nnhiep.travelmanager.fragments.TourFragment;
import com.nnhiep.travelmanager.models.DSTour;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Add_Tour extends AppCompatActivity {
    Button btnAdd_tour,btnEdit_tour;
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

        //nhận dữ liệu từ intent khác gửi sang
        Intent intent = getIntent();
        //lay bundle
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            int id = bundle.getInt("Id");
            String image = bundle.getString("Image");
            String name = bundle.getString("Name");
            String phone = bundle.getString("Phone");
            IdTour.setText(String.valueOf(id));
            NameTour.setText(name);
            FeeTour.setText(phone);
            btnAdd_tour.setText("Edit");


        }


        //bắt sự kiện khi click vào camera  -ltphuong 18/03/2023
        ImgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, request_code_camera);
            }
            //đổ dữ liệu ra imageview

        });


        //bắt sự kiện khi click vào folder -ltphuong 18/03/2023
        ImgFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, request_code_folder);

            }
        });
    }}

//        btnEdit_tour.setOnClickListener(new View.OnClickListener() {
//                                            @Override
//                                            public void onClick(View v) {
//                                                int id = Integer.parseInt(IdTour.getText().toString());
//                                                String title = NameTour.getText().toString();
//                                                String price = FeeTour.getText().toString();
//                                                BitmapDrawable bitmapDrawable = (BitmapDrawable) img_Tour.getDrawable();
//                                                Bitmap bitmap = bitmapDrawable.getBitmap();
//                                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
//                                                byte[] hinhAnh = byteArrayOutputStream.toByteArray();
//                                            })
//                Intent intent=new Intent();
//                Bundle b=new Bundle();
//               b.putInt("Id",id);
//                b.putString("Title",title);
//               b.putString("Price",price);
//               b.putByteArray("Image",hinhAnh);
//               intent.putExtras(b);
//                setResult(150,intent);
//               finish();
//                                            }
//                                        });


                //bắt sự kiện khi nhấn nút Add

//        btnAdd_tour.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //chuyen data imageview _>byte[]
//                BitmapDrawable bitmapDrawable= (BitmapDrawable) img_Tour.getDrawable();
//                Bitmap bitmap = bitmapDrawable.getBitmap();
//                ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream) ;
//                byte[] hinhAnh =byteArrayOutputStream.toByteArray();
//                Search.database.insertTour(
//                        IdTour.getText().toString().trim(),
//                        NameTour.getText().toString().trim(),
//                        FeeTour.getText().toString().trim(),
//                        hinhAnh
//
//
//
////                );
//                Toast.makeText(Add_Tour.this, "Đã thêm", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(Add_Tour.this,Search.class));
//            }
//        });
//
//
//}

//    private void Anhxa() {
//            btnAdd_tour=(Button) findViewById(R.id.btnAdd_Tour);
//            btnEdit_tour=(Button) findViewById(R.id.btnCancel);
//            IdTour= (EditText) findViewById(R.id.Id_tour);
//            NameTour= (EditText) findViewById(R.id.Name_tour);
//            FeeTour= (EditText) findViewById(R.id.Fee_Tour);
//            img_Tour= (ImageView) findViewById(R.id.img_tour);
//            ImgCamera= (ImageButton) findViewById(R.id.img_camera);
//            ImgFolder=(ImageButton) findViewById(R.id.img_folder);
//    }
