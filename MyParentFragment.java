package com.esotericcoder.www.todo;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyParentFragment extends android.support.v4.app.Fragment implements DatePickerDialog.OnDateSetListener {

    public interface Listener {
        void onTaskReceived(Task task);
    }

    private static final String TAG = "MainFragment";

    private Button dateButton;
    private Button submitButton;
    private TextView dueDateTextView;
    private EditText taskDescription;
    private Spinner dropdown;
    private Date dueDate;
    private Listener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        dropdown = view.findViewById(R.id.priority);
        dateButton = view.findViewById(R.id.open_date_picker_dialog);
        submitButton = view.findViewById(R.id.save_task);
        dueDateTextView = view.findViewById(R.id.due_date_text);
        taskDescription = view.findViewById(R.id.newTaskDescription);
        String[] items = new String[]{"High Priority", "Normal Priority", "Low Priority"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task newTask = new Task(taskDescription.getText().toString(), dropdown.getSelectedItemPosition(), dueDate);
                if (listener != null) {
                    listener.onTaskReceived(newTask);
                }
            }
        });

        return view;
    }

    // attach to an onclick handler to show the date picker
    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment().setListener(this);
        newFragment.show(getFragmentManager(), "datePicker");
    }

    // handle the date selected
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // store the values selected into a Calendar instance
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, monthOfYear);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        dueDate = c.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("MM-dd-yyyy");
        dueDateTextView.setText(format1.format(dueDate));
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
}