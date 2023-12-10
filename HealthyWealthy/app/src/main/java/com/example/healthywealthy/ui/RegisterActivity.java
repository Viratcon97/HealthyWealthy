package com.example.healthywealthy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthywealthy.R;
import com.example.healthywealthy.databinding.ActivityQuestionBinding;
import com.example.healthywealthy.databinding.ActivityRegisterBinding;
import com.example.healthywealthy.model.User;
import com.example.healthywealthy.utils.SaveData;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding activityRegisterBinding;

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



        activityRegisterBinding.etName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEND){
                    String text = v.getText().toString();
                    if(!TextUtils.isEmpty(text)){
                        v.setText(text);
                        return  true;
                    }
                }
                return false;
            }
        });

        activityRegisterBinding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = activityRegisterBinding.etName.getText().toString();

                if(name.isEmpty()){
                    //Check if name is empty or not
                    Toast.makeText(getApplicationContext(),getString(R.string.errorEnterValidString),Toast.LENGTH_LONG).show();
                }else{
                    //Save Name to Shared preference and navigate to home activity
                    String id = String.valueOf(System.currentTimeMillis());
                    User user = new User(name);
                    SaveData.saveUser(user,getApplicationContext());
                    Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}