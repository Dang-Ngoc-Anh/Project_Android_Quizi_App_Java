//package com.example.quizi.model;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import androidx.annotation.Nullable;
//
//public class DataBaseTheme extends SQLiteOpenHelper {
//
//    public static final String DB_THEME = "DB_THEME.db";
//    public static final String TABLE_THEME = "TABLE_THEME";
//    public static final String COLUMN_THEME = "COLUMN_THEME";
//
//
//    public DataBaseTheme(@Nullable Context context) {
//        super(context, DB_THEME, null, 1);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String createTableThem = "CREATE TABLE "
//                + TABLE_THEME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + COLUMN_THEME + " TEXT)";
//        db.execSQL(createTableThem);
//
//    }
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }
//}
