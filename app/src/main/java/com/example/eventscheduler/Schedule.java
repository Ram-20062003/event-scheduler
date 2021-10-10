package com.example.eventscheduler;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class Schedule extends AppCompatActivity {
    RecyclerView recyclerView;
    public static int choose=0;
    private static final String TAG = "Schedule";
    List<Table_class> arrayList=new ArrayList<>();
    public static int j=0;
    public static int k=1000;
    FloatingActionButton fab;
    Recyclerview_Adapter recyclerview_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scheduler_view);
        recyclerView=findViewById(R.id.recycler_view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#ffc745"));
        }
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                arrayList=TableRoomDatabase.getInstance(getApplicationContext()).table_class_dao().get_task();
                Log.d(TAG, "run: "+arrayList);
                recyclerview_adapter=new Recyclerview_Adapter(arrayList);
                recyclerview_adapter.notifyDataSetChanged();
                recyclerView.setAdapter(recyclerview_adapter);

            }
        });
        thread.start();


        fab=findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Schedule.this,Dialog_settime.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
