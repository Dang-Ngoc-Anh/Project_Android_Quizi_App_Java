package com.example.quizi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import java.net.URL;

public class ContactActivity extends AppCompatActivity {

    ImageView imgFacebook , imgZaLo, imgGmail, imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        hook();
        imgFacebook.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.facebook.com/"));
            startActivity(intent);
        });
        imgZaLo.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://chat.zalo.me/"));
            startActivity(intent);

        });
        imgGmail.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://mail.google.com/"));
            startActivity(intent);

        });
        imgBack.setOnClickListener(v -> {
            Intent intent = new Intent(ContactActivity.this , MainActivity.class);
            startActivity(intent);
        });
    }

    private void hook() {
        imgFacebook = findViewById(R.id.imageViewFacebook);
        imgZaLo = findViewById(R.id.imageViewZalo);
        imgGmail = findViewById(R.id.imageViewGmail);
        imgBack = findViewById(R.id.imgBack);
    }
}