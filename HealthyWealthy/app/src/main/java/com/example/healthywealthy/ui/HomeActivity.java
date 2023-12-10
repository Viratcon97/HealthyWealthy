package com.example.healthywealthy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.healthywealthy.databinding.ActivityHomeBinding;
import com.example.healthywealthy.utils.DataOperation;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding activityHomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_home);

        activityHomeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = activityHomeBinding.getRoot();
        setContentView(view);

        activityHomeBinding.btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigate to user profile screen
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        activityHomeBinding.btnResetUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Clear Shared preference
                DataOperation.resetUser(getApplicationContext());
                Toast.makeText(getApplicationContext(),"User reset successful!",Toast.LENGTH_LONG).show();
            }
        });

        activityHomeBinding.btnQuickHealthCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start the Quiz
                //Navigate to user profile screen
                Intent intent = new Intent(HomeActivity.this, QuestionOneActivity.class);
                startActivity(intent);
            }
        });
    }
}