package com.example.timepicker;

import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SetReminder extends BroadcastReceiver {

    String text_input;
    int notificationId;
    NotificationManager notificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent mainIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, mainIntent, 0);

        notificationId = intent.getIntExtra("notificationId", 0);
        text_input = intent.getStringExtra("todo");

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Builder builder = new Builder(context);
        builder.setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentTitle("Check your App!")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentText(text_input)
                .setContentIntent(contentIntent)
                .setPriority(Notification.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_ALL);

        notificationManager.notify(notificationId, builder.build());

    }
}
