package com.example.eventscheduler;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface Table_Class_Dao {
    @Insert
    void inset_task(Table_class table_class);
    @Query("SELECT * FROM table_task")
    List<Table_class> get_task();
    @Query("SELECT * FROM table_task WHERE task_time LIKE :name")
    Table_class find_task(String name);
    @Update
    void update_task(Table_class table_class);
}
