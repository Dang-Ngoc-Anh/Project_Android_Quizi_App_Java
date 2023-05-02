package com.example.quizi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizi.model.User;

public class MainActivity extends AppCompatActivity {

    Button btnDashboard;
    Button btnCreateExample;
    Button  btnMark;
    Button  btnContact;
    TextView tvLogout;
    TextView tvUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        remove tile , full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        hook();
        User user = new User(LoginActivity.email , LoginActivity.password);
        tvUser.setText(user.getEmail().toString());

//        button vào thi
        btnDashboard.setOnClickListener(v -> nextActivityDoashboard());

//        button tạo đề thi
        btnCreateExample.setOnClickListener(v -> nextActivityCreateExample());

        tvLogout.setOnClickListener(v -> {
            moveTaskToBack(true);
            System.exit(1);
        });

//        button xem kết quả vừa thi
        btnMark.setOnClickListener(v -> {
         Intent intent = new Intent(MainActivity.this, WonActivity.class);
            intent.putExtra("countCorrect" , DashboardActivity.countCorrect);
            intent.putExtra("countWrong" , DashboardActivity.countWrong);
            startActivity(intent);
        });

        btnContact.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(MainActivity.this , ContactActivity.class);
                startActivity(intent);
            }catch (Exception e){
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            }
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
        btnMark = findViewById(R.id.btnMark);
        btnContact = findViewById(R.id.btnLienHe);
    }


}