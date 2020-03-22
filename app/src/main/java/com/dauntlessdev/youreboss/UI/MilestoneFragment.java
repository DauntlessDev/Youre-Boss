package com.dauntlessdev.youreboss.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dauntlessdev.youreboss.Adapters.MilestoneAdapter;
import com.dauntlessdev.youreboss.Adapters.TaskAdapter;
import com.dauntlessdev.youreboss.Controller.DatabaseHelper;
import com.dauntlessdev.youreboss.Model.Task;
import com.dauntlessdev.youreboss.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MilestoneFragment extends Fragment {

    DatabaseHelper db;
    static List<Task> taskList;
    static MilestoneAdapter milestoneAdapter;
    static RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_milestone, container, false);

        db = new DatabaseHelper(getActivity());

        //set up the recycler view
        taskList = db.getAllTask(2);
        milestoneAdapter = new MilestoneAdapter(getActivity(), taskList);
        recyclerView = root.findViewById(R.id.milestoneRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(milestoneAdapter);

        //fab click listener
        FloatingActionButton fab = root.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MilestoneBottomSheet milestoneBottomSheet = new MilestoneBottomSheet();
                milestoneBottomSheet.show(getFragmentManager(), "-1");

            }
        });
        return root;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
