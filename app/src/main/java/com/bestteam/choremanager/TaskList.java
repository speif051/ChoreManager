package com.bestteam.choremanager;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by frank on 2017-11-29.
 */

public class TaskList extends ArrayAdapter<Task> {
    private Activity context;
    List<Task> tasks;

    public TaskList(Activity context, List<Task> tasks) {
        super(context, R.layout.layout_task_list, tasks);
        this.context = context;
        this.tasks = tasks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_task_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewNote = (TextView) listViewItem.findViewById(R.id.textViewNote);
        TextView textViewState = (TextView) listViewItem.findViewById(R.id.textViewState);
        TextView textViewDeadline = (TextView) listViewItem.findViewById(R.id.textViewDeadline);

        Task task = tasks.get(position);
        textViewName.setText(task.getTaskName());
        textViewNote.setText(task.getNote());
        textViewName.setText(task.getState());
        textViewNote.setText(task.getDeadline());
        return listViewItem;
    }
}
