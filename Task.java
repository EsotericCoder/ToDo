package com.esotericcoder.www.todo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

@Entity
public class Task {

    @PrimaryKey(autoGenerate=true)
    Long id;
    private String description;
    @TypeConverters(DateConverter.class)
    private Date dueDate;
    private int priority;

    public Task(String description, int priority, Date dueDate) {
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

}
