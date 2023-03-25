package com.nnhiep.travelmanager.views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.database.Database;
import com.nnhiep.travelmanager.databinding.ActivityLoginBinding;
import java.util.ArrayList;

/**
 * Trang đăng nhập
 * @author nnhiep 18.03.2023
 */
public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private Database db;
    private ArrayList<String> user_id,user_name, user_account, user_password, user_phone;
    private ArrayList<Number> user_age, user_gender;
    private ArrayList<byte[]> user_avatar;
    private Button btnSignin;
    private EditText eTxtAccount, eTxtPassword;
    private TextView txtErrorAccount, txtErrorPassword, txtChangeSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Gán giá trị cho đối tượng view binding - nnhiep 18.03.2023
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        // Thiết lập layout cho activity - nnhiep 18.03.2023
        setContentView(binding.getRoot());
        db = new Database(this);

        checkUserExist();

        setup_ui();

        // Xử lý sự kiện click đăng nhập - nnhiep 18.03.2023
        btnSignin.setOnClickListener(v -> {
            if(validate()) {
                db.updateDataSystem("1", true);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Xử lý sự kiện click chuyển sang màn đăng ký - nnhiep 18.03.2023
        txtChangeSignup.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivityForResult(intent, 200);
        });

        // Xử lý sự kiện blur ra khỏi input tài khoản - nnhiep 18.03.2023
        eTxtAccount.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus) {
                validateAccount();
            }
        });

        // Xử lý sự kiện blur ra khỏi input mật khẩu - nnhiep 18.03.2023
        eTxtPassword.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus) {
                validatePassword();
            }
        });
    }

    /**
     * Reset các giá trị khi back lại màn
     * @author nnhiep 18.03.2023
     */
    @Override
    protected void onResume() {
        super.onResume();
        eTxtAccount.requestFocus();
        eTxtAccount.setText("");
        eTxtPassword.setText("");
        txtErrorAccount.setText("");
        txtErrorPassword.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200 && resultCode == 150) {
            getDataUser();
            Toast.makeText(this, this.getResources().getString(R.string.register_success), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Hàm khởi tạo các biến
     * @author nnhiep 18.03.2023
     */
    private void setup_ui() {
        user_account = new ArrayList<>();
        user_password = new ArrayList<>();
        user_name = new ArrayList<>();
        user_age = new ArrayList<>();
        user_gender = new ArrayList<>();
        user_phone = new ArrayList<>();
        user_avatar = new ArrayList<>();
        btnSignin = binding.btnSignin;
        eTxtAccount = binding.eTxtAccount;
        eTxtPassword = binding.eTxtPassword;
        txtErrorAccount = binding.txtErrorAccount;
        txtErrorPassword = binding.txtErrorPassword;
        txtChangeSignup = binding.txtChangeSignup;
        getDataUser();
        eTxtAccount.requestFocus();
    }

    /**
     * Lấy thông tin người dùng
     * @author nnhiep 19.03.2023
     */
    private void getDataUser() {
        try {
            Cursor cursor = db.getDataUser();
            if(cursor.getCount() == 0) {
                Toast.makeText(this, this.getResources().getString(R.string.user_no_data), Toast.LENGTH_SHORT).show();
            } else {
                cursor.moveToFirst();
                user_id.add(cursor.getString(0));
                user_name.add(cursor.getString(1));
                user_age.add(cursor.getInt(2));
                user_gender.add(cursor.getInt(3));
                user_account.add(cursor.getString(4));
                user_password.add(cursor.getString(5));
                user_phone.add(cursor.getString(6));
                user_avatar.add(cursor.getBlob(7));
                cursor.close();
            }
        } catch (Exception e) {
            Toast.makeText(this, this.getResources().getString(R.string.error_occur), Toast.LENGTH_SHORT).show();
            Log.d("Error DB", e.getMessage());
        }
    }

    // region validate
    /**
     * Hàm kiểm tra xem người dùng đã đăng nhập chưa
     * @author nnhiep 19.03.2023
     */
    private void checkUserExist() {
        try {
            Cursor cursor = db.getDataSystem();
            if(cursor.getCount() == 0) {
                db.insertDataSystem(false);
            } else {
                cursor.moveToFirst();
                int position = cursor.getColumnIndex("system_is_login");
                if(position > -1 && cursor.getInt(position) != 0) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, this.getResources().getString(R.string.error_occur), Toast.LENGTH_SHORT).show();
            Log.d("Error DB", e.getMessage());
        }
    }

    /**
     * Hàm kiểm tra dữ liệu đầu vào
     * @author nnhiep 18.03.2023
     */
    private boolean validate() {
        boolean isValid = validateAccount();
        if(!isValid) {
            validatePassword();
            return false;
        }
        isValid = validatePassword();
        return isValid;
    }

    /**
     * Hàm kiểm tra tài khoản
     * @author nnhiep 18.03.2023
     */
    private boolean validateAccount() {
        if(eTxtAccount.getText().toString().trim().isEmpty()) {
            txtErrorAccount.setText(this.getResources().getString(R.string.required));
            return false;
        }
        if(user_account.size() > 0) {
            if(!eTxtAccount.getText().toString().trim().equals(user_account.get(0))) {
                txtErrorAccount.setText(this.getResources().getString(R.string.account_invalid));
                return false;
            }
        } else {
            txtErrorAccount.setText(this.getResources().getString(R.string.account_invalid));
            return false;
        }
        txtErrorAccount.setText("");
        return true;
    }

    /**
     * Hàm kiểm tra mật khẩu
     * @author nnhiep 18.03.2023
     */
    private boolean validatePassword() {
        if(eTxtPassword.getText().toString().trim().isEmpty()) {
            txtErrorPassword.setText(this.getResources().getString(R.string.required));
            return false;
        }
        if(user_password.size() > 0) {
            if(!eTxtPassword.getText().toString().trim().equals(user_password.get(0))) {
                txtErrorPassword.setText(this.getResources().getString(R.string.confirm_password_invalid));
                return false;
            }
        } else {
            txtErrorPassword.setText(this.getResources().getString(R.string.password_invalid));
            return false;
        }
        txtErrorPassword.setText("");
        return true;
    }
    // endregion
}