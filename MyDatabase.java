package com.esotericcoder.www.todo;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

// bump version number if your schema changes
@Database(entities={Task.class}, version=1)
public abstract class MyDatabase extends RoomDatabase {
    // Declare your data access objects as abstract
    public abstract TaskDao taskDao();

    // Database name to be used
    public static final String NAME = "MyDataBase";
}