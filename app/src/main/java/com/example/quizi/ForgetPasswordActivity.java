package com.example.quizi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quizi.model.DataBaseUser;
import com.example.quizi.model.User;

public class ForgetPasswordActivity extends AppCompatActivity {

    EditText etEmail;
    EditText etPasswordNew;
    EditText etConfirmPasswordNew;
    Button btnSuccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        hook();

        btnSuccess.setOnClickListener(v -> {
            DataBaseUser dataBaseUser = new DataBaseUser(ForgetPasswordActivity.this);
            String email = etEmail.getText().toString();
            String passwordNew = etPasswordNew.getText().toString();
            String confirmPasswordNew = etConfirmPasswordNew.getText().toString();
            try{
                if(passwordNew.equals(confirmPasswordNew)){
                    dataBaseUser.updatePassword(email , passwordNew);
                    Toast.makeText(this, "Mật khẩu mỡi đã thay đổi", Toast.LENGTH_SHORT).show();
                    nextActivityLogin();
                }else
                    Toast.makeText(this, "Password với confirm chưa đúng", Toast.LENGTH_SHORT).show();
            }
            catch (Exception e){
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void nextActivityLogin() {
        Intent intent =  new Intent(ForgetPasswordActivity.this , LoginActivity.class);
        startActivity(intent);
    }

    private void hook() {
        etEmail = findViewById(R.id.etEmail);
        etPasswordNew = findViewById((R.id.etPasswordNew));
        etConfirmPasswordNew = findViewById((R.id.etConfirmPasswordNew));
        btnSuccess = findViewById(R.id.btnSuccess);
    }

}