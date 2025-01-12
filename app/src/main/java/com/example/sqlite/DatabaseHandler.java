package com.example.sqlite;

import static android.provider.Contacts.SettingsColumns.KEY;
import static java.sql.Types.INTEGER;
import static java.text.Collator.PRIMARY;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "schoolManager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "students";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_PHONE_NUMBER = "phone_number";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Tạo bảng khi database được khởi tạo
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " TEXT, "
                + KEY_ADDRESS + " TEXT, "
                + KEY_PHONE_NUMBER + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }
    // Xử lý khi database được nâng cấp
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME); // Xóa bảng nếu tồn tại
        onCreate(db); // Tạo lại bảng
    }

    // Thêm sinh viên vào bảng
    public void addStudent(String name, String address, String phoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO " + TABLE_NAME + " ("
                + KEY_NAME + ", "
                + KEY_ADDRESS + ", "
                + KEY_PHONE_NUMBER + ") VALUES (?, ?, ?)";
        db.execSQL(query, new String[]{name, address, phoneNumber});

        db.close();
    }

    // Xóa sinh viên theo ID
    public void deleteStudent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + KEY_ID + " = ?";
        db.execSQL(query, new String[]{String.valueOf(id)});

        db.close();
    }

    // Lấy danh sách tất cả sinh viên
    public List<String> getAllStudents() {
        List<String> studentList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String student = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID)) + " - "
                        + cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME)) + " - "
                        + cursor.getString(cursor.getColumnIndexOrThrow(KEY_ADDRESS)) + " - "
                        + cursor.getString(cursor.getColumnIndexOrThrow(KEY_PHONE_NUMBER));
                studentList.add(student);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return studentList;
    }

}
