package com.example.quizi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.quizi.adapter.AdapterTopic;
import com.example.quizi.model.Question;
import com.example.quizi.model.QuestionDataBase;
import com.example.quizi.model.Topic;
import com.example.quizi.model.TopicDataBase;

import java.util.ArrayList;
import java.util.List;

public class GetTopicActivity extends AppCompatActivity {

    ImageView imgBack;
    AdapterTopic adapterTopic;
    List<Topic> topicList = new ArrayList<>();
    ListView lvTopic;
    List<Question> questionList = new ArrayList<>();
    Topic topic = new Topic();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_topic);
        hook();
        loadData();
        lvTopic.setOnItemClickListener((parent, view, position, id) -> {
            topic = topicList.get(position);
            nextActivityDashboard(topic);
        });

        imgBack.setOnClickListener(v -> {
            Intent intent = new Intent(GetTopicActivity.this , MainActivity.class);
            startActivity(intent);
        });
    }

    private void nextActivityDashboard(Topic topic) {
        Intent intent = new Intent(GetTopicActivity.this , DashboardActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("objTopic" , topic);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void hook() {
        lvTopic = findViewById(R.id.lvTestNumber);
        imgBack = findViewById(R.id.imgBack);
    }

    private void loadData(){
        topicList = TopicDataBase.getInstance(GetTopicActivity.this).topicDAO().getAllTopic();
        questionList = QuestionDataBase.getInstance(GetTopicActivity.this).questionDAO().getItems();
        List<Topic> topicList1 = new ArrayList<>();
        for(Topic t : topicList){
            for(Question q : questionList){
                if(t.getTopic() == q.getTopic() ){
                    topicList1.add(t);
                    break;
                }
            }
        }
        adapterTopic = new AdapterTopic(GetTopicActivity.this , R.layout.topic_item , topicList1);
        lvTopic.setAdapter(adapterTopic);

    }
}