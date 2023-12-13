package com.example.healthywealthy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.healthywealthy.R;
import com.example.healthywealthy.databinding.ActivityQuestionFifthBinding;
import com.example.healthywealthy.databinding.ActivityResultBinding;
import com.example.healthywealthy.model.User;
import com.example.healthywealthy.model.UserQuizResult;
import com.example.healthywealthy.utils.DataOperation;
import com.example.healthywealthy.utils.Helper;

import java.util.Calendar;
import java.util.Date;

public class ResultActivity extends AppCompatActivity {
    ActivityResultBinding activityResultBinding ;

    int countYes = 0;
    int countNo = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityResultBinding = ActivityResultBinding.inflate(getLayoutInflater());
        View view = activityResultBinding.getRoot();
        setContentView(view);
        init();
    }

    private void init(){

        //Get previous answers
        Intent answersIntent = getIntent();
        countYes = answersIntent.getIntExtra("yes_count",0);
        countNo = answersIntent.getIntExtra("no_count",0);

        //All No
        if(countNo == 5 && countYes == 0){
            activityResultBinding.tvResultSummary.setText(Helper.results_all_no);
            activityResultBinding.ivResults.setImageResource(R.drawable.wear_all_no);
        }else if(countYes == 5 && countNo == 0){
            //All Yes
            activityResultBinding.tvResultSummary.setText(Helper.results_all_yes);
            activityResultBinding.ivResults.setImageResource(R.drawable.wear_all_yes);
        }else if(countYes == 2 && countNo == 3){
            //2 Yes, 3 No
            activityResultBinding.tvResultSummary.setText(Helper.results_two_yes_three_no);
            activityResultBinding.ivResults.setImageResource(R.drawable.wear_two_yes_three_no);
        }else if(countYes == 4 && countNo == 1){
            //4 Yes, 1 No
            activityResultBinding.tvResultSummary.setText(Helper.results_four_yes_one_no);
            activityResultBinding.ivResults.setImageResource(R.drawable.wear_four_yes_one_no);
        }else{
            //Remaining All
            activityResultBinding.tvResultSummary.setText(Helper.results_three_yes_two_no);
            activityResultBinding.ivResults.setImageResource(R.drawable.wear_three_yes_two_no);
        }

        activityResultBinding.btnSaveResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Save results to shared preference
                Date currentTime = Calendar.getInstance().getTime();
                UserQuizResult userQuizResult = new UserQuizResult(currentTime.toString(),countYes);
                DataOperation.saveResults(userQuizResult,getApplicationContext());
            }
        });

        activityResultBinding.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigate to Home Screen
                Intent intent = new Intent(ResultActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}