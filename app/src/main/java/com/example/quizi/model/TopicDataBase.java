package com.example.quizi.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Topic.class} , version = 1)
public abstract class TopicDataBase extends RoomDatabase {
    private static String DB_NAME = "DB_TOPIC.db";
    private static TopicDataBase instance;

    public static synchronized TopicDataBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext() , TopicDataBase.class , DB_NAME)
                    .allowMainThreadQueries().build();
        }

        return instance;
    }

    public abstract TopicDAO topicDAO();
}
