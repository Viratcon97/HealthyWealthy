package com.example.healthywealthy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.healthywealthy.R;
import com.example.healthywealthy.databinding.ActivityQuestionOneBinding;
import com.example.healthywealthy.model.User;
import com.example.healthywealthy.utils.Helper;
import com.example.healthywealthy.utils.DataOperation;

import java.util.Objects;

public class QuestionOneActivity extends AppCompatActivity {

    ActivityQuestionOneBinding activityQuestionOneBinding;

    int age = 0;
    String answerOne = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_question);

        activityQuestionOneBinding = ActivityQuestionOneBinding.inflate(getLayoutInflater());
        View view = activityQuestionOneBinding.getRoot();
        setContentView(view);
        init();
    }
    private void init(){

        //Get age from shared preference
        User userDetails = DataOperation.getUser(getApplicationContext());
        age = userDetails.getAge();

        //Set Question
        if(age > 0 && age <20){
            activityQuestionOneBinding.tvQuestion.setText(Helper.one_age_1_19);
        }else if(age > 19 && age < 46){
            activityQuestionOneBinding.tvQuestion.setText(Helper.one_age_20_45);
        }else if(age > 45 && age < 66){
            activityQuestionOneBinding.tvQuestion.setText(Helper.one_age_46_65);
        }else{
            activityQuestionOneBinding.tvQuestion.setText(Helper.one_age_65);
        }

        activityQuestionOneBinding.ivBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get answers
                if(activityQuestionOneBinding.rbYes.isChecked()){
                    answerOne = getString(R.string.txtYes);
                }else if(activityQuestionOneBinding.rbNo.isChecked()){
                    answerOne = getString(R.string.txtNo);
                }

                //Check answer, if null, show error otherwise navigate further
                if(Objects.equals(answerOne, "")){
                    Toast.makeText(getApplicationContext(), R.string.errorOption, Toast.LENGTH_SHORT).show();
                }else{
                    //Navigate to second question
                    Intent intent = new Intent(QuestionOneActivity.this, QuestionTwoActivity.class);
                    intent.putExtra("answer",answerOne);
                    startActivity(intent);
                }
            }
        });

    }
}