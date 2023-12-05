package com.example.healthywealthy.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.healthywealthy.model.User;

public class SaveData {

    public static void saveUser(User user, Context context){
        if(user != null){
            SharedPreferences sharedPreferences = context.getSharedPreferences("user_details", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", user.getName());
            editor.apply();
        }
    }

    public static void resetUser(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_details", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
    public static String getUser( Context context){
        String username = "";
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_details", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username","");
        return username;
    }
}
