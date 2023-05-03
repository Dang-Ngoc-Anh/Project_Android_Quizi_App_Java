package com.example.quizi.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "TABLE_TOPIC")
public class Topic implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private long topic;

    public Topic(long id, long topic) {
        this.id = id;
        this.topic = topic;
    }

    public Topic(long topic) {
        this.topic = topic;
    }

    public Topic() {

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

    @Override
    public String toString() {
        return "Theme{" +
                "id=" + id +
                ", theme='" + topic + '\'' +
                '}';
    }
}
