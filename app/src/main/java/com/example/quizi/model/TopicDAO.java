package com.example.quizi.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TopicDAO {

    @Query("SELECT * FROM TABLE_TOPIC")
    List<Topic>  getAllTopic();

    @Insert
    public void insertTopic(Topic topic);


    @Query("DELETE FROM TABLE_TOPIC WHERE topic = :numbersTopic")
    public void deleteTopicByName(long numbersTopic);
}
