package com.example.quizi.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HistoryDao {

    @Query("SELECT * FROM TABLE_History")
    public List<History> getAllHistory();
    @Insert
    public void insertHistory(History history);
}
