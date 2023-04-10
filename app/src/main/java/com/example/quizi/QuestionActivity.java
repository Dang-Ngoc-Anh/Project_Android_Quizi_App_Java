package com.example.quizi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.quizi.adapter.AdapterQuestion;
import com.example.quizi.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    public static ImageView imgAdd;
    public static ImageView imgBack;
    ListView lvQuestion;
    AdapterQuestion adapterQuestion;
    List<Question> list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        hook();
        addOneData();
        adapterQuestion = new AdapterQuestion(QuestionActivity.this, R.layout.topic_item, list);
        lvQuestion.setAdapter(adapterQuestion);
//
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionActivity.this , TopicActivity.class);
                startActivity(intent);
            }
        });

    }

    private void addOneData() {
        list = new ArrayList<>();
    }

    private void hook() {
        imgAdd = findViewById(R.id.imgAdd);
        imgBack = findViewById(R.id.imgBack);
        lvQuestion = findViewById(R.id.lvQuestion);
    }
}