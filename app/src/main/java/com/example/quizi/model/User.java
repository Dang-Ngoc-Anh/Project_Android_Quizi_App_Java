package com.example.quizi.model;

import androidx.room.Entity;

import java.io.Serializable;

public class User implements Serializable {
    private long id ;
    private  String email;
    private  String password;
    private  String confirmPassword;

//    Contructer Login
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(long id ,String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

//    contruct sign up
    public User(String email, String password, String confirmPassword) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public User(long id , String email, String password, String confirmPassword) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }


    public long getId() {
        return id;
    }

    public void setId(long  id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
