package com.bestteam.choremanager;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by frank on 2017-11-28.
 */

public class Task {
    private int _id;
    private String _taskname;
    private String _note;
    private String _state;
    Date  _deadline;

    public Task() {
    }

    public Task(int id, String taskname, String note, String state, Date deadline) {
        _id = id;
        _taskname = taskname;
        _note = note;
        _state = state;
        _deadline = deadline;
    }

    public Task(String taskname, String note, String state, Date deadline) {
        _taskname = taskname;
        _note = note;
        _state = state;
        _deadline = deadline;
    }

    public void setID(int id) {
        _id = id;
    }
    public int getID() { return _id; }
    public void setTaskName(String taskname) {
        _taskname = taskname;
    }
    public String getTaskName() {
        return _taskname;
    }
    public void setNote(String note) {
        _note = note;
    }
    public String getNote() {
        return _note;
    }
    public void setState(String state) {
        _state = state;
    }
    public String getState() {
        return _state;
    }
    public void setDeadline(Date date) {
        _deadline = date;
    }
    public String getDeadline() { return _deadline.toString(); }

}
