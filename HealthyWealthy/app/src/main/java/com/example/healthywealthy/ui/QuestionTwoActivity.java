package com.example.healthywealthy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthywealthy.R;
import com.example.healthywealthy.databinding.ActivityQuestionTwoBinding;
import com.example.healthywealthy.model.User;
import com.example.healthywealthy.utils.Helper;
import com.example.healthywealthy.utils.DataOperation;

import java.util.Objects;

public class QuestionTwoActivity extends AppCompatActivity {



    ActivityQuestionTwoBinding activityQuestionTwoBinding;

    int age = 0;
    String answerTwo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_question);

        activityQuestionTwoBinding = ActivityQuestionTwoBinding.inflate(getLayoutInflater());
        View view = activityQuestionTwoBinding.getRoot();
        setContentView(view);
        init();
    }
    private void init(){

        //Get age from shared preference
        User userDetails = DataOperation.getUser(getApplicationContext());
        age = userDetails.getAge();

        //Set Question
        if(age > 0 && age <20){
            activityQuestionTwoBinding.tvQuestion.setText(Helper.two_age_1_19);
        }else if(age > 19 && age < 46){
            activityQuestionTwoBinding.tvQuestion.setText(Helper.two_age_20_45);
        }else if(age > 45 && age < 66){
            activityQuestionTwoBinding.tvQuestion.setText(Helper.two_age_46_65);
        }else{
            activityQuestionTwoBinding.tvQuestion.setText(Helper.two_age_65);
        }

        activityQuestionTwoBinding.ivBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get previous answers

                Intent answersIntent = getIntent();
                String answerOne = answersIntent.getStringExtra("answer");
                int countYes = 0;
                int countNo = 0;
                if(Objects.equals(answerOne, getString(R.string.txtYes))){
                    countYes = countYes + 1;
                }else{
                    countNo = countNo + 1;
                }

                //Get answers
                if(activityQuestionTwoBinding.rbYes.isChecked()){
                    answerTwo = getString(R.string.txtYes);
                }else if(activityQuestionTwoBinding.rbNo.isChecked()){
                    answerTwo = getString(R.string.txtNo);
                }

                if(Objects.equals(answerTwo, getString(R.string.txtYes))){
                    countYes = countYes + 1;
                }else{
                    countNo = countNo + 1;
                }

                //Check answer, if null, show error otherwise navigate further
                if(Objects.equals(answerTwo, "")){
                    Toast.makeText(getApplicationContext(), R.string.errorOption, Toast.LENGTH_SHORT).show();
                }else{
                    //Navigate to third question
                    Intent intent = new Intent(QuestionTwoActivity.this, QuestionThreeActivity.class);
                    intent.putExtra("yes_count",countYes);
                    intent.putExtra("no_count",countNo);
                    startActivity(intent);
                }
            }
        });

    }
}