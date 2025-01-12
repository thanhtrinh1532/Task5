package com.example.sqlite;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        DatabaseHandler databaseHandler = new DatabaseHandler(this);

        databaseHandler.addStudent("Trần Quốc Toàn", "Bình Định", "0363565315");
        databaseHandler.addStudent("Thanh Trình", "Hue", "0363565315");

        // Lấy danh sách sinh viên
        List<String> students = databaseHandler.getAllStudents();
        for (String student : students) {
            Log.d("Student", student); // Hiển thị thông tin sinh viên trong Logcat
        }
    }
}