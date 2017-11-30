package com.bestteam.choremanager;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
/**
 * Firebase Libraries Added by Mike Nov 30 2017
 */
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by frank on 2017-11-29.
 */

public class TasksCreationFragment extends Fragment{

    /**
     * Setting up database, instance of DatabaseReference
     */
    DatabaseReference database;

    //Create Instance Variables to collect data from fragment_tasks_creation.xml.xml file
    EditText editTextDeadline, editTextNote, editTextName;
    DatePicker datePicker;
    Date deadline;

    Button addButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_tasks_creation, container, false);
        //Database Reference
        database = FirebaseDatabase.getInstance().getReference("Tasks");

        //Collects the information from fragment_tasks_creation.xml.xml file
        datePicker = (DatePicker)myView.findViewById(R.id.deadline);
        deadline = new Date(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        editTextNote = (EditText)myView.findViewById(R.id.editTextNote);
        final  String note = editTextNote.getText().toString();
        editTextName = (EditText)myView.findViewById(R.id.editTextName);
        final String name = editTextName.getText().toString();
        addButton = (Button)myView.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add Database Information here
                Task task = new Task(0, name, note, "active", deadline);
                Toast.makeText(TasksCreationFragment.this.getActivity(), "Click", Toast.LENGTH_SHORT).show();
            }
        });

        return myView;
    }
}
