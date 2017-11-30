package com.bestteam.choremanager;
import java.util.Calendar;

/**
 * Created by frank on 2017-11-28.
 */

public class Task {
    private String _id;
    private String _taskname;
    private String _note;
    private String _state;
    private String _deadline;

    public Task() {
    }

    public Task(String id, String taskname, String note, String state, String deadline) {
        _id = id;
        _taskname = taskname;
        _note = note;
        _state = state;
        _deadline = deadline;
    }

    public Task(String taskname, String note, String state, String deadline) {
        _taskname = taskname;
        _note = note;
        _state = state;
        _deadline = deadline;
    }

    public void setID(String id) {
        _id = id;
    }
    public String getID() { return _id; }
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
    public void setDeadline(String date) {
        _deadline = date;
    }
    public String getDeadline() { return _deadline; }

}
