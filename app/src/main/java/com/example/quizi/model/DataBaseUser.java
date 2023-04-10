package com.example.quizi.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.quizi.SignUpActivity;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class DataBaseUser extends SQLiteOpenHelper {

    public static final String TABLE_USER = "TABLE_USER";
    public static final String COLUMN_EMAIL = "COLUMN_EMAIL";
    public static final String COLUMN_PASSWORD = "COLUMN_PASSWORD";
    public static final String COLUMN_CONFIRM_PASSWORD = "COLUMN_CONFIRM_PASSWORD";
    public static final String DB_USERS = "DB_USER.db";

    public DataBaseUser(@Nullable Context context) {
        super(context, DB_USERS, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableUser = "CREATE TABLE " + TABLE_USER + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_CONFIRM_PASSWORD +" TEXT)";

        db.execSQL(createTableUser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(User user){
//        init
        SQLiteDatabase db = this.getWritableDatabase();
//        obj send data
        ContentValues cv = new ContentValues();
//        put key, value
            cv.put(COLUMN_EMAIL , user.getEmail());
            cv.put(COLUMN_PASSWORD , user.getPassword());
            cv.put(COLUMN_CONFIRM_PASSWORD , user.getConfirmPassword());

        long insert = db.insert(TABLE_USER , null , cv);
        if(insert == -1)
            return  false;
        else
            return true;
    }

    public List<User> getAllUser(){
        List<User> userList = new ArrayList<>();
        String rawQuery = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(rawQuery , null);

        if(cursor.moveToFirst()){
            do {
                long cursorId = cursor.getInt(0);
                String cursorEmail = cursor.getString(1);
                String cursorPassword = cursor.getString(2);
                String cursorConfirmPassword = cursor.getString(3);
                User user = new User(cursorId , cursorEmail , cursorPassword , cursorConfirmPassword);
                userList.add(user);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }
}
