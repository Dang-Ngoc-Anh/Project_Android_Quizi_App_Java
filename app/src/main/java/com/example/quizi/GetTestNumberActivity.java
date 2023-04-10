package com.example.quizi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.quizi.adapter.AdapterQuestion;
import com.example.quizi.model.Question;
import com.example.quizi.model.QuestionDataBase;
import com.example.quizi.model.TopicDataBase;

import java.util.ArrayList;
import java.util.List;

public class GetTestNumberActivity extends AppCompatActivity {

    Button btnDelete ;
    Button btnSuccess;
    ListView lvQuestion;
    AdapterQuestion adapterQuestion;
    static List<Question> list;
    long topicNumbers ;
    int index;
    long number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_test_number);
        hook();
        list = new ArrayList<>();
        topicNumbers = (long) getIntent().getExtras().get("topicNumbers");
        loadData();

        lvQuestion.setOnItemClickListener((parent, view, position, id) -> {
            index = position;
            nextActivityUpdate();
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
                AlertDialog.Builder builder = new AlertDialog.Builder(GetTestNumberActivity.this);
                builder.setTitle("Lựa chọn câu cần xóa : ");

                String[] arrQuestionNumbers = new String[list.size()];
                for(int i = 0 ; i < list.size() ; i++){
                    arrQuestionNumbers[i] = String.valueOf(list.get(i).getNumbers());
                }

                builder.setSingleChoiceItems( arrQuestionNumbers , -1 , (dialog, which) -> {
                    number = Long.parseLong(arrQuestionNumbers[which].toString());
                });

                builder.setPositiveButton("Đồng ý ", (dialog, which) -> {
                    QuestionDataBase.getInstance(GetTestNumberActivity.this).questionDAO().deleteQuestion(topicNumbers,number);
                    loadData();
                });

                builder.setNegativeButton("Cand " , (dialog, which) -> dialog.dismiss());
                Dialog dialog = builder.create();
                dialog.show();
            }
        });

        btnSuccess.setOnClickListener(v -> {
            nextActivityTopic();
        });
    }

    private void nextActivityTopic() {
        Intent intent = new Intent(GetTestNumberActivity.this , TopicActivity.class);
        startActivity(intent);
    }

    private void nextActivityUpdate() {
        Intent intent = new Intent(GetTestNumberActivity.this , UpdateQuestionActivity.class);
        long number = list.get(index).getNumbers();
        intent.putExtra("number"  , number);
        intent.putExtra("topicNumbers" , topicNumbers);
        startActivity(intent);
    }

    private void hook() {
        btnSuccess = findViewById(R.id.btnSuccess);
        lvQuestion = findViewById(R.id.lvQuestion);
        btnDelete = findViewById(R.id.btnDelete);
        btnSuccess = findViewById(R.id.btnSuccess);
    }

    private void loadData(){
        list = QuestionDataBase.getInstance(GetTestNumberActivity.this).questionDAO().getItemByTopic(topicNumbers);
        adapterQuestion  = new AdapterQuestion(this , R.layout.topic_item , list);
        lvQuestion.setAdapter(adapterQuestion);
    }
}