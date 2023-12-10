package com.example.healthywealthy.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.healthywealthy.model.User;

public class DataOperation {

    public static void saveUser(User user, Context context){
        if(user != null){
            SharedPreferences sharedPreferences = context.getSharedPreferences("user_details", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", user.getName());
            editor.putInt("age",user.getAge());
            editor.apply();
        }
    }

    public static void resetUser(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_details", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
    public static User getUser( Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_details", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        int age = sharedPreferences.getInt("age",0);
        User user = new User(username, age);
        return user;
    }
}
