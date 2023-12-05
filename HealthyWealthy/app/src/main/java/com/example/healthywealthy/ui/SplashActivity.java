package com.example.healthywealthy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import com.example.healthywealthy.R;
import com.example.healthywealthy.databinding.ActivityRegisterBinding;
import com.example.healthywealthy.databinding.ActivitySplashBinding;

import java.util.Timer;

public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding activitySplashBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash);

        activitySplashBinding = ActivitySplashBinding.inflate(getLayoutInflater());
        View view = activitySplashBinding.getRoot();
        setContentView(view);
        CountDownTimer timer = new CountDownTimer(3000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                //Check if user name present or not
                //If name is registered, navigate to Home Activity or else navigate to register activity

                Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        };
        timer.start();

    }
}