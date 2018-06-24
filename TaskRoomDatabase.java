package com.esotericcoder.www.todo;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

// bump version number if your schema changes
@Database(entities={Task.class}, version=1)
public abstract class TaskRoomDatabase extends RoomDatabase {

    // Declare your data access objects as abstract
    public abstract TaskDao taskDao();

    private static TaskRoomDatabase INSTANCE;

    public static TaskRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TaskRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TaskRoomDatabase.class, NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // Database name to be used
    public static final String NAME = "task_database";
}