package com.dauntlessdev.youreboss.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dauntlessdev.youreboss.Adapters.MilestoneAdapter;
import com.dauntlessdev.youreboss.Adapters.TaskAdapter;
import com.dauntlessdev.youreboss.Controller.DatabaseHelper;
import com.dauntlessdev.youreboss.Model.Task;
import com.dauntlessdev.youreboss.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class MilestoneBottomSheet extends BottomSheetDialogFragment {
    Task task;
    DatabaseHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.bottomsheet_task,container,false);

        db = new DatabaseHelper(getActivity());
        final TextView textView = root.findViewById(R.id.taskdescriptionText);
        final int id = Integer.parseInt(getTag());

        // if exists, set the textview text to the task content/name
        if(id != -1){
            task = db.getSingleTask(id , 2);
            textView.setText(task.getName());
        }

        Button button = root.findViewById(R.id.doneButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // if id doesnot exist in data base, add but if it exist update
                if (id != -1){
                    task.setName(textView.getText().toString());
                    db.updateTask(task, 2);
                }
                else{
                    task = new Task(textView.getText().toString());
                    db.addTask(task,2);
                }
                dismiss();

            }
        });

        ImageButton imageButton = root.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if exists delete, else just dismiss
                if(id != -1){
                    db.deleteTask(id,2);
                }

                dismiss();
            }
        });
        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //must be changed someday, used to updated recycler view because notifydatasetchange doessnot work
        MilestoneFragment.taskList = db.getAllTask(2);
        MilestoneFragment.milestoneAdapter = new MilestoneAdapter(getActivity(), MilestoneFragment.taskList);
        MilestoneFragment.recyclerView.setAdapter(MilestoneFragment.milestoneAdapter);
    }
}
