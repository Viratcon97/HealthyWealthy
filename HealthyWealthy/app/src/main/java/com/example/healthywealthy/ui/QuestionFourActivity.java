package com.example.healthywealthy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthywealthy.R;
import com.example.healthywealthy.databinding.ActivityQuestionFourBinding;
import com.example.healthywealthy.model.User;
import com.example.healthywealthy.utils.Helper;
import com.example.healthywealthy.utils.DataOperation;

import java.util.Objects;

public class QuestionFourActivity extends AppCompatActivity {


    ActivityQuestionFourBinding activityQuestionFourBinding ;

    int age = 0;
    String answerFour = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_question);

        activityQuestionFourBinding = ActivityQuestionFourBinding.inflate(getLayoutInflater());
        View view = activityQuestionFourBinding.getRoot();
        setContentView(view);
        init();
    }
    private void init(){

        //Get age from shared preference
        User userDetails = DataOperation.getUser(getApplicationContext());
        age = userDetails.getAge();

        //Set Question
        if(age > 0 && age <20){
            activityQuestionFourBinding.tvQuestion.setText(Helper.four_age_1_19);
        }else if(age > 19 && age < 46){
            activityQuestionFourBinding.tvQuestion.setText(Helper.four_age_20_45);
        }else if(age > 45 && age < 66){
            activityQuestionFourBinding.tvQuestion.setText(Helper.four_age_46_65);
        }else{
            activityQuestionFourBinding.tvQuestion.setText(Helper.four_age_65);
        }

        activityQuestionFourBinding.ivBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get previous answers
                Intent answersIntent = getIntent();
                int countYes = answersIntent.getIntExtra("yes_count",0);
                int countNo = answersIntent.getIntExtra("no_count",0);


                //Get answers
                if(activityQuestionFourBinding.rbYes.isChecked()){
                    answerFour = getString(R.string.txtYes);
                }else if(activityQuestionFourBinding.rbNo.isChecked()){
                    answerFour = getString(R.string.txtNo);
                }

                if(Objects.equals(answerFour, getString(R.string.txtYes))){
                    countYes = countYes + 1;
                }else{
                    countNo = countNo + 1;
                }

                //Check answer, if null, show error otherwise navigate further
                if(Objects.equals(answerFour, "")){
                    Toast.makeText(getApplicationContext(), R.string.errorOption, Toast.LENGTH_SHORT).show();
                }else{
                    //Navigate to fifth question
                    Intent intent = new Intent(QuestionFourActivity.this, QuestionFifthActivity.class);
                    intent.putExtra("yes_count",countYes);
                    intent.putExtra("no_count",countNo);
                    startActivity(intent);
                }
            }
        });

    }
}