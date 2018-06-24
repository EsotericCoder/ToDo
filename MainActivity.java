package com.esotericcoder.www.todo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {

    private TaskViewModel mTaskViewModel;
    private TasksAdapter tasksAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        tasksAdapter = new TasksAdapter(new ArrayList<Task>(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(tasksAdapter);

        mTaskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);

        mTaskViewModel.getAllTasks().observe(MainActivity.this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> itemAndPeople) {
                tasksAdapter.addTasks(itemAndPeople);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date currentTime = new Date();
                Task task = new Task("Hello World", 1, currentTime);
                mTaskViewModel.insert(task);
            }
        });

    }

    @Override
    public boolean onLongClick(View view) {
        Task task = (Task) view.getTag();
        mTaskViewModel.delete(task);
        return true;
    }

}


