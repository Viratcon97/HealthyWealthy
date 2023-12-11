package com.example.healthywealthy;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class NotificationWorker extends Worker {



        private static final String CHANNEL_ID = "CHANNEL_01";
        public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
            super(context, workerParams);
        }

        @NonNull
        @Override
        public Result doWork() {
            String notificationTitle = getInputData().getString("notificationTitle");

            // Send notification
            sendNotification(notificationTitle);

            // Indicate success
            return Result.success();
        }

        private void sendNotification(String notificationTitle) {
            // Your existing notification sending code here
            // ...

            // Example:
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
            builder.setContentTitle(notificationTitle);
            builder.setContentText("Click to open the app");
            builder.setSmallIcon(R.drawable.ic_notification);

            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplicationContext());
            if (ActivityCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            managerCompat.notify(2, builder.build());
        }


}
