package com.example.healthywealthy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.healthywealthy.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    ActivityHomeBinding activityHomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_home);

        activityHomeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = activityHomeBinding.getRoot();
        setContentView(view);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == activityHomeBinding.btnQuickHealthCheck.getId()){
            //Start the Quiz

        }else {
            //Clear Shared preference
        }
    }
}