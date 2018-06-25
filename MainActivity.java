package com.esotericcoder.www.todo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener, MyParentFragment.Listener {

    public static final String TAG_MAIN_FRAGMENT = "MainFragment";

    private TaskViewModel mTaskViewModel;
    private TasksAdapter tasksAdapter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
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

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyParentFragment fragment = new MyParentFragment();
                fragment.setListener(MainActivity.this);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment, TAG_MAIN_FRAGMENT);
                transaction.commit();
                fab.hide();
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                    fab.show();
                }
            }
        });

    }

    @Override
    public boolean onLongClick(View view) {
        Task task = (Task) view.getTag();
        mTaskViewModel.delete(task);
        return true;
    }

    @Override
    public void onTaskReceived(Task task) {
        mTaskViewModel.insert(task);
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG_MAIN_FRAGMENT);
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            fab.show();
        }
    }
}


