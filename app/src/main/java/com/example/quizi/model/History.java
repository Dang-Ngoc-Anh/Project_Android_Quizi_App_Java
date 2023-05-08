package com.example.quizi.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "TABLE_History")
public class History  {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private long topic;
    private long lan;
    private int countCorrect;
    private int countWrong;



    public History(long topic, long lan, int countCorrect, int countWrong) {
        this.topic = topic;
        this.lan = lan;
        this.countCorrect = countCorrect;
        this.countWrong = countWrong;
    }
    @Ignore
    public History(long id, long topic, long lan, int countCorrect, int countWrong) {
        this.id = id;
        this.topic = topic;
        this.lan = lan;
        this.countCorrect = countCorrect;
        this.countWrong = countWrong;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTopic() {
        return topic;
    }

    public void setTopic(long topic) {
        this.topic = topic;
    }

    public long getLan() {
        return lan;
    }

    public void setLan(long lan) {
        this.lan = lan;
    }

    public int getCountCorrect() {
        return countCorrect;
    }

    public void setCountCorrect(int countCorrect) {
        this.countCorrect = countCorrect;
    }

    public int getCountWrong() {
        return countWrong;
    }

    public void setCountWrong(int countWrong) {
        this.countWrong = countWrong;
    }
}
