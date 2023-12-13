package com.example.healthywealthy.model;
import java.util.Calendar;
import java.util.Date;
public class UserQuizResult {

    String date;
    int result;

    public UserQuizResult(String date, int result) {
        this.date = date;
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
