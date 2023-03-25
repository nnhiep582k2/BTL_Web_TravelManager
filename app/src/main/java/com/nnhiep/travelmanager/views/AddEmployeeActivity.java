package com.nnhiep.travelmanager.views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.database.Database;
import com.nnhiep.travelmanager.databinding.ActivityAddEmployeeBinding;
import com.nnhiep.travelmanager.fragments.EmployeeFragment;
import com.nnhiep.travelmanager.models.Employee;
import java.util.ArrayList;
import java.util.List;

public class AddEmployeeActivity extends AppCompatActivity {
    private ActivityAddEmployeeBinding binding;
    private Database db;
    Bitmap imgStore = null;
    private Uri imgPath;
    List<Employee> listEmployees;
    Employee employee;
    de.hdodenhof.circleimageview.CircleImageView imgEmployeeAvatar;
    Spinner spinnerGender;
    TextView txtChangeAvatar, txtErrorAvatar, txtErrorName, txtErrorAge, txtErrorPhone, txtErrorGmail;
    Button btnCancel, btnEdit;
    EditText eTextEChangeName, eTextEChangeAge, eTextEChangePhone, eTextEChangeGmail;
    int choseImage;
    boolean hasImage = false;
    String mode = "Add";
    private static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Gán giá trị cho đối tượng view binding - nnhiep 17.03.2023
        binding = ActivityAddEmployeeBinding.inflate(getLayoutInflater());
        // Thiết lập layout cho activity - nnhiep 17.03.2023
        setContentView(binding.getRoot());
        db = new Database(this);

        setupUI();
        setupSpinner();
        getDataFromOtherActivity();

        // Xử lý sự kiện click thêm - nnhiep 18.03.2023
        btnEdit.setOnClickListener(v -> {
            if(validate()) {
                Intent intent = new Intent(AddEmployeeActivity.this, EmployeeFragment.class);
                if(imgStore == null && !hasImage) {
                    imgStore = BitmapFactory.decodeResource(getResources(), choseImage);
                } else {
                    imgStore = employee.getAvatar();
                }
                if(mode.equals("Add")) db.insertAnEmployee(employee.getName(), employee.getAge(), employee.getGender(), employee.getPhone(), employee.getGmail(), imgStore);
                else db.updateAnEmployee(employee.getId(), employee.getName(), employee.getAge(), employee.getGender(), employee.getPhone(), employee.getGmail(), imgStore);
                setResult(150, intent);
                finish();
            }
        });

        // Xử lý click hủy - nnhiep 20.03.2023
        btnCancel.setOnClickListener(v -> {
            Intent intent = new Intent(AddEmployeeActivity.this, EmployeeFragment.class);
            setResult(100, intent);
            finish();
        });

        // Click đổi ảnh đại diện - nnhiep 20.03.2023
        txtChangeAvatar.setOnClickListener(v -> {
            Intent gallery = new Intent();
            gallery.setType("image/*");
            gallery.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(gallery, "Avatar"), PICK_IMAGE);
        });

        // Xử lý sự kiện blur ra khỏi input tên - nnhiep 24.03.2023
        eTextEChangeName.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus) {
                validateName();
            }
        });

        // Xử lý sự kiện blur ra khỏi input tuổi - nnhiep 24.03.2023
        eTextEChangeAge.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus) {
                validateAge();
            }
        });

        // Xử lý sự kiện blur ra khỏi input số điện thoại - nnhiep 24.03.2023
        eTextEChangePhone.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus) {
                validatePhone();
            }
        });

        // Xử lý sự kiện blur ra khỏi input gmail - nnhiep 24.03.2023
        eTextEChangeGmail.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus) {
                validateGmail();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() != null) {
                imgPath = data.getData();
                imgStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imgPath);
                binding.imgEmployeeAvatar.setImageBitmap(imgStore);
            }
        } catch(Exception ex) {
            Toast.makeText(this, "" + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Hàm khởi tạo giao diện
     * @author nnhiep 24.03.2023
     */
    private void setupUI() {
        spinnerGender = binding.spinnerGender;
        txtChangeAvatar = binding.txtChangeAvatar;
        btnCancel = binding.btnCancel;
        btnEdit = binding.btnEdit;
        eTextEChangeName = binding.eTextEChangeName;
        eTextEChangeAge = binding.eTextEChangeAge;
        eTextEChangePhone = binding.eTextEChangePhone;
        eTextEChangeGmail = binding.eTextEChangeGmail;
        imgEmployeeAvatar = binding.imgEmployeeAvatar;
        txtErrorAvatar = binding.txtErrorAvatar;
        txtErrorName = binding.txtErrorName;
        txtErrorAge = binding.txtErrorAge;
        txtErrorPhone = binding.txtErrorPhone;
        txtErrorGmail = binding.txtErrorGmail;
        listEmployees = new ArrayList<>();
    }

    /**
     * Hàm khởi tạo mảng dropdown giới tính
     * @author nnhiep 24.03.2023
     */
    private void setupSpinner() {
        ArrayList<String> arrayGender = new ArrayList<>();
        arrayGender.add("Female");
        arrayGender.add("Male");
        arrayGender.add("Other");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrayGender);
        spinnerGender.setAdapter(adapter);

        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        if(!hasImage) {
                            choseImage = R.drawable.employee_female;
                            imgEmployeeAvatar.setImageResource(R.drawable.employee_female);
                        }
                        break;
                    case 1:
                        if(!hasImage) {
                            choseImage = R.drawable.employee_male;
                            imgEmployeeAvatar.setImageResource(R.drawable.employee_male);
                        }
                        break;
                    case 2:
                        if(!hasImage) {
                            choseImage = R.drawable.unknown_avatar;
                            imgEmployeeAvatar.setImageResource(R.drawable.unknown_avatar);
                        }
                        break;
                }
                employee.setGender(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * Hàm lấy dữ liệu nhân viên
     * @author nnhiep 24.03.2023
     */
    public void getDataFromOtherActivity() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("ID");
        if(id != null) {
            mode = "Edit";
            hasImage = true;
            employee = db.getDataEmployeeByID(id);

            eTextEChangeName.setText(employee.getName());
            eTextEChangeAge.setText(employee.getAge() + "");
            eTextEChangePhone.setText(employee.getPhone());
            eTextEChangeGmail.setText(employee.getGmail());
            spinnerGender.setSelection(employee.getGender());
            imgEmployeeAvatar.setImageBitmap(employee.getAvatar());
        } else {
            mode = "Add";
            employee = new Employee();
        }
    }

    // region validate
    /**
     * Hàm kiểm tra dữ liệu đầu vào
     * @author nnhiep 24.03.2023
     */
    private boolean validate() {
        boolean isValid = validateName();
        if(!isValid) {
            validateAge();
            validatePhone();
            validateGmail();
            return false;
        }
        isValid = validateAge();
        if(!isValid) {
            validatePhone();
            validateGmail();
            return false;
        }
        isValid = validatePhone();
        if(!isValid) {
            validateGmail();
            return false;
        }
        isValid = validateGmail();
        return isValid;
    }

    /**
     * Kiểm tra tên
     * @author nnhiep 24.04.2023
     */
    private boolean validateName() {
        String regName = "^[a-zA-Z\\s]+";
        if(eTextEChangeName.getText().toString().trim().isEmpty()) {
            txtErrorName.setText(this.getResources().getString(R.string.required));
            return false;
        }
        if(!eTextEChangeName.getText().toString().trim().matches(regName)) {
            txtErrorName.setText(this.getResources().getString(R.string.name_invalid));
            return false;
        }
        txtErrorName.setText("");
        employee.setName(eTextEChangeName.getText().toString().trim());
        return true;
    }

    /**
     * Kiểm tra tuổi
     * @author nnhiep 24.04.2023
     */
    private boolean validateAge() {
        if(eTextEChangeAge.getText().toString().trim().isEmpty()) {
            txtErrorAge.setText(this.getResources().getString(R.string.required));
            return false;
        }
        int temp = Integer.parseInt(eTextEChangeAge.getText().toString().trim());
        if(temp < 18 || temp > 70) {
            txtErrorAge.setText(this.getResources().getString(R.string.age_invalid));
            return false;
        }
        txtErrorAge.setText("");
        employee.setAge(Integer.parseInt(eTextEChangeAge.getText().toString().trim()));
        return true;
    }

    /**
     * Kiểm tra số điện thoại
     * @author nnhiep 24.04.2023
     */
    private boolean validatePhone() {
        String regPhone = "^09\\d{8}$";
        if(eTextEChangePhone.getText().toString().trim().isEmpty()) {
            txtErrorPhone.setText(this.getResources().getString(R.string.required));
            return false;
        }
        if(!eTextEChangePhone.getText().toString().trim().matches(regPhone)) {
            txtErrorPhone.setText(this.getResources().getString(R.string.phone_invalid));
            return false;
        }
        txtErrorPhone.setText("");
        employee.setPhone(eTextEChangePhone.getText().toString().trim());
        return true;
    }

    /**
     * Kiểm tra số gmail
     * @author nnhiep 24.04.2023
     */
    private boolean validateGmail() {
        String regMail = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
        if(!eTextEChangeGmail.getText().toString().trim().isEmpty() && !eTextEChangeGmail.getText().toString().trim().matches(regMail)) {
            txtErrorGmail.setText(this.getResources().getString(R.string.gmail_invalid));
            return false;
        }
        txtErrorGmail.setText("");
        employee.setGmail(eTextEChangeGmail.getText().toString().trim());
        return true;
    }
    // endregion
}