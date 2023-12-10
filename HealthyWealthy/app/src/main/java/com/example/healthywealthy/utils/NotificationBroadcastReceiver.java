package com.example.healthywealthy.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.healthywealthy.ui.HomeActivity;

public class NotificationBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            // If the device is restarted, reschedule the alarm
            ((HomeActivity) context).scheduleNotification();
        } else {
            // Trigger the notification
            ((HomeActivity) context).sendNotification();
        }
    }
}
