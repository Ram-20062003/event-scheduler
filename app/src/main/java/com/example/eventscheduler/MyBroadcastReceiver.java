package com.example.eventscheduler;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Calendar;
import java.util.List;

public class MyBroadcastReceiver extends BroadcastReceiver {
    MediaPlayer mediaPlayer;
    String time,content="",yes;
    Context context1;
    private static final String TAG = "MyBroadcastReceiver";
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: sucess");

            Calendar calendar = Calendar.getInstance();
            String h = (calendar.getTime()).toString();
            String s = h.substring(10, 16).trim();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Table_class table_class = TableRoomDatabase.getInstance(context).table_class_dao().find_task(s);
                    Log.d(TAG, "run: "+table_class);
                    if (table_class.getTask_time().trim().equals(s.trim())&&table_class.isTask_cancel()) {
                        mediaPlayer = MediaPlayer.create(context, R.raw.alarm);
                        mediaPlayer.start();
                        if (!mediaPlayer.isPlaying())
                            mediaPlayer.release();
                        time = "TIMES UP:" + table_class.getTask_time();
                        content = "Task name:" + table_class.getTask_name();
                        NotificationChannel channel = new NotificationChannel("Ram", "name", NotificationManager.IMPORTANCE_HIGH);
                        channel.setDescription("description");
                        NotificationManager notificationManager1 = context.getSystemService(NotificationManager.class);
                        notificationManager1.createNotificationChannel(channel);
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Ram")
                                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                                .setContentTitle(time)
                                .setContentText(content)
                                .setPriority(NotificationCompat.PRIORITY_HIGH);
                        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
                        notificationManager.notify(Schedule.j, builder.build());
                    }

                }
            });
            thread.start();
        }

}

