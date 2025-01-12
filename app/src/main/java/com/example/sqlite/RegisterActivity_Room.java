package com.example.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.sqlite.data.User;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlite.data.AppDatabase;

public class RegisterActivity_Room extends AppCompatActivity {

    private EditText etFullName, etEmail, etPassword;
    private Button btnRegister;
    private TextView tvLoginInstead;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Khởi tạo Database Room
        db = AppDatabase.getInstance(this);

        // Ánh xạ các view
        etFullName = findViewById(R.id.etFullNameRegister);
        etEmail = findViewById(R.id.etEmailRegister);
        etPassword = findViewById(R.id.etPasswordRegister);
        btnRegister = findViewById(R.id.btnRegister);
        tvLoginInstead = findViewById(R.id.tvLoginInstead);

        // Sự kiện khi nhấn nút đăng ký
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        // Sự kiện khi nhấn vào "Already have an account? Login"
        tvLoginInstead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến màn hình đăng nhập
                startActivity(new Intent(RegisterActivity_Room.this, LoginActivity_Room.class));
                finish(); // Kết thúc màn hình đăng ký
            }
        });
    }

    // Xử lý đăng ký người dùng
    private void registerUser() {
        String fullName = etFullName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Kiểm tra xem các trường có trống không
        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra nếu email đã tồn tại
        User existingUser = db.userDao().getUserByEmail(email);
        if (existingUser != null) {
            Toast.makeText(this, "Email already exists", Toast.LENGTH_SHORT).show();
            return;
        }

        // Thêm người dùng mới vào cơ sở dữ liệu
        User newUser = new User(fullName, email, password);
        long userId = db.userDao().insertUser(newUser);

        if (userId != -1) {
            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
            finish(); // Quay lại màn hình đăng nhập
        } else {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
        }
    }
}
