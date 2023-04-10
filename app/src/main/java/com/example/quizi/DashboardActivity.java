package com.example.quizi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.quizi.model.Question;
import com.example.quizi.model.QuestionDataBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    ProgressBar progressBar;
    List<Question> modelList;
    CountDownTimer countDownTimer;
    Question question;
    TextView cardQuestion ,cardOptionA , cardOptionB ,cardOptionC,cardOptionD;
    CardView cardA , cardB , cardC , cardD;
    Button btnNext;
    int timeValue = 15;
    int index = 0;
    int countCorrect = 0;
    int countWrong = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        hooks();

        modelList = new ArrayList<>();
        modelList = QuestionDataBase.getInstance(getApplicationContext()).questionDAO().getItems();
//        Collections.shuffle(modelList);
        question = modelList.get(index);
        setAllData();
        btnNext.setClickable(false);

        resetDialog();
        cardA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableButton();
                btnNext.setClickable(true);
                if(question.getOptionA().equals(question.getAnswer())){
                    cardA.setBackgroundColor(getResources().getColor(R.color.green));
                    if(index < modelList.size() - 1){
                       correct(cardA);
                    }else
                        gameWon();
                }else
                    wrong(cardA);
            }
        });

        cardB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableButton();
                btnNext.setClickable(true);
                if(question.getOptionB().equals(question.getAnswer())){
                    cardB.setBackgroundColor(getResources().getColor(R.color.green));
                    if(index < modelList.size() - 1){
                        correct(cardB);
                    }else
                        gameWon();
                }else
                    wrong(cardB);
            }
        });

        cardC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableButton();
                btnNext.setClickable(true);
                if(question.getOptionC().equals(question.getAnswer())){
                    cardC.setBackgroundColor(getResources().getColor(R.color.green));
                    if(index < modelList.size() - 1){
                       correct(cardC);
                    }else
                        gameWon();
                }else
                    wrong(cardC);
            }
        });

        cardD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableButton();
                btnNext.setClickable(true);
                if(question.getOptionD().equals(question.getAnswer())){
                    cardD.setBackgroundColor(getResources().getColor(R.color.green));
                    if(index < modelList.size() - 1){
                        correct(cardD);
                    }else
                        gameWon();
                }else
                    wrong(cardD);
            }
        });
    }

    private void setAllData() {

        cardQuestion.setText(question.getQuestion());
        cardOptionA.setText(question.getOptionA());
        cardOptionB.setText(question.getOptionB());
        cardOptionC.setText(question.getOptionC());
        cardOptionD.setText(question.getOptionD());
    }


    public void hooks(){
         progressBar = (ProgressBar) findViewById(R.id.quizi_timer);

//         hook textview show data
         cardQuestion = findViewById(R.id.card_question);
         cardOptionA = findViewById(R.id.card_opptionA);
         cardOptionB = findViewById(R.id.card_opptionB);
         cardOptionC = findViewById(R.id.card_opptionC);
         cardOptionD = findViewById(R.id.card_opptionD);

//         select answer
        cardA = findViewById(R.id.cardA);
        cardB = findViewById(R.id.cardB);
        cardC = findViewById(R.id.cardC);
        cardD= findViewById(R.id.cardD);

//        next question
        btnNext = findViewById(R.id.btnNext);
    }


    public void resetDialog(){
        countDownTimer = new CountDownTimer(15000 , 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeValue = timeValue - 1;
                progressBar.setProgress(timeValue);
            }

            @Override
            public void onFinish() {
                Dialog dialog = new Dialog(DashboardActivity.this);
                dialog.setContentView(R.layout.time_out_dialog);
                dialog.show();
                dialog.findViewById(R.id.btnTryAgain).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DashboardActivity.this , MainActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }.start();
    }
    public void correct(CardView card){
        card.setBackgroundColor(getResources().getColor(R.color.green));
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                timeValue = 15;
                resetDialog();
                countCorrect++;
                index++;
                question = modelList.get(index);
                resetColor();
                setAllData();
                enableButton();
            }
        });
    }

    public void wrong(CardView card){
        card.setBackgroundColor(getResources().getColor(R.color.red));
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                timeValue = 15;
                resetDialog();
                countWrong++;
                if(index < modelList.size() - 1){
                    index++;
                    question = modelList.get(index);
                    resetColor();
                    setAllData();
                    enableButton();
                }else {
                    gameWon();
                }
            }
        });

    }

    private void gameWon() {
        Intent intent = new Intent(DashboardActivity.this , WonActivity.class);
        intent.putExtra("countCorrect" , countCorrect);
        intent.putExtra("countWrong" , countWrong);
        startActivity(intent);
    }

    public void enableButton(){
        cardA.setClickable(true);
        cardB.setClickable(true);
        cardC.setClickable(true);
        cardD.setClickable(true);
    }
    public void disableButton(){
        cardA.setClickable(false);
        cardB.setClickable(false);
        cardC.setClickable(false);
        cardD.setClickable(false);
    }

    public void resetColor(){
        cardA.setBackgroundColor(getResources().getColor(R.color.white));
        cardB.setBackgroundColor(getResources().getColor(R.color.white));
        cardC.setBackgroundColor(getResources().getColor(R.color.white));
        cardD.setBackgroundColor(getResources().getColor(R.color.white));
    }


}