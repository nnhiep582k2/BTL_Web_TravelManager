package com.nnhiep.travelmanager.views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.nnhiep.travelmanager.R;
import com.nnhiep.travelmanager.databinding.ActivityLoginBinding;

/**
 * Trang đăng nhập
 * @author nnhiep 18.03.2023
 */
public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    Button btnSignin;
    EditText eTxtAccount, eTxtPassword;
    TextView txtErrorAccount, txtErrorPassword, txtForgotPassword, txtChangeSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Gán giá trị cho đối tượng view binding - nnhiep 18.03.2023
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        // Thiết lập layout cho activity - nnhiep 18.03.2023
        setContentView(binding.getRoot());

        setup_ui();

        // Xử lý sự kiện click đăng nhập - nnhiep 18.03.2023
        btnSignin.setOnClickListener(v -> {
            if(validate()) {
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
            Toast.makeText(this, this.getResources().getString(R.string.register_success), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Hàm khởi tạo các biến
     * @author nnhiep 18.03.2023
     */
    private void setup_ui() {
        btnSignin = binding.btnSignin;
        eTxtAccount = binding.eTxtAccount;
        eTxtPassword = binding.eTxtPassword;
        txtErrorAccount = binding.txtErrorAccount;
        txtErrorPassword = binding.txtErrorPassword;
        txtForgotPassword = binding.txtForgotPassword;
        txtChangeSignup = binding.txtChangeSignup;
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
        if(eTxtAccount.getText().toString().isEmpty()) {
            txtErrorAccount.setText(this.getResources().getString(R.string.required));
            return false;
        }
        if(eTxtAccount.getText().toString().length() < 5) {
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
        if(eTxtPassword.getText().toString().isEmpty()) {
            txtErrorPassword.setText(this.getResources().getString(R.string.required));
            return false;
        }
        if(!eTxtPassword.getText().toString().equals("123")) {
            txtErrorPassword.setText(this.getResources().getString(R.string.confirm_password_invalid));
            return false;
        }
        txtErrorPassword.setText("");
        return true;
    }
    // endregion
}