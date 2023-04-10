//package com.example.quizi.model;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import androidx.annotation.Nullable;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DataBaseQuestion extends SQLiteOpenHelper {
//    public static final String DB_QUESTION = "DB_QUESTION.db";
//    public static final String TABLE_QUESTION = "TABLE_QUESTION";
//    public static final String COLUMN_QUESTION = "COLUMN_QUESTION";
//    public static final String COLUMN_OPTION_A = "COLUMN_OPTION_A";
//    public static final String COLUMN_OPTION_B = "COLUMN_OPTION_B";
//    public static final String COLUMN_OPTION_C = "COLUMN_OPTION_C";
//    public static final String COLUMN_OPTION_D = "COLUMN_OPTION_D";
//    public static final String COLUMN_OPTION_ANSWER = "COLUMN_OPTION_ANSWER";
//    public static final String COLUMN_THEME = "COLUMN_THEME";
//
//
//    public DataBaseQuestion(@Nullable Context context) {
//        super(context, DB_QUESTION , null, 1);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String createTableQuestion =
//                "CREATE TABLE "
//                        + TABLE_QUESTION +  " ( s"
//                        + COLUMN_THEME + " TEXT,"
//                        + COLUMN_QUESTION + " TEXT,"
//                        + COLUMN_OPTION_A + " TEXT,"
//                        + COLUMN_OPTION_B + " TEXT,"
//                        + COLUMN_OPTION_C + " TEXT,"
//                        + COLUMN_OPTION_D + " TEXT,"
//                        + COLUMN_OPTION_ANSWER + " TEXT)";
//        db.execSQL(createTableQuestion);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }
//
//    public boolean addOneQuestion(Question question){
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues cv = new ContentValues();
//
//        cv.put(COLUMN_THEME , question.getTopic());
//        cv.put(COLUMN_QUESTION , question.getQuestion());
//        cv.put(COLUMN_OPTION_A , question.getOptionA());
//        cv.put(COLUMN_OPTION_B , question.getOptionB());
//        cv.put(COLUMN_OPTION_C , question.getOptionC());
//        cv.put(COLUMN_OPTION_D , question.getOptionD());
//        cv.put(COLUMN_OPTION_ANSWER , question.getAnswer());
//
//        long insert =  db.insert(TABLE_QUESTION , null , cv);
//        if(insert == -1)
//            return false;
//        else
//            return true;
//    }
//
//
//    public List<Question> getAllQuestion(){
//        List<Question> list = new ArrayList<>();
//        String rawQuery = "SELECT * FROM " + TABLE_QUESTION;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(rawQuery , null);
//
//        if(cursor.moveToFirst()){
//            do {
//                String theme = cursor.getString(0);
//                String option = cursor.getString(1);
//                String optionA = cursor.getString(2);
//                String optionB = cursor.getString(3);
//                String optionC = cursor.getString(4);
//                String optionD = cursor.getString(5);
//                String optionAnswer = cursor.getString(6);
//
//                Question question = new Question( 1, option, optionA , optionB , optionC , optionD , optionAnswer);
//                list.add(question);
//            }while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return list;
//    }
//}
