package com.example.quizi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizi.model.Question;
import com.example.quizi.model.QuestionDataBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CreateExmapleActivity extends AppCompatActivity {

    EditText etQuestion , etQuestionA , etQuestionB, etQuestionC , etQuestionD;
    String strAnswer;
    ImageView imgAdd , imgBack;
    Button btnSuccess , btnAnswer;
    TextView tvAnswer;
    static List<Question> list = new ArrayList<>();
    static long topicNumbers ;
    static int maxQuestion = 5;
    long number = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exmaple);
        hook();
        topicNumbers =  (long) getIntent().getExtras().get("topicNumbers");

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateExmapleActivity.this  , TopicActivity.class);
                startActivity(intent);
            }
        });
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearValue();
            }
        });

        btnSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivityMain();
            }
        });

        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String questions = etQuestion.getText().toString().trim();
                String questionA = etQuestionA.getText().toString().trim();
                String questionB = etQuestionB.getText().toString().trim();
                String questionC = etQuestionC.getText().toString().trim();
                String questionD = etQuestionD.getText().toString().trim();
                if(isEmpty(questions , questionA , questionB , questionC , questionD)){
                    String[] arrResultAnswer = new String[]{questionA , questionB , questionC , questionD};

                    AlertDialog.Builder builder = new AlertDialog.Builder(CreateExmapleActivity.this);
                    builder.setTitle("Lựa chọn đáp án");

                    builder.setSingleChoiceItems(arrResultAnswer , -1 , (dialog, which) -> {
                        tvAnswer.setText(arrResultAnswer[which]);
                    });

                    builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            strAnswer = tvAnswer.getText().toString().trim();
                            number += 1;
                            list = QuestionDataBase.getInstance(CreateExmapleActivity.this).questionDAO().getItemByTopic(topicNumbers);
                            if(list.size() < maxQuestion){
                                Question question = new Question(
                                        topicNumbers ,
                                        list.get(list.size() - 1).getNumbers() + 1,
                                        questions, questionA, questionB, questionC, questionD, strAnswer
                                );
                                addOneData(question);
                            }else {
                                Toast.makeText(CreateExmapleActivity.this, "Đề đã đủ câu hỏi", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    builder.setNegativeButton("Cand", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    Dialog dialog = builder.create();
                    dialog.show();
                }else {
                    Toast.makeText(CreateExmapleActivity.this, "Không để trống", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void nextActivityMain() {
        list = QuestionDataBase.getInstance(this).questionDAO().getItems();
        long count = list.stream().filter(item  -> topicNumbers == item.getTopic()).count();
        if(count >= maxQuestion  ){
            Intent intent = new Intent(CreateExmapleActivity.this , MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Đã thêm " + maxQuestion + " câu hỏi vào đề " + topicNumbers, Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, "Đề " + topicNumbers + " có " + count +"/"+ maxQuestion, Toast.LENGTH_SHORT).show();
        }

    }

    private void disableEditText(EditText editText) {
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
        editText.setBackgroundColor(Color.TRANSPARENT);
    }
    private void clearValue() {
        list = QuestionDataBase.getInstance(this).questionDAO().getItems();
        long count = list.stream().filter(item  -> topicNumbers == item.getTopic()).count();
        if(count >= maxQuestion){
            Toast.makeText(this, "Đã đủ " + maxQuestion  + " câu hỏi", Toast.LENGTH_SHORT).show();
            disableEditText(etQuestion);
            disableEditText(etQuestionA);
            disableEditText(etQuestionB);
            disableEditText(etQuestionC);
            disableEditText(etQuestionD);
        }else {
            Toast.makeText(this, "Câu hỏi thứ " + count + "/" + maxQuestion , Toast.LENGTH_SHORT).show();
            etQuestion.setText("");
            etQuestionA.setText("");
            etQuestionB.setText("");
            etQuestionC.setText("");
            etQuestionD.setText("");
        }

    }

    private boolean isEmpty(String question , String questionA , String questionB ,String questionC , String questionD  ){
        if(TextUtils.isEmpty(question)
                || TextUtils.isEmpty(questionA)
                || TextUtils.isEmpty(questionB)
                || TextUtils.isEmpty(questionC)
                || TextUtils.isEmpty(questionD)
        )
            return false;
        return true;
    }
    private void addOneData(Question question) {
        QuestionDataBase.getInstance(CreateExmapleActivity.this).questionDAO().insert(question);
    }

    public void  hook(){
        etQuestion = findViewById(R.id.etQuestion);
        etQuestionA = findViewById(R.id.etQuestionA);
        etQuestionB = findViewById(R.id.etQuestionB);
        etQuestionC = findViewById(R.id.etQuestionC);
        etQuestionD = findViewById(R.id.etQuestionD);
        btnSuccess = findViewById(R.id.btnSuccess);
        imgAdd = findViewById(R.id.imgAdd);
        btnAnswer = findViewById(R.id.btnAnwser);
        tvAnswer = findViewById(R.id.tvAnswer);
        imgBack = findViewById(R.id.imgBack);
    }
}