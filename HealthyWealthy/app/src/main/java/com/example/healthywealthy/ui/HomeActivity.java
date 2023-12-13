package com.example.healthywealthy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.healthywealthy.R;
import com.example.healthywealthy.databinding.ActivityHomeBinding;
import com.example.healthywealthy.model.User;
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

        //Fetch details to check if user is exists or not
        User userDetails = DataOperation.getUser(getApplicationContext());

        activityHomeBinding.btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if user details is exist or not
                if(!userDetails.getName().isEmpty()){
                    //Navigate to user profile screen
                    Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), R.string.error_user_not_exists,Toast.LENGTH_LONG).show();
                }
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
    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }
}