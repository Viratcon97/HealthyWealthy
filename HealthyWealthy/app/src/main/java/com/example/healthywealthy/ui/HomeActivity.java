package com.example.healthywealthy.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.Manifest;

import com.example.healthywealthy.NotificationWorker;
import com.example.healthywealthy.R;
import com.example.healthywealthy.databinding.ActivityHomeBinding;
import com.example.healthywealthy.utils.SaveData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding activityHomeBinding;
    private static final String CHANNEL_ID = "CHANNEL_01";
    private static final int NOTIFICATION_ID = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_home);

        activityHomeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = activityHomeBinding.getRoot();
        setContentView(view);

        

        createNotificationChannel();
        scheduleNotificationWithWorkManager();
//        scheduleNotification();

        activityHomeBinding.btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigate to user profile screen
                Intent intent = new Intent(HomeActivity.this, GraphActivity.class);
                startActivity(intent);
            }
        });

        activityHomeBinding.btnResetUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Clear Shared preference
                SaveData.resetUser(getApplicationContext());
                Toast.makeText(getApplicationContext(),"User reset successful!",Toast.LENGTH_LONG).show();
            }
        });

        activityHomeBinding.btnQuickHealthCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start the Quiz
            }
        });
    }

    private void scheduleNotificationWithWorkManager() {
        // Set the time for the notification (in this case, 8 am)
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);

        long delay = calendar.getTimeInMillis() - System.currentTimeMillis();

        DateFormat simpleDelay = new SimpleDateFormat("HH:mm:ss");
        Date result = new Date(delay);


        // Create a Data object to pass any necessary data to the worker
        Data inputData = new Data.Builder().putString("notificationTitle", "Your Static Title").build();

//        // Create a OneTimeWorkRequest
//        OneTimeWorkRequest notificationWork = new OneTimeWorkRequest.Builder(NotificationWorker.class)
//                .setInputData(inputData)
//                .setInitialDelay(delay, TimeUnit.MILLISECONDS)
//                .build();

        // Create a PeriodicWorkRequest to repeat every day
        PeriodicWorkRequest notificationWork = new PeriodicWorkRequest.Builder(
                NotificationWorker.class,
                1, // repeat interval, in days
                TimeUnit.DAYS
        )
                .setInputData(inputData)
                .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                .build();

        // Enqueue the work request
        WorkManager.getInstance(this).enqueue(notificationWork);
        Toast.makeText(getApplicationContext()," work scheduled"+simpleDelay.format(result),Toast.LENGTH_LONG).show();

    }

//    public void scheduleNotification() {
//        // Set the time for the notification (in this case, 8 am)
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, 15);
//        calendar.set(Calendar.MINUTE, 6);
//        calendar.set(Calendar.SECOND, 0);
//
//        // Create an Intent for the BroadcastReceiver
//        Intent intent = new Intent(this, NotificationBroadcastReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(
//                this, NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        // Get the AlarmManager service
//        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//
//        // Set the alarm to trigger at the specified time
//        alarmManager.setRepeating(
//                AlarmManager.RTC_WAKEUP,
//                calendar.getTimeInMillis(),
//                AlarmManager.INTERVAL_DAY,
//                pendingIntent
//        );
//        Toast.makeText(getApplicationContext(),"scheduled at" + calendar.getTimeInMillis() ,Toast.LENGTH_LONG).show();
//    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelName = "Channel Name";
            String channelDescription = "Team Notification Channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
            channel.setDescription(channelDescription);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            Toast.makeText(getApplicationContext()," created channel successfullt",Toast.LENGTH_LONG).show();
        }
    }


    public void sendNotification() {
// Create an intent to open the app when the notification is clicked
        Intent intent = new Intent(this, HomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        String notificationTitle = "Your Static Title"; // Replace with your desired title
        String notificationText = "Click to open the app";

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setContentTitle(notificationTitle);
        builder.setContentText(notificationText);
        builder.setSmallIcon(R.drawable.ic_notification);
        builder.setContentIntent(pendingIntent);

        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 5);
            return;
        }
        managerCompat.notify(2, builder.build());

        Toast.makeText(getApplicationContext(),"sent the notifictaion ",Toast.LENGTH_LONG).show();

    }
}