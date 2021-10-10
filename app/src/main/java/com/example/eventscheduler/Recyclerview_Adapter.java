package com.example.eventscheduler;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Recyclerview_Adapter extends RecyclerView.Adapter<Recyclerview_Adapter.Recyclerview_ViewHolder> {
    List<Table_class> display_itemList=new ArrayList<>();
Context context;
    private static final String TAG = "Recyclerview_Adapter";
    public Recyclerview_Adapter(List<Table_class> list)
     {
         display_itemList=list;
         notifyDataSetChanged();
     }
    @Override
    public Recyclerview_ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list,parent,false);
        Recyclerview_ViewHolder recyclerview_viewHolder=new Recyclerview_ViewHolder(view);
        return recyclerview_viewHolder;
    }

    @Override
    public void onBindViewHolder(Recyclerview_Adapter.Recyclerview_ViewHolder holder, int position) {
        Calendar calendar = Calendar.getInstance();
        String h=(calendar.getTime()).toString();
        String s=h.substring(10,16).trim();
        holder.t_task.setText(display_itemList.get(position).getTask_name());
        holder.t_time.setText(display_itemList.get(position).getTask_time());
        int hou_o=Integer.parseInt(s.substring(0,2).trim());
        int sec_o=Integer.parseInt(s.substring(3,5).trim());
        int hou_r=Integer.parseInt(display_itemList.get(position).getTask_time().substring(0,2));
        int sec_r=Integer.parseInt(display_itemList.get(position).getTask_time().substring(3,5));
        if(hou_o<hou_r) {
            holder.t_time.setTextColor(Color.BLACK);
            holder.t_task.setTextColor(Color.BLACK);
            holder.t_task.setText(display_itemList.get(position).getTask_name());
            holder.t_time.setText(display_itemList.get(position).getTask_time());
        }
        if(hou_o==hou_r&&sec_o<sec_r)
        {
            holder.t_time.setTextColor(Color.BLACK);
            holder.t_task.setTextColor(Color.BLACK);
            holder.t_task.setText(display_itemList.get(position).getTask_name());
            holder.t_time.setText(display_itemList.get(position).getTask_time());
        }
        if(hou_o>hou_r)
        {
            holder.t_time.setTextColor(Color.RED);
            holder.t_task.setTextColor(Color.RED);
            holder.t_task.setText(display_itemList.get(position).getTask_name());
            holder.t_time.setText(display_itemList.get(position).getTask_time());

        }
        if(hou_o==hou_r&&sec_o>sec_r)
        {
            holder.t_time.setTextColor(Color.RED);
            holder.t_task.setTextColor(Color.RED);
            holder.t_task.setText(display_itemList.get(position).getTask_name());
            holder.t_time.setText(display_itemList.get(position).getTask_time());

        }
        Log.d(TAG, "onBindViewHolder: "+s +display_itemList.get(position).getTask_time());



    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: "+display_itemList.size());
        return display_itemList.size();

    }

    class Recyclerview_ViewHolder extends RecyclerView.ViewHolder{
        TextView t_time,t_task;
        public Recyclerview_ViewHolder(View itemView) {
            super(itemView);
            t_time=itemView.findViewById(R.id.time);
            t_task=itemView.findViewById(R.id.text);
        }
    }
}
