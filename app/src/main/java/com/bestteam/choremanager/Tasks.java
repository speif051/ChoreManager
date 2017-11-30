package com.bestteam.choremanager;

/*import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;*/

/**
 * Created by frank on 2017-11-29.
 */

/*public class Tasks extends Fragment{

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.tasks_list, container, false);
        return myView;
    }
}*/

/*import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Tasks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

}*/

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class Tasks extends AppCompatActivity {

    EditText editTextName;
    EditText editTextNote;
    EditText editTextState;
    EditText editTextDeadline;
    Button buttonAddTask;
    ListView listViewTasks;
    DatabaseReference databaseTasks;

    List<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        databaseTasks = FirebaseDatabase.getInstance().getReference("tasks");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_list);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextNote = (EditText) findViewById(R.id.editTextNote);
        editTextState = (EditText) findViewById(R.id.editTextState);
        editTextDeadline = (EditText) findViewById(R.id.editTextDeadline);
        listViewTasks = (ListView) findViewById(R.id.listViewTasks);
        buttonAddTask = (Button) findViewById(R.id.addButton);

        tasks = new ArrayList<>();

        //adding an onclicklistener to button
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTask();
            }
        });

        listViewTasks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Task task = tasks.get(i);
                showUpdateDeleteDialog(task.getID(), task.getTaskName());
                return true;
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        databaseTasks.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                tasks.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    Task task = postSnapshot.getValue(Task.class);
                    tasks.add(task);
                }
                TaskList tasksAdaptor = new TaskList(Tasks.this, tasks);
                listViewTasks.setAdapter(tasksAdaptor);
            }
            @Override
            public void onCancelled(DatabaseError databaseError){

            }
        });
    }


    private void showUpdateDeleteDialog(final String taskID, String taskName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.task_update_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextName = (EditText) dialogView.findViewById(R.id.editTextName);
        final EditText editTextNote  = (EditText) dialogView.findViewById(R.id.editTextNote);
        final EditText editTextState  = (EditText) dialogView.findViewById(R.id.editTextState);
        final EditText editTextDeadline  = (EditText) dialogView.findViewById(R.id.editTextDeadline);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateTask);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteTask);

        dialogBuilder.setTitle(taskName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String note = editTextNote.getText().toString().trim();
                String state = editTextState.getText().toString().trim();
                String deadline = editTextDeadline.getText().toString().trim();
                if (!TextUtils.isEmpty(name)) {
                    updateTask(taskID, name, note, state, deadline);
                    b.dismiss();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteTask(taskID);
                b.dismiss();
            }
        });
    }

    private void updateTask(String id, String name, String note, String state, String deadline) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("tasks").child(id);
        Task task = new Task(id, name, note, state, deadline);
        dR.setValue(task);
        Toast.makeText(getApplicationContext(), "Task Updated", Toast.LENGTH_LONG).show();
    }

    private void deleteTask(String id) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("tasks").child(id);
        dR.removeValue();
        Toast.makeText(getApplicationContext(), "Task Deleted", Toast.LENGTH_LONG).show();
    }

    private void addTask() {
        String name = editTextName.getText().toString().trim();
        String note = editTextNote.getText().toString().trim();
        String state = editTextState.getText().toString().trim();
        String deadline = editTextDeadline.getText().toString().trim();

        if (!TextUtils.isEmpty(name)){
            String id = databaseTasks.push().getKey();
            Task task = new Task(id, name, note, state, deadline);
            databaseTasks.child(id).setValue(task);
            editTextName.setText("");
            editTextNote.setText("");
            editTextState.setText("");
            editTextDeadline.setText("");
            Toast.makeText(this, "Task added", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }
}
