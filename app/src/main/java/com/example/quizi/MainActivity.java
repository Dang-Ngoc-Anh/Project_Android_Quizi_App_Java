package com.example.quizi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizi.model.Question;
import com.example.quizi.model.QuestionDataBase;
import com.example.quizi.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnDashboard;
    Button btnCreateExample;
    Button btnTest;
    TextView tvLogout;
    TextView tvUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hook();

//        User user = (User) getIntent().getExtras().get("objUser");
//        tvUser.setText(user.getEmail().toString());
        btnDashboard.setOnClickListener(v -> nextActivityDoashboard());

        btnCreateExample.setOnClickListener(v -> nextActivityCreateExample());

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                System.exit(1);
            }
        });

        btnTest.setOnClickListener(v -> {

        });
    }

    private void nextActivityCreateExample() {
        Intent intent = new Intent(MainActivity.this , TopicActivity.class);
        startActivity(intent);
    }

    private void nextActivityDoashboard() {
        Intent intent = new Intent(MainActivity.this , DashboardActivity.class);
        startActivity(intent);
    }

    public void hook(){
        btnDashboard = (Button) findViewById(R.id.btnDashboard);
        btnCreateExample = findViewById(R.id.btnCreateExample);
        tvLogout = findViewById(R.id.tvLogout);
        tvUser = findViewById(R.id.tvUser);
        btnTest = findViewById(R.id.btnDanhGia);
    }


}