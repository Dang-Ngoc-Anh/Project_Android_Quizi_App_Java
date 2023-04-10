package com.example.quizi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quizi.adapter.AdapterTopic;
import com.example.quizi.model.Question;
import com.example.quizi.model.QuestionDataBase;
import com.example.quizi.model.Topic;
import com.example.quizi.model.TopicDataBase;

import java.util.ArrayList;
import java.util.List;

public class TopicActivity extends AppCompatActivity {

      ImageView imgAdd;
      ImageView imgBack;
      ListView lvTestNumber;
      Button btnDelete;
      Button btnUpdate;
      AdapterTopic adapterTopic;
      static List<Topic> list ;
      private int index = 0;
      private long topicNumbers;
      TextView tvTopicNumbers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        hook();
        list = new ArrayList<>();
        loadData();

        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOneData();
                loadData();
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                startActivity(intent);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list = TopicDataBase.getInstance(TopicActivity.this).topicDAO().getAllTopic();

                AlertDialog.Builder builder = new AlertDialog.Builder(TopicActivity.this);
                builder.setTitle("Lựa chọn để : ");

                String[] arrTopicNumbers = new String[list.size()];
                for(int i = 0 ; i < list.size() ; i++){
                    arrTopicNumbers[i] = String.valueOf(list.get(i).getTopic());
                }

                builder.setSingleChoiceItems( arrTopicNumbers , -1 , (dialog, which) -> {
                    tvTopicNumbers = findViewById(R.id.tvTopic);
                    topicNumbers = Long.parseLong(arrTopicNumbers[which].toString());
                });

                builder.setPositiveButton("Đồng ý ", (dialog, which) -> {
                    nextActivityGetQuestion(topicNumbers);
                });

                builder.setNegativeButton("Cand " , (dialog, which) -> dialog.dismiss());
                Dialog dialog = builder.create();
                dialog.show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
                list = TopicDataBase.getInstance(TopicActivity.this).topicDAO().getAllTopic();
                AlertDialog.Builder builder = new AlertDialog.Builder(TopicActivity.this);
                builder.setTitle("Lựa chọn đề : ");

                String[] arrTopicNumbers = new String[list.size()];
                for(int i = 0 ; i < list.size() ; i++){
                    arrTopicNumbers[i] = String.valueOf(list.get(i).getTopic());
                }

                builder.setSingleChoiceItems( arrTopicNumbers , -1 , (dialog, which) -> {
                    tvTopicNumbers = findViewById(R.id.tvTopic);
                    topicNumbers = Long.parseLong(arrTopicNumbers[which].toString());
                });

                builder.setPositiveButton("Đồng ý ", (dialog, which) -> {
                    TopicDataBase.getInstance(TopicActivity.this).topicDAO().deleteTopicByName(topicNumbers);
                    QuestionDataBase.getInstance(TopicActivity.this).questionDAO().deleteQuestionByTopic(topicNumbers);
                    loadData();
                });

                builder.setNegativeButton("Cand " , (dialog, which) -> dialog.dismiss());
                Dialog dialog = builder.create();
                dialog.show();
            }
        });

        lvTestNumber.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                nextActivityCreateQuestion();
            }
        });
    }

    private void nextActivityGetQuestion(long topicNumber) {
        Intent intent = new Intent(TopicActivity.this , GetTestNumberActivity.class);
        intent.putExtra("topicNumbers" , topicNumber);
        startActivity(intent);
    }

    private void nextActivityCreateQuestion() {
        Intent intent = new Intent(TopicActivity.this , CreateExmapleActivity.class);
        long topicNumbers = list.get(index).getTopic();
        intent.putExtra("topicNumbers" , topicNumbers);
        startActivity(intent);
    }

    private void addOneData() {
        if(list.size() == 0){
            TopicDataBase.getInstance(TopicActivity.this).topicDAO().insertTopic(new Topic(0));
        }else {
            int size =  list.size();
            long numbers = list.get(size - 1).getTopic() + 1;
            Topic topic = new Topic(numbers);
            TopicDataBase.getInstance(TopicActivity.this).topicDAO().insertTopic(topic);
        }
    }


    private void loadData() {
        list = TopicDataBase.getInstance(TopicActivity.this).topicDAO().getAllTopic();
        adapterTopic = new AdapterTopic(TopicActivity.this , R.layout.topic_item, list);
        lvTestNumber.setAdapter(adapterTopic);
    }

    private void hook() {
        imgAdd = findViewById(R.id.imgAdd);
        imgBack = findViewById(R.id.imgBack);
        lvTestNumber = findViewById(R.id.lvTestNumber);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
    }
}