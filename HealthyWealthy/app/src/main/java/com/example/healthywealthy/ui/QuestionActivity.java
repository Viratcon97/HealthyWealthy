package com.example.healthywealthy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.healthywealthy.R;
import com.example.healthywealthy.databinding.ActivityQuestionBinding;
import com.example.healthywealthy.utils.QuestionList;

import java.util.ArrayList;
import java.util.Arrays;

public class QuestionActivity extends AppCompatActivity {



    ActivityQuestionBinding activityQuestionBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_question);

        activityQuestionBinding = ActivityQuestionBinding.inflate(getLayoutInflater());
        View view = activityQuestionBinding.getRoot();
        setContentView(view);
        init();
    }
    private void init(){

    }
}