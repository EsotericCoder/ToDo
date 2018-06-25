package com.esotericcoder.www.todo;

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

    public int getPriority(){
        return priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getPriorityText() {
        String textPriority;
        if (priority == 0){
            textPriority = "High";
        }else if(priority == 1){
            textPriority = "Normal";
        }else{
            textPriority = "Low";
        }
        return textPriority;
    }

    public String getRealtiveDueDate(){
        String relativeDueDate = "";
        Date today = new Date();
        if(dueDate != null){
            Long difference = dueDate.getTime() - today.getTime();
            int differenceDays;
            if (difference >= 0) {
                differenceDays = (int)(difference / (1000 * 60 * 60 * 24));
                if(differenceDays == 0){
                    relativeDueDate = "Today";
                }else if(differenceDays == 1){
                    relativeDueDate = "Tomorrow";
                }else{
                    relativeDueDate = "in " + differenceDays + " days";
                }
            }else{
                differenceDays = (int)((difference * -1) / (1000 * 60 * 60 * 24));
                if(differenceDays == 0){
                    relativeDueDate = "Yesterday";
                }else{
                    relativeDueDate = (difference * -1) + " days ago";
                }
            }
        }
        return relativeDueDate;
    }

}
