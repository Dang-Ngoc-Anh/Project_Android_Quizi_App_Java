package com.example.quizi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WonActivity extends AppCompatActivity {

    TextView tvResult , tvUser;
    Button btnSaveResult;
    static int countCorrect;
    static  int  countWrong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);
        hooks();
        tvUser.setText(LoginActivity.email);
         countCorrect = (int) getIntent().getExtras().get("countCorrect");
         countWrong = (int) getIntent().getExtras().get("countWrong");

        tvResult.setText("Đúng : " + countCorrect + "\nSai : " + countWrong);

        btnSaveResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivityMain();
            }
        });
    }

    private void nextActivityMain() {
        Intent intent = new Intent(WonActivity.this , MainActivity.class);
        startActivity(intent);
    }

    public void hooks(){
        tvUser = findViewById(R.id.tvUser);
        tvResult = findViewById(R.id.tvResult);
        btnSaveResult = findViewById(R.id.btnSaveResult);
    }
}