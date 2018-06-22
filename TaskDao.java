package com.esotericcoder.www.todo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insertTask(Task task);

    @Delete
    public void deleteTask(Task task);
}