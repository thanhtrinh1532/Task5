package com.example.sqlite.data;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    // Trả về DAO để thực hiện các thao tác với dữ liệu
    public abstract UserDao userDao();

    private static AppDatabase INSTANCE;

    // Singleton Pattern để đảm bảo chỉ tạo một instance của database
    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "user_database")
                    .allowMainThreadQueries() // Cảnh báo: Không nên sử dụng trong production, tốt hơn là dùng background thread
                    .build();
        }
        return INSTANCE;
    }
}

