package com.example.quizi.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {History.class} , version = 1)
public abstract class HistoryDataBase extends RoomDatabase {
    private static String DB_NAME = "DB_HISTORY.db";
    private static HistoryDataBase instance;

    public static synchronized HistoryDataBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext() , HistoryDataBase.class , DB_NAME)
                    .allowMainThreadQueries().build();
        }
        return instance;
    }


    public abstract HistoryDao historyDao();
}
