package com.example.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.sqlite.DatabaseHelper;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);

        // Khởi tạo DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Tham chiếu đến các view
        EditText etFullName = findViewById(R.id.etFullNameRegister);
        EditText etEmail = findViewById(R.id.etEmailRegister);
        EditText etPassword = findViewById(R.id.etPasswordRegister);
        Button btnRegister = findViewById(R.id.btnRegister);

        // Xử lý sự kiện khi nhấn nút đăng ký
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = etFullName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // Kiểm tra dữ liệu nhập vào
                if (fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Register.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Kiểm tra email đã tồn tại chưa
                if (dbHelper.isEmailExists(email)) {
                    Toast.makeText(Register.this, "Email already exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Thêm người dùng mới
                boolean isInserted = dbHelper.insertUser(fullName, email, password);
                if (isInserted) {
                    // Hiển thị thông báo thành công
                    Toast.makeText(Register.this, "Registration successful!", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(Register.this, Login.class));
                    finish();
                } else {
                    // Hiển thị thông báo thất bại
                    Toast.makeText(Register.this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
