package com.example.healthywealthy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;

import com.example.healthywealthy.databinding.ActivitySplashBinding;
import com.example.healthywealthy.model.User;
import com.example.healthywealthy.utils.DataOperation;

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
                //Fetching User name from shared preference
                User userDetails = DataOperation.getUser(getApplicationContext());
                if(!TextUtils.isEmpty(userDetails.getName())) {
                    //Registered
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);

                }else{
                    //Not Registered
                    Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
                    startActivity(intent);

                }
            }
        };
        timer.start();

    }
}