package com.example.quizi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserMainActivity extends AppCompatActivity {
    Button btnDashboard , btnContact;
    Button btnMark;
    TextView tvLogout;
    TextView tvUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        hook();
        tvUser.setText(LoginActivity.email);
        btnDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivityDoashboard();
            }
        });

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                System.exit(1);
            }
        });

        btnMark.setOnClickListener(v -> {
            Intent intent = new Intent(UserMainActivity.this, WonActivity.class);
            intent.putExtra("countCorrect" , WonActivity.countCorrect);
            intent.putExtra("countWrong" , WonActivity.countWrong);
            startActivity(intent);
        });

        btnContact.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(UserMainActivity.this , ContactActivity.class);
                startActivity(intent);
            }catch (Exception e){
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
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
        btnMark = findViewById(R.id.btnMark);
        btnContact = findViewById(R.id.btnLienHe);
    }
}