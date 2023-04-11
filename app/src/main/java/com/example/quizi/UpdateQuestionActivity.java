package com.example.quizi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class UpdateQuestionActivity extends AppCompatActivity {

    EditText etQuestion , etQuestionA , etQuestionB, etQuestionC , etQuestionD;
    String strAnswer;
    ImageView imgBack;
    Button btnSuccess , btnAnswer;
    TextView tvAnswer;
    static long number ;
    static long topicNumbers;
    static List<Question> list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_question);
        hook();
        list = new ArrayList<>();
//        Câu hỏi thứ mấy trong đề
          number = (long) getIntent().getExtras().get("number");
//        Đây là đề thứ mấy
          topicNumbers = (long) getIntent().getExtras().get("topicNumbers");
          loadData(topicNumbers , number);
        etQuestion.setText(list.get(0).getQuestion());
        etQuestionA.setText(list.get(0).getOptionA());
        etQuestionB.setText(list.get(0).getOptionB());
        etQuestionC.setText(list.get(0).getOptionC());
        etQuestionD.setText(list.get(0).getOptionD());
        Toast.makeText(this, "" +list.size(), Toast.LENGTH_SHORT).show();

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

                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateQuestionActivity.this);
                    builder.setTitle("Lựa chọn đáp án");

                    builder.setSingleChoiceItems(arrResultAnswer , -1 , (dialog, which) -> {
                        tvAnswer.setText(arrResultAnswer[which]);
                    });

                    builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            strAnswer = tvAnswer.getText().toString().trim();
                            QuestionDataBase.getInstance(UpdateQuestionActivity.this).questionDAO()
                                    .updateQuestionByNumber(topicNumbers , number , questions , questionA , questionB ,questionC , questionD , strAnswer);
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
                    Toast.makeText(UpdateQuestionActivity.this, "Không để trống", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSuccess.setOnClickListener(v -> {
            Intent intent = new Intent(UpdateQuestionActivity.this , TopicActivity .class);
            startActivity(intent);
        });
    }

    private void loadData(long topicNumbers, long number) {
        list = QuestionDataBase.getInstance(UpdateQuestionActivity.this)
                .questionDAO().getOneQuestion(topicNumbers , number );
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
    public void  hook(){
        etQuestion = findViewById(R.id.etQuestion);
        etQuestionA = findViewById(R.id.etQuestionA);
        etQuestionB = findViewById(R.id.etQuestionB);
        etQuestionC = findViewById(R.id.etQuestionC);
        etQuestionD = findViewById(R.id.etQuestionD);
        btnSuccess = findViewById(R.id.btnSuccess);
        btnAnswer = findViewById(R.id.btnAnwser);
        tvAnswer = findViewById(R.id.tvAnswer);
        imgBack = findViewById(R.id.imgBack);
    }


}