package com.bestteam.choremanager;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Mike on 2017-11-30.
 */

public class TasksListFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_tasks_list, container, false);
        return myView;
    }
}

