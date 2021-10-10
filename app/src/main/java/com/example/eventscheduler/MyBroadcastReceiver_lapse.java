package com.example.eventscheduler;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MyBroadcastReceiver_lapse extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver_lap";
    private static final String ACTION_CANCEL = "ACTION_CANCEL";
    MediaPlayer mediaPlayer;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive:here ");
        Intent intent1=new Intent(context,Cancel.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent1,0);
        Intent intent_cancel=new Intent(context,MyBroadcaster_cancel.class);
        intent_cancel.setAction("cancel");
        PendingIntent pendingIntent1=PendingIntent.getBroadcast(context,0,intent_cancel,0);
        NotificationChannel channel = new NotificationChannel("Ram", "name", NotificationManager.IMPORTANCE_HIGH);
        channel.setDescription("description");
        NotificationManager notificationManager1 = context.getSystemService(NotificationManager.class);
        notificationManager1.createNotificationChannel(channel);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Ram")
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle("Task Remainder")
                .setContentText("Heyy!!You have a task deadline in 15 minutes").addAction(R.drawable.ic_baseline_timer_24,"cancel",pendingIntent1)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(Schedule.j, builder.build());

    }

    }

