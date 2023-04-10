package com.example.quizi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizi.model.Question;
import com.example.quizi.model.QuestionDataBase;

import java.util.ArrayList;
import java.util.List;

public class UserMainActivity extends AppCompatActivity {
    Button btnDashboard;
    Button btnTest;
    TextView tvLogout;
    TextView tvUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        hook();
        btnDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivityDoashboard();
            }
        });

    }

    private void nextActivityDoashboard() {
        Intent intent = new Intent(UserMainActivity.this , DashboardActivity.class);
        startActivity(intent);
    }


    public void hook(){
        btnDashboard = (Button) findViewById(R.id.btnDashboard);
        tvLogout = findViewById(R.id.tvLogout);
        tvUser = findViewById(R.id.tvUser);
        btnTest = findViewById(R.id.btnDanhGia);
    }
}