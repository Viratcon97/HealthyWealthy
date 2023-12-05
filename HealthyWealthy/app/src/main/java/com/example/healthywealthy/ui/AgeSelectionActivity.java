package com.example.healthywealthy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import com.example.healthywealthy.databinding.ActivityAgeSelectionBinding;

public class AgeSelectionActivity extends AppCompatActivity {

    ActivityAgeSelectionBinding activityAgeSelectionBinding;
    int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        activityAgeSelectionBinding = ActivityAgeSelectionBinding.inflate(getLayoutInflater());
        View view = activityAgeSelectionBinding.getRoot();
        setContentView(view);
        init();
    }
    private void init(){
        age = 0;
        activityAgeSelectionBinding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                age = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                activityAgeSelectionBinding.tvShowAge.setText(String.valueOf(age));
            }
        });

        activityAgeSelectionBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigating to Question Activity
                Intent intent = new Intent(AgeSelectionActivity.this,QuestionActivity.class);
                startActivity(intent);
            }
        });

    }
}