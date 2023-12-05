package com.example.healthywealthy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.healthywealthy.R;
import com.example.healthywealthy.databinding.ActivityHomeBinding;
import com.example.healthywealthy.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding activityProfileBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_profile);
        activityProfileBinding = ActivityProfileBinding.inflate(getLayoutInflater());
        View view = activityProfileBinding.getRoot();
        setContentView(view);

    }
}