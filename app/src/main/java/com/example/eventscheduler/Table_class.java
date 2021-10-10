package com.example.eventscheduler;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_task")
public class Table_class {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "task_name")
    private String task_name;
    @ColumnInfo(name ="task_time")
    private String task_time;
    @ColumnInfo(name = "cancel")
    private boolean task_cancel;

    public Table_class(String task_name, String task_time, boolean task_cancel) {
        this.task_name = task_name;
        this.task_time = task_time;
        this.task_cancel = task_cancel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_time() {
        return task_time;
    }

    public void setTask_time(String task_time) {
        this.task_time = task_time;
    }

    public boolean isTask_cancel() {
        return task_cancel;
    }

    public void setTask_cancel(boolean task_cancel) {
        this.task_cancel = task_cancel;
    }

    @Override
    public String toString() {
        return "Table_class{" +
                "id=" + id +
                ", task_name='" + task_name + '\'' +
                ", task_time='" + task_time + '\'' +
                ", task_cancel=" + task_cancel +
                '}';
    }
}
