package com.example.healthywealthy.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.Manifest;

import com.example.healthywealthy.R;
import com.example.healthywealthy.databinding.ActivityHomeBinding;
import com.example.healthywealthy.utils.NotificationBroadcastReceiver;
import com.example.healthywealthy.utils.SaveData;

import java.util.Calendar;

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
        scheduleNotification();

        activityHomeBinding.btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigate to user profile screen
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
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

    public void scheduleNotification() {
        // Set the time for the notification (in this case, 8 am)
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 2);
        calendar.set(Calendar.MINUTE, 51);
        calendar.set(Calendar.SECOND, 0);

        // Create an Intent for the BroadcastReceiver
        Intent intent = new Intent(this, NotificationBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this, NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Get the AlarmManager service
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        // Set the alarm to trigger at the specified time
        alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,
                pendingIntent
        );
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelName = "Channel Name";
            String channelDescription = "Team Notification Channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
            channel.setDescription(channelDescription);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
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

    }
}