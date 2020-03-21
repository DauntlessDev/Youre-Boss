package com.dauntlessdev.youreboss.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dauntlessdev.youreboss.Adapters.ContractAdapter;
import com.dauntlessdev.youreboss.ContractActivity;
import com.dauntlessdev.youreboss.DatabaseHelper;
import com.dauntlessdev.youreboss.Model.Contract;
import com.dauntlessdev.youreboss.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ContractFragment extends Fragment {
    RecyclerView recyclerView;
    ContractAdapter contractAdapter;
    List<Contract> contractList;


    DatabaseHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_contract, container, false);
        db = new DatabaseHelper(getActivity());

        final Intent intent = new Intent(getActivity(), ContractActivity.class);

        FloatingActionButton fab = root.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        db.close();
        super.onDestroyView();
    }

    @Override
    public void onResume() {

        contractList = db.getAllContract();
        contractAdapter = new ContractAdapter(getActivity(), contractList);
        recyclerView = getView().findViewById(R.id.listofContracts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(contractAdapter);
        super.onResume();
    }
}
