package com.example.eventscheduler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;
import java.util.Locale;

public class MyBroadcaster_cancel extends BroadcastReceiver {
    private static final String TAG = "MyBroadcaster_cancel";
    @Override
    public void onReceive(Context context, Intent intent) {
        Calendar calendar = Calendar.getInstance();
        String h = (calendar.getTime()).toString();
        String s = h.substring(10, 16).trim();
        String t;
        int hour= Integer.parseInt(s.substring(0,2));
        int second= Integer.parseInt(s.substring(3));
        if(second>=45)
        {
            second=second+15-60;
            hour=hour+1;
            t=String.format(Locale.getDefault(),"%02d:%02d",hour,second);

        }
        else{
            second=second+15;
            t=String.format(Locale.getDefault(),"%02d:%02d",hour,second);

        }
        Log.d(TAG, "onReceive: "+second);
        if(intent.getAction().equals("cancel"))
        {
            Log.d(TAG, "onReceive: cancelled");
            Dialog_settime.cancel=1;
            Log.d(TAG, "onReceive: cancelled"+Dialog_settime.cancel);
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    Table_class table_class=TableRoomDatabase.getInstance(context).table_class_dao().find_task(t);
                    table_class.setTask_cancel(false);
                    TableRoomDatabase.getInstance(context).table_class_dao().update_task(table_class);
                }
            });
            thread.start();
        }
    }
}
