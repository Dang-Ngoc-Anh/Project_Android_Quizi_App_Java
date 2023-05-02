package com.example.quizi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quizi.model.DataBaseUser;
import com.example.quizi.model.User;

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    EditText etEmail;
    EditText etPassword;
    EditText etConfirmPassword;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getView();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertUser();
            }
        });
    }

    private boolean checkEmpty(){
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword =  etConfirmPassword.getText().toString().trim();
        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirmPassword) )
            return true;
        else
            return false;
    }
    private void insertUser() {
        User user;
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirmPassword =  etConfirmPassword.getText().toString().trim();

            if(password.equals(confirmPassword) && checkEmpty() == true && isValidEmail(email) == true){
                DataBaseUser dataBaseUser = new DataBaseUser(SignUpActivity.this);
                List<User> list = new ArrayList<>();
                list = dataBaseUser.getAllUser();

//                Rông add vao DB
                if(list.size() == 0){
                    user = new User(email,  password, confirmPassword);
                    dataBaseUser.addOne(user);
                    Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    nextActivityLogin();
                }else {
//                    ko rỗng kiểm tra xem co tồn tại hay ko
                    for(User item : list){
                        if(!(item.getEmail().contains(email))){
                            user = new User(email,  password, confirmPassword);
                            boolean success =  dataBaseUser.addOne(user);
                            if(success == true){
                                nextActivityLogin();
                                Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                break;
                            }else{
                                Toast.makeText(this, "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(this, "Account đã có", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                }

            }
            else
                Toast.makeText(this, "Không để trống hoặc email chưa fomat", Toast.LENGTH_SHORT).show();
    }

    private void nextActivityLogin() {
        Intent intent = new Intent(SignUpActivity.this , LoginActivity.class);
        startActivity(intent);
    }

    private boolean isValidEmail(CharSequence email) {
        if (!TextUtils.isEmpty(email)) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
        return false;
    }
    public void getView(){
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPasswordNew);
        etConfirmPassword = findViewById(R.id.etConfirmPasswordNew);
        btnSignUp = findViewById(R.id.btnSuccess);
    }

//    public void showAdapter(){
//        DataBaseUser dataBaseUser = new DataBaseUser(SignUpActivity.this);
//        List<User> userList = dataBaseUser.getAllUser();
//
//        ArrayAdapter arrayAdapter = new ArrayAdapter<User>(SignUpActivity.this , R.layout.list_view , userList);
//        new ListView(SignUpActivity.this).setAdapter(arrayAdapter);
//    }
}