package com.dauntlessdev.youreboss.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dauntlessdev.youreboss.Controller.ContractActivity;
import com.dauntlessdev.youreboss.Model.Contract;
import com.dauntlessdev.youreboss.R;

import java.util.List;

public class ContractAdapter extends RecyclerView.Adapter<ContractAdapter.ViewHolder>{
    LayoutInflater layoutInflater;
    List<Contract> contractList;

    public ContractAdapter(Context context, List<Contract> contractList){
        this.layoutInflater = LayoutInflater.from(context);
        this.contractList = contractList;
    }

    @NonNull
    @Override
    public ContractAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_contract,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContractAdapter.ViewHolder holder, int position) {
        String contractTitle = contractList.get(position).getTitle();
        String contractDate = contractList.get(position).getDatetime();
        holder.title.setText(contractTitle);
        holder.date.setText(contractDate);
    }

    @Override
    public int getItemCount() {
        return contractList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.itemTitle);
            date = itemView.findViewById(R.id.itemDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), ContractActivity.class);
                    intent.putExtra("ID", contractList.get(getAdapterPosition()).getId());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}
