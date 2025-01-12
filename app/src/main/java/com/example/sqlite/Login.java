package com.example.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private EditText etFullName, etPassword;
    private Button btnLogin;
    private CheckBox cbRememberMe;
    private TextView tvSignUp, tvForgotPassword;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);

        // Khởi tạo DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Ánh xạ các thành phần giao diện
        etFullName = findViewById(R.id.etFullName);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        cbRememberMe = findViewById(R.id.cbRememberMe);
        tvSignUp = findViewById(R.id.tvSignUp);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        // Xử lý sự kiện
        handleEvents();
    }

    private void handleEvents() {
        // Sự kiện khi nhấn nút Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        // Sự kiện chuyển sang màn hình Sign Up
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang Activity Đăng ký
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        // Sự kiện Forgot Password (có thể triển khai sau)
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Login.this, "Feature not implemented yet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loginUser() {
        String fullName = etFullName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Kiểm tra xem các trường có trống không
        if (fullName.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra thông tin đăng nhập trong cơ sở dữ liệu
        boolean isValid = dbHelper.checkLogin(fullName, password);
        if (isValid) {
            // Đăng nhập thành công
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();

//             Chuyển sang màn hình chính
             startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        } else {
            // Đăng nhập thất bại
            Toast.makeText(this, "Invalid Full Name or Password", Toast.LENGTH_SHORT).show();
        }
    }

}
