package com.esotericcoder.www.todo;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task")
    LiveData<List<Task>> getAllTasks();

    @Query("select * from task where id = :id")
    Task getItembyId(Long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insert(Task task);

    @Delete
    public void delete(Task task);
}