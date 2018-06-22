package com.esotericcoder.www.todo;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

public class MyApplication extends Application {
    MyDatabase myDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        public class RestClientApp extends Application {
            // when upgrading versions, kill the original tables by using fallbackToDestructiveMigration()
            myDatabase = Room.databaseBuilder(this, MyDatabase.class, MyDatabase.NAME).fallbackToDestructiveMigration().build();
        }

        public MyDatabase getMyDatabase() {
            return myDatabase;
        }
    }
}