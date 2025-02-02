package com.dauntlessdev.youreboss.UI;

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
import com.dauntlessdev.youreboss.Controller.ContractActivity;
import com.dauntlessdev.youreboss.Controller.DatabaseHelper;
import com.dauntlessdev.youreboss.Model.Contract;
import com.dauntlessdev.youreboss.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ContractFragment extends Fragment {
    public static RecyclerView recyclerView;
    public static ContractAdapter contractAdapter;
    public static List<Contract> contractList;


    DatabaseHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_contract, container, false);
        db = new DatabaseHelper(getActivity());

        //for recycler view setup on create
        contractList = db.getAllContract();
        contractAdapter = new ContractAdapter(getActivity(), contractList);
        recyclerView = root.findViewById(R.id.listofContracts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(contractAdapter);

        //for fab, clicking it will make you add a new contract
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
        // on finish of using database close it
        db.close();
        super.onDestroyView();
    }

}
