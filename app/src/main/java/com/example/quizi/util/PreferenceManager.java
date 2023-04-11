package com.example.quizi.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
   private final SharedPreferences sharedPreferences;

    public PreferenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences(Const.USER_NAME , Context.MODE_PRIVATE);
    }

    public void putString(String key , String value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key , value);
        editor.apply();
    }

    public String getUser(String key){
        return sharedPreferences.getString(key , "Admin");
    }
}
