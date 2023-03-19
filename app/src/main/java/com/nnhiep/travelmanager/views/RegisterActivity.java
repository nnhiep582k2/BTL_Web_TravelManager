package com.nnhiep.travelmanager.views;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.database.Database;
import com.nnhiep.travelmanager.databinding.ActivityRegisterBinding;
import com.nnhiep.travelmanager.models.Schedule;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    Database db;
    Button btnSignup;
    EditText eTxtAccount, eTxtPassword, eTxtConfirmPassword;
    TextView txtErrorAccount, txtErrorPassword, txtErrorConfirmPassword, txtChangeSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Gán giá trị cho đối tượng view binding - nnhiep 18.03.2023
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        // Thiết lập layout cho activity - nnhiep 18.03.2023
        setContentView(binding.getRoot());

        setup_ui();

        // Xử lý sự kiện click đăng ký - nnhiep 18.03.2023
        btnSignup.setOnClickListener(v -> {
            if(validate()) {
                String account = eTxtAccount.getText().toString();
                String password = eTxtPassword.getText().toString();
                db.insertAnEmployee(account, 0, 0, account, password, "", null);
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                setResult(150, intent);
                finish();
            }
        });

        // Xử lý sự kiện click chuyển sang màn đăng nhập - nnhiep 18.03.2023
        txtChangeSignin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
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

        // Xử lý sự kiện blur ra khỏi input xác nhận mật khẩu - nnhiep 18.03.2023
        eTxtConfirmPassword.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus) {
                validateConfirmPassword();
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
        eTxtConfirmPassword.setText("");
        txtErrorAccount.setText("");
        txtErrorPassword.setText("");
        txtErrorConfirmPassword.setText("");
    }

    /**
     * Hàm khởi tạo các biến
     * @author nnhiep 18.03.2023
     */
    private void setup_ui() {
        db = new Database(this);
        btnSignup = binding.btnSignup;
        eTxtAccount = binding.eTxtAccount;
        eTxtPassword = binding.eTxtPassword;
        eTxtConfirmPassword = binding.eTxtConfirmPassword;
        txtErrorAccount = binding.txtErrorAccount;
        txtErrorPassword = binding.txtErrorPassword;
        txtErrorConfirmPassword = binding.txtErrorConfirmPassword;
        txtChangeSignin = binding.txtChangeSignin;
        eTxtAccount.requestFocus();
    }

    // region validate
    /**
     * Hàm kiểm tra dữ liệu đầu vào
     * @author nnhiep 18.03.2023
     */
    private boolean validate() {
        boolean isValid = validateAccount();
        if(!isValid) {
            validatePassword();
            validateConfirmPassword();
            return false;
        }
        isValid = validatePassword();
        if(!isValid) {
            validateConfirmPassword();
            return false;
        }
        isValid = validateConfirmPassword();
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
        if(eTxtAccount.getText().toString().trim().length() < 5) {
            txtErrorAccount.setText(this.getResources().getString(R.string.account_min_length));
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
        if(eTxtPassword.getText().toString().trim().length() < 8) {
            txtErrorPassword.setText(this.getResources().getString(R.string.password_min_length));
            return false;
        }
        txtErrorPassword.setText("");
        return true;
    }

    /**
     * Hàm kiểm tra xác nhận mật khẩu
     * @author nnhiep 18.03.2023
     */
    private boolean validateConfirmPassword() {
        if(!eTxtPassword.getText().toString().trim().isEmpty() && eTxtConfirmPassword.getText().toString().trim().isEmpty()) {
            txtErrorConfirmPassword.setText(this.getResources().getString(R.string.required));
            return false;
        }
        if(!eTxtPassword.getText().toString().trim().isEmpty() && !eTxtConfirmPassword.getText().toString().trim().isEmpty() &&
                !eTxtConfirmPassword.getText().toString().trim().equals(eTxtPassword.getText().toString())) {
            txtErrorConfirmPassword.setText(this.getResources().getString(R.string.password_not_true));
            return false;
        }
        txtErrorConfirmPassword.setText("");
        return true;
    }
    // endregion
}