package com.example.quizi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.quizi.adapter.AdapterHistory;
import com.example.quizi.model.History;
import com.example.quizi.model.HistoryDataBase;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    ImageView imgBack;
    ListView lvHistory;
    AdapterHistory adapterHistory;
    static List<History> historyList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        hook();
        loadData();
        imgBack.setOnClickListener(v -> {
            Intent intent = new Intent(HistoryActivity.this , MainActivity.class);
            startActivity(intent);
        });
    }

    private void loadData() {
        historyList = HistoryDataBase.getInstance(HistoryActivity.this).historyDao().getAllHistory();
        adapterHistory = new AdapterHistory(HistoryActivity.this , R.layout.history_item , historyList);
        lvHistory.setAdapter(adapterHistory);
    }

    private void hook() {
        imgBack = findViewById(R.id.imgBack);
        lvHistory = findViewById(R.id.lvHistory);
    }
}