package com.esotericcoder.www.todo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TaskViewHolder> {

    private List<Task> mTasks; // Cached copy of words
    private View.OnLongClickListener longClickListener;

    public TasksAdapter(List<Task> mTasks, View.OnLongClickListener longClickListener) {
        this.mTasks = mTasks;
        this.longClickListener = longClickListener;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TaskViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false));
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        Task task = mTasks.get(position);
        holder.taskText.setText(task.getDescription());
        holder.taskDue.setText("Due: " + task.getDueDate());
        holder.itemView.setTag(task);
        holder.itemView.setOnLongClickListener(longClickListener);
    }

    void addTasks(List<Task> tasks){
        mTasks = tasks;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mTasks != null)
            return mTasks.size();
        else return 0;
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        private TextView taskText;
        private TextView taskDue;


        private TaskViewHolder(View itemView) {
            super(itemView);
            taskText = itemView.findViewById(R.id.taskText);
            taskDue = itemView.findViewById(R.id.taskDue);
        }
    }
}