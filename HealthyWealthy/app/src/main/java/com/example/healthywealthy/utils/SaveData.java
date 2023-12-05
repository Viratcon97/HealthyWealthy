package com.example.healthywealthy.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.healthywealthy.model.User;

public class SaveData {

    public static void saveUser(User user, Context context){
        if(user != null){
            SharedPreferences sharedPreferences = context.getSharedPreferences("user_details", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(user.getId(), user.getName());
            editor.commit();
        }
    }
}
