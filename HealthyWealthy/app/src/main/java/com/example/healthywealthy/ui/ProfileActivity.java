package com.example.healthywealthy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.example.healthywealthy.R;
import com.example.healthywealthy.databinding.ActivityProfileBinding;
import com.example.healthywealthy.model.User;
import com.example.healthywealthy.utils.DataOperation;

public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding activityProfileBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_profile);
        activityProfileBinding = ActivityProfileBinding.inflate(getLayoutInflater());
        View view = activityProfileBinding.getRoot();
        setContentView(view);

        init();
    }
    private void init(){

        //Fetching User name from shared preference
        User userDetails = DataOperation.getUser(getApplicationContext());
        if(!TextUtils.isEmpty(userDetails.getName())){
            activityProfileBinding.tvUserName.setText(String.format("%s%s", getString(R.string.txtUser), userDetails.getName()));
        }
        if(!TextUtils.isEmpty(String.valueOf(userDetails.getAge()))){
            activityProfileBinding.tvUserAge.setText(getString(R.string.age)+userDetails.getAge());
        }
    }
}