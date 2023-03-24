package com.nnhiep.travelmanager.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.adapters.TourAdapter;
import com.nnhiep.travelmanager.database.Tour_database;
import com.nnhiep.travelmanager.models.DSTour;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class Search extends AppCompatActivity  implements SearchView.OnQueryTextListener {
    TextView et_title,et_price,et_star,et_date;
    Tour_database db;
    ImageView img_Tour;
    ListView lst;
    ArrayList<DSTour> arrayList;
    ArrayList<DSTour> searchList;
    Button btnSx;


    TourAdapter adapter;
    int Tid;
    DSTour dsTour;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar=findViewById(R.id.tbar);
        lst=findViewById(R.id.lstTour);
        btnSx=findViewById(R.id.sapxep_tour);
        setSupportActionBar(toolbar);
        db=new Tour_database(this);

        showTourdata();
        //nhận sự kiện của setchoice
        lst.setMultiChoiceModeListener(modeListener);
        //Danh sách cho phép nhiều lựa chọn trong chế độ lựa chọn phương thức
        lst.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        Button btnRegister=findViewById(R.id.register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Search.this,RegisterTour.class);
                startActivity(intent);
            }
        });
        btnSx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SortDes();
            }
        });


    }

    private void showTourdata() {
        arrayList=db.getTourData();
        adapter=new TourAdapter(this,arrayList);
        lst.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.insert_menu,menu);
        MenuItem search=menu.findItem(R.id.search);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(search);
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getTitle().equals("New"))
        {
             LayoutInflater inflater=(LayoutInflater) Search.this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.activity_add_tour,null);

         et_title=view.findViewById(R.id.Name_tour_detail);
         et_price=view.findViewById(R.id.Fee_Tour_detail);
         et_star=view.findViewById(R.id.et_star_detail);
         et_date=view.findViewById(R.id.d_detail);

         img_Tour=view.findViewById(R.id.img_tour);




        img_Tour.setOnClickListener(new View.OnClickListener() {

                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(Intent.ACTION_PICK);
                                            intent.setType("image/*");
                                            startActivityForResult(intent, 200);
                                        }
                                    });
        AlertDialog.Builder builder=new AlertDialog.Builder(Search.this);
        builder.setView(view)
                .setTitle("Adding new Tour")
                .setMessage("Enter Tour information")
                .setIcon(R.drawable.person_add)
                .setPositiveButton("Add new Tour ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String title=et_title.getText().toString();
                        String price=et_price.getText().toString();
                        String star= et_star.getText().toString();
                        String date=et_date.getText().toString();
;                        boolean res=db.insertTour(title,price,star,date,ImageTobyte(img_Tour));
                        if(res==true)
                        {
                            showTourdata();
                            Toast.makeText(Search.this, "New Tour added", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Search.this, "New Tour not added", Toast.LENGTH_SHORT).show();
                        }



                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();



        }
        else {

        }
        LayoutInflater inflater=(LayoutInflater) Search.this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.activity_add_tour,null);

         et_title=view.findViewById(R.id.Name_tour_detail);
         et_price=view.findViewById(R.id.Fee_Tour_detail);
         et_star=view.findViewById(R.id.et_star_detail);
         et_date=view.findViewById(R.id.d_detail);

         img_Tour=view.findViewById(R.id.img_tour);




        img_Tour.setOnClickListener(new View.OnClickListener() {

                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(Intent.ACTION_PICK);
                                            intent.setType("image/*");
                                            startActivityForResult(intent, 200);
                                        }
                                    });
        AlertDialog.Builder builder=new AlertDialog.Builder(Search.this);
        builder.setView(view)
                .setTitle("Adding new Tour")
                .setMessage("Enter Tour information")
                .setIcon(R.drawable.person_add)
                .setPositiveButton("Add new Tour ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String title=et_title.getText().toString();
                        String price=et_price.getText().toString();
                        String star= et_star.getText().toString();
                        String date=et_date.getText().toString();
;                        boolean res=db.insertTour(title,price,star,date,ImageTobyte(img_Tour));
                        if(res==true)
                        {
                            showTourdata();
                            Toast.makeText(Search.this, "New Tour added", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Search.this, "New Tour not added", Toast.LENGTH_SHORT).show();
                        }



                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();


        return super.onOptionsItemSelected(item);
    }
    //chuyển ảnh về dạng byte
    private byte[] ImageTobyte(ImageView img_tour) {
        //chuyển ảnh về bit
        Bitmap bitmap=((BitmapDrawable) img_tour.getDrawable()).getBitmap();
        //khai báo mảng
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        //nén file
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        //chuyển mảng về byte
        byte[] bytes=stream.toByteArray();
        return bytes;
    }

    //đây là kq khi lấy ảnh từ gellary (không cần lắm)
    @Override
     public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 &&   resultCode==RESULT_OK && data !=null) {
            Uri uri=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(uri);
                Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
                img_Tour.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                throw new RuntimeException();
            }


        }
    }
   // Đặt một MultiChoiceModeListenercái sẽ quản lý vòng đời của lựa chọn ActionMode
    AbsListView.MultiChoiceModeListener modeListener=new AbsListView.MultiChoiceModeListener() {
        @Override
        //Khi người dùng nhấn chọn một item nào đó thì nó sẽ arraylist sẽ lấy vị tri của phần tử đó,rôì lấy id
        public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
            dsTour=arrayList.get(position);
           Tid= dsTour.getId();

        }
        //phương thức khởi tạo
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            getMenuInflater().inflate(R.menu.abs_menu,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }
        //khi người dùng nhấp vào item
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            //kiểm tra title có trùng với title mà mình đã set trong abs_menu chưa(tương tự như id)
            if (item.getTitle().equals("register")) {
                //nếu trùng rồi thì điều hướng tới một layout add tour
                LayoutInflater inflater = (LayoutInflater) Search.this.getSystemService(LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.register_detail, null);

               //ánh xạ
                et_title = view.findViewById(R.id.Title_register_detail);
                et_price = view.findViewById(R.id.Fee_register_detail);
                et_star = view.findViewById(R.id.star_register_detail);
                et_date = view.findViewById(R.id.d_register_detail);
                img_Tour = view.findViewById(R.id.img_tour_register);

                //lấy ra thông tin cũ
                String oldtitle = dsTour.getTitle();
                String oldprice = dsTour.getPrice();
                String oldstar = dsTour.getStar();
                String olddate = dsTour.getDate();
                byte[] oldimage = dsTour.getImage();
                //set dữ liệu
                et_title.setText(oldprice);
                et_price.setText(oldtitle);
                et_star.setText(oldstar);
                et_date.setText(olddate);
                //chuyen byte ve bitmap
                Bitmap bitmap = BitmapFactory.decodeByteArray(oldimage, 0, oldimage.length);
                img_Tour.setImageBitmap(bitmap);

                 // bắt sự kiện khi click vào ảnh (khong cần lắm)
                img_Tour.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent, 200);
                    }
                });
                //tạo một dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(Search.this);
                builder.setView(view)
                        .setTitle("Register  Tour ")
                        .setMessage("Tour information")
                        .setIcon(R.drawable.baseline_playlist_add_24)
                        //sự kiện khi nhấn vào register
                        .setPositiveButton("Register Tour ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //lấy giá trị ra
                                String title = et_title.getText().toString();
                                String price = et_price.getText().toString();
                                String star = et_star.getText().toString();
                                String date = et_date.getText().toString();
                                //biến res là kq khi thêm vào đăng kí tour
                                boolean res = db.insertTourRegister(Tid, title, price, star, date, ImageTobyte(img_Tour));
                                //Khi đã thêm thành công
                                if (res == true) {
                                    db.deleteTour(String.valueOf(Tid));
                                    showTourdata();
                                    Toast.makeText(Search.this, "successfull", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Search.this, "failed", Toast.LENGTH_SHORT).show();
                                }


                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.create().show();
            }
            return true;
        }




        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    };


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    //Được gọi khi văn bản truy vấn được thay đổi bởi người dùng
    @Override
    //tìm kiếm theo ngày,tháng,năm
    public boolean onQueryTextChange(String newText) {
        //khởi tạo một mảng để lưu những kq tìm kiếm
        searchList=new ArrayList<>();

        for(DSTour dsTour1:arrayList)
        {
            String title=dsTour1.getDate();
            if(title.contains(newText))
            {
                searchList.add(dsTour1);
            }
        }
        adapter.searchFilter(searchList);
        return true;
    }
    //sắp xếp theo ngày tháng từ gần tới xa của Tour
      private void SortDes ()
        {
            Collections.sort(arrayList, new DSTour.PriceDes());
            adapter.notifyDataSetChanged();
        }

}