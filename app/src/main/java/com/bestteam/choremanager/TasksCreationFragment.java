package com.bestteam.choremanager;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by frank on 2017-11-29.
 */

public class TasksCreationFragment extends Fragment{

    //Create Instance Variables to collect data from fragment_tasks_creation.xml.xml file
    EditText editTextDeadline, editTextNote, editTextName;
    Button addButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_tasks_creation, container, false);

        //Collects the information from fragment_tasks_creation.xml.xml file
        editTextDeadline = (EditText)myView.findViewById(R.id.editTextDeadline);
        editTextNote = (EditText)myView.findViewById(R.id.editTextNote);
        editTextName = (EditText)myView.findViewById(R.id.editTextName);
        addButton = (Button)myView.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add Database Information here
                Toast.makeText(TasksCreationFragment.this.getActivity(), "Click", Toast.LENGTH_SHORT).show();
            }
        });

        return myView;
    }
}
