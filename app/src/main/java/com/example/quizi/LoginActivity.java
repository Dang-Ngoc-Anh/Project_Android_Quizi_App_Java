package com.example.quizi;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;

import com.example.quizi.model.DataBaseUser;
import com.example.quizi.model.User;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail;
    EditText etPassword;
    Button btnlogin;
    TextView forgetPassword , createAccount;
    public static String email;
    public static String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        init view
        getView();
        etEmail.setText("Admin");
        etPassword.setText("admin");
        createAccount.setOnClickListener(v -> nextActivitySignUp());
        forgetPassword.setOnClickListener(v -> {
            nextActivityForgetPassword();
        });

        btnlogin.setOnClickListener(v -> {
            email = etEmail.getText().toString().trim();
            password = etPassword.getText().toString().trim();

//            get list data user
            DataBaseUser dataBaseUser = new DataBaseUser(LoginActivity.this);
            List<User> list = new ArrayList<>();
            list = dataBaseUser.getAllUser();

//          check account
            if(email.equals("Admin") && password.equals("admin")){
                email = "Admin";
                nextActivityMain();
                return;
            }
            boolean hasUser = true;
            if(checkEmpty() == true ){
                if(isValidEmail(email)){
                    if(list.size() == 0){
                        Toast.makeText(LoginActivity.this, "Bạn chưa có account", Toast.LENGTH_SHORT).show();
                    }else
                        for(User item : list){
                            if(item.getEmail().equals(email)  && item.getPassword().equals(password)){
                                nextActivityUserMain();
                                hasUser = true;
                                Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                break;
                            } else
                                hasUser = false;
                        }
                }else
                    Toast.makeText(LoginActivity.this, "Email chưa fomat", Toast.LENGTH_SHORT).show();
            }else
                Toast.makeText(LoginActivity.this, "Không để trống", Toast.LENGTH_SHORT).show();
            if(hasUser == false)
                Toast.makeText(LoginActivity.this, "Bạn chưa có account", Toast.LENGTH_SHORT).show();
        });
    }

    private void nextActivityUserMain() {
        Intent intent = new Intent(LoginActivity.this , UserMainActivity.class);
        startActivity(intent);
    }

    private void nextActivitySignUp() {
        Intent intent = new Intent(LoginActivity.this , SignUpActivity.class);
        startActivity(intent);
    }

    private void nextActivityMain(){
            Intent intent = new Intent(LoginActivity.this , MainActivity.class);
            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            startActivity(intent);
    }

    private boolean checkEmpty(){
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password))
            return true;
        else
            return false;
    }
    private boolean isValidEmail(CharSequence email) {
        if (!TextUtils.isEmpty(email)) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
        return false;
    }

    public void nextActivityForgetPassword(){
        Intent intent = new Intent(LoginActivity.this , ForgetPasswordActivity.class);
        startActivity(intent);

    }
    public void getView(){
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPasswordNew);
        btnlogin = findViewById(R.id.btnLogin);
        forgetPassword = findViewById(R.id.forgetPassword);
        createAccount = findViewById(R.id.createAccount);
    }
}