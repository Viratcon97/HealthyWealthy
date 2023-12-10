package com.example.healthywealthy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.healthywealthy.R;
import com.example.healthywealthy.databinding.ActivityRegisterBinding;
import com.example.healthywealthy.model.User;
import com.example.healthywealthy.utils.DataOperation;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding activityRegisterBinding;

    int age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_register);

        activityRegisterBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = activityRegisterBinding.getRoot();
        setContentView(view);
        init();
    }
    private void init(){

        activityRegisterBinding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = activityRegisterBinding.etName.getText().toString();
                int age = Integer.parseInt(activityRegisterBinding.tvShowAge.getText().toString());
                if(name.isEmpty() || age == 0){
                    //Check if name is empty or not
                    Toast.makeText(getApplicationContext(),getString(R.string.errorEnterValidString),Toast.LENGTH_LONG).show();
                }else{
                    //Save Name to Shared preference and navigate to home activity
                    User user = new User(name, age);
                    DataOperation.saveUser(user,getApplicationContext());
                    Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });

        age = 0;
        activityRegisterBinding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                age = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                activityRegisterBinding.tvShowAge.setText(String.valueOf(age));
            }
        });

    }
}