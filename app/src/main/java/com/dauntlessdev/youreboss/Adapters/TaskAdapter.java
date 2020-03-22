package com.dauntlessdev.youreboss.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dauntlessdev.youreboss.Model.Task;
import com.dauntlessdev.youreboss.R;
import com.dauntlessdev.youreboss.UI.TaskBottomSheet;

import java.util.List;


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>{
    LayoutInflater layoutInflater;
    List<Task> taskList;

    public TaskAdapter(Context context, List<Task> taskList){
        this.layoutInflater = LayoutInflater.from(context);
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_task,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        String taskName = taskList.get(position).getName();
        holder.name.setText(taskName);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.taskItemTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager fragmentManager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
                    TaskBottomSheet taskBottomSheet = new TaskBottomSheet();
                    taskBottomSheet.show(fragmentManager, String.valueOf(taskList.get(getAdapterPosition()).getId()));
                }
            });
        }
    }
}
