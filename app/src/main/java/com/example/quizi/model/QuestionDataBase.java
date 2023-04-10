package com.example.quizi.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Question.class}, version = 1)
public abstract class QuestionDataBase extends RoomDatabase {
    private static String DB_NAME = "DB_QUESTION.db";
    private static QuestionDataBase instance;

    public static synchronized QuestionDataBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext() , QuestionDataBase.class , DB_NAME)
                    .allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract QuestionDAO questionDAO();
}
