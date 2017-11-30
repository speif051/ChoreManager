package com.bestteam.choremanager;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

//Copied from Tasks
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

public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Copied from Tasks
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Copied from Tasks
        databaseTasks = FirebaseDatabase.getInstance().getReference("tasks");
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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_tasks_list) {
            //fragmentManager.beginTransaction().replace(R.id.content_navigation, new Tasks()).commit();
            startActivity(new Intent(Navigation.this, Tasks.class));
        } else if (id == R.id.nav_people_list) {
            fragmentManager.beginTransaction().replace(R.id.content_navigation, new People()).commit();
        } else if (id == R.id.nav_resource_list) {
            fragmentManager.beginTransaction().replace(R.id.content_navigation, new Resource()).commit();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Copied from Tasks
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
                TaskList tasksAdaptor = new TaskList(Navigation.this, tasks);
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
