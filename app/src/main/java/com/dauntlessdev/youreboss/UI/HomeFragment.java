package com.dauntlessdev.youreboss.UI;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dauntlessdev.youreboss.Adapters.ContractAdapter;
import com.dauntlessdev.youreboss.Adapters.TaskAdapter;
import com.dauntlessdev.youreboss.Controller.DatabaseHelper;
import com.dauntlessdev.youreboss.Model.Task;
import com.dauntlessdev.youreboss.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HomeFragment extends Fragment {
    DatabaseHelper db;
    List<Task> taskList;
    TaskAdapter taskAdapter;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        db = new DatabaseHelper(getActivity());


        taskList = db.getAllTask();
        taskAdapter = new TaskAdapter(getActivity(), taskList);
        recyclerView = root.findViewById(R.id.taskRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(taskAdapter);

        FloatingActionButton fab = root.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskBottomSheet taskBottomSheet = new TaskBottomSheet();
                taskBottomSheet.show(getFragmentManager(), "taskBottomSheet");

            }
        });


        return root;
    }


    @Override
    public void onResume() {
        super.onResume();

    }
}
