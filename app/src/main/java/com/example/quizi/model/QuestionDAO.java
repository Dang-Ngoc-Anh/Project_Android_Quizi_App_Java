package com.example.quizi.model;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public  interface QuestionDAO {

    @Query("SELECT * FROM TABLE_QUESTION")
    public List<Question> getItems();

    @Query("SELECT * FROM TABLE_QUESTION WHERE topic = :topicNumber")
    public List<Question> getItemByTopic(long topicNumber);

    @Query("SELECT * FROM TABLE_QUESTION WHERE topic = :topic AND numbers = :number")
    public List<Question> getOneQuestion(long topic , long number);

    @Query("Update TABLE_QUESTION SET " +
            "question = :question , " +
            "optionA = :questionA , " +
            "optionB = :questionB ," +
            "optionC = :questionC , " +
            "optionD = :questionD, " +
            "answer = :answer " +
            "WHERE topic = :topicNumbers AND numbers = :number ")
    public void updateQuestionByNumber(
            long topicNumbers,
            long number,
            String question ,
            String questionA,
            String questionB ,
            String questionC ,
            String questionD ,
            String answer
    );

    @Query("Delete from table_question where topic = :topic AND numbers = :numbers")
    public  void deleteQuestion(Long topic , Long numbers);

    @Query("Delete from table_question where topic = :topic")
    public  void deleteQuestionByTopic(Long topic);

    @Insert
    public  void insert(Question question);

    @Update
    public  void update(Question question);



}
