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

import com.example.sqlite.data.AppDatabase;
import com.example.sqlite.data.User;


public class LoginActivity_Room extends AppCompatActivity {

    private EditText etFullName, etPassword;
    private Button btnLogin;
    private CheckBox cbRememberMe;
    private TextView tvSignUp, tvForgotPassword;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Khởi tạo Room Database
        db = AppDatabase.getInstance(this);

        // Ánh xạ các view
        etFullName = findViewById(R.id.etFullName);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        cbRememberMe = findViewById(R.id.cbRememberMe);
        tvSignUp = findViewById(R.id.tvSignUp);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        // Xử lý sự kiện khi nhấn nút Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        // Sự kiện khi nhấn vào "Don't have an account? Sign up"
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến màn hình đăng ký
                startActivity(new Intent(LoginActivity_Room.this, RegisterActivity_Room.class));
                finish(); // Kết thúc màn hình đăng nhập
            }
        });

        // Sự kiện khi nhấn vào "Forgot Password"
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tính năng quên mật khẩu (có thể triển khai sau)
                Toast.makeText(LoginActivity_Room.this, "Feature not implemented yet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Xử lý đăng nhập
    private void loginUser() {
        String fullName = etFullName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Kiểm tra xem các trường có trống không
        if (fullName.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra thông tin đăng nhập trong cơ sở dữ liệu
        User user = db.userDao().loginUser(fullName, password);
        if (user != null) {
            // Đăng nhập thành công
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();

            // Chuyển đến màn hình chính (Home Activity)
            startActivity(new Intent(LoginActivity_Room.this, MainActivity.class));
            finish(); // Kết thúc màn hình đăng nhập
        } else {
            // Đăng nhập thất bại
            Toast.makeText(this, "Invalid Full Name or Password", Toast.LENGTH_SHORT).show();
        }
    }
}
