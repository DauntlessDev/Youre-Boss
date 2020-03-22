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
import com.dauntlessdev.youreboss.UI.MilestoneBottomSheet;

import java.util.List;


public class MilestoneAdapter extends RecyclerView.Adapter<MilestoneAdapter.ViewHolder>{
    LayoutInflater layoutInflater;
    List<Task> taskList;

    public MilestoneAdapter(Context context, List<Task> taskList){
        this.layoutInflater = LayoutInflater.from(context);
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public MilestoneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_milestone,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MilestoneAdapter.ViewHolder holder, int position) {
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
            name = itemView.findViewById(R.id.milestoneItemTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager fragmentManager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
                    MilestoneBottomSheet milestoneBottomSheet = new MilestoneBottomSheet();
                    milestoneBottomSheet.show(fragmentManager, String.valueOf(taskList.get(getAdapterPosition()).getId()));
                }
            });
        }
    }
}
