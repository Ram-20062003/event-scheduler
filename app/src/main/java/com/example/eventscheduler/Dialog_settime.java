package com.example.eventscheduler;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static android.content.ContentValues.TAG;

public class Dialog_settime extends AppCompatActivity{
    int hour,minutes,m_15,h_15;
    private static final String TAG = "Dialog_settime";
    public static int cancel=0;
    public static List<String> list=new ArrayList<>();
    List<Table_class> arrayList=new ArrayList<>();
    EditText editText;
    TimePicker timePicker;

    String time;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        editText=(EditText)findViewById(R.id.task_name);
        timePicker=findViewById(R.id.timePicker);

        String s=editText.getText().toString();
        Log.d(TAG, "setalarm: "+cancel);


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void set_time(View view) {
        hour=timePicker.getHour();
        minutes=timePicker.getMinute();
        if(minutes<=15) {
            m_15 = minutes - 15 + 60;
           h_15=hour-1;
        }
        else
        {
            m_15=minutes-15;
            h_15=hour;
        }
        time=String.format(Locale.getDefault(),"%02d:%02d",hour,minutes);
        list.add(time);
        Log.d(TAG, String.valueOf(h_15)+m_15);
        //Table_class display_item=new Table_class(editText.getText().toString(),time);
        Table_class table_class=new Table_class(editText.getText().toString(),time,true);
        Display_item display_item1=new Display_item();
        display_item1.execute(table_class);

       setalarmfifteen();
        setalarm();

    }

    private void setalarmfifteen() {
        AlarmManager alarmManager1 =
                (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        int t=h_15*3600+m_15*60;

        Calendar calendar1 = Calendar.getInstance();
        int s=calendar1.get(Calendar.HOUR_OF_DAY)*3600+calendar1.get(Calendar.MINUTE)*60;
        Log.d(TAG, "setalarmfifteen: "+h_15+m_15);
            calendar1.setTimeInMillis(System.currentTimeMillis());
            calendar1.set(Calendar.HOUR_OF_DAY, h_15);
            calendar1.set(Calendar.MINUTE, m_15);
            Log.d(TAG, "setalarmfifteen: " + (calendar1.getTime()));
            calendar1.set(Calendar.SECOND, 0);

        if(t-s>=0) {
            Intent intent = new Intent(this, MyBroadcastReceiver_lapse.class);
            PendingIntent pendingIntent1 = PendingIntent.getBroadcast(
                    this.getApplicationContext(), Schedule.j + 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            Schedule.k++;
            alarmManager1.set(AlarmManager.RTC_WAKEUP, calendar1.getTimeInMillis(), pendingIntent1);
        }
        }
    private void setalarm() {
        AlarmManager alarmManager =
                (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE,minutes);
        calendar.set(Calendar.SECOND,0);
        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), Schedule.choose, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Schedule.choose++;
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        Log.d(TAG, "setalarm: "+cancel);
        Intent intent_move=new Intent(Dialog_settime.this,Schedule.class);
        startActivity(intent_move);
    }
    class Display_item extends AsyncTask<Table_class,Void,Void>
    {
        @Override
        protected Void doInBackground(Table_class... table_classes) {
            TableRoomDatabase.getInstance(getApplicationContext()).table_class_dao().inset_task(table_classes[0]);

            return null;
        }
    }

}

