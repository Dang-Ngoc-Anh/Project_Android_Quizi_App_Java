package com.example.quizi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizi.model.History;
import com.example.quizi.model.HistoryDataBase;
import com.example.quizi.model.Topic;

import java.util.ArrayList;
import java.util.List;

public class WonActivity extends AppCompatActivity {

    TextView tvResult , tvUser;
    Button btnSaveResult;
    static int countCorrect;
    static int  countWrong;
    long topic;
    static List<History> historyList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);
        hooks();
        historyList = HistoryDataBase.getInstance(WonActivity.this).historyDao().getAllHistory();
        tvUser.setText(LoginActivity.email);
         countCorrect = (int) getIntent().getExtras().get("countCorrect");
         countWrong = (int) getIntent().getExtras().get("countWrong");
         topic = (long) getIntent().getExtras().get("topicNumber");
         tvResult.setText("Đúng : " + countCorrect + "\nSai : " + countWrong);

        btnSaveResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivityMain();
            }
        });
    }

    private void nextActivityMain() {
        try {
            History history ;
            Intent intent = new Intent(WonActivity.this , MainActivity.class);
            if(historyList.size() == 0){
                history = new History(topic , 0 , countCorrect , countWrong );
                HistoryDataBase.getInstance(WonActivity.this).historyDao().insertHistory(history);
            }else {
                long lan = historyList.get(historyList.size()-1).getLan() + 1;
                history = new History(topic , lan , countCorrect , countWrong );
                HistoryDataBase.getInstance(WonActivity.this).historyDao().insertHistory(history);
            }
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void hooks(){
        tvUser = findViewById(R.id.tvUser);
        tvResult = findViewById(R.id.tvResult);
        btnSaveResult = findViewById(R.id.btnSaveResult);
    }
}