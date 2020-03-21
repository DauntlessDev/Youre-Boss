package com.dauntlessdev.youreboss;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.dauntlessdev.youreboss.Controller.HomeFragment;
import com.dauntlessdev.youreboss.Model.Contract;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ContractActivity extends AppCompatActivity {
    Toolbar toolbar;
    String formattedDate;
    TextView titleText;
    TextView contentText;
    DatabaseHelper db;
    int id;
    Contract contract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract);
        db = new DatabaseHelper(this);

        //set up the toolbar
        toolbar =  findViewById(R.id.contract_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Contract Details");

        //bind to layout
        titleText = findViewById(R.id.itemTitle);
        contentText = findViewById(R.id.contentTextView);

        /*try to get intent from recycle view
        * if it has then put the content in the contract
        * else don't and add a new contract*/
        Intent intent = getIntent();
        id = intent.getIntExtra("ID",-1);
        if (id != -1) {
            contract = db.getSingleContract(id);
            titleText.setText(contract.getTitle());
            contentText.setText(contract.getContent());
        }else{
            Toast.makeText(this, "Adding new contract.", Toast.LENGTH_SHORT).show();
        }



        //get current month and date
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a", Locale.getDefault());
        formattedDate = df.format(Calendar.getInstance().getTime());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.contract_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // if contract is not new delete, if not then just cancel adding
            case R.id.nav_delete:
                if(id != -1){
                    db.deleteContract(id);
                    Toast.makeText(this, "Contract is deleted.", Toast.LENGTH_SHORT).show();
                }finish();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // if contract is not new, update that contract, if not then add a new contract in db
        if (id != -1){
            contract.setContent(contentText.getText().toString());
            contract.setTitle(titleText.getText().toString());
            db.updateContract(contract);
        }else{
            contract = new Contract(titleText.getText().toString(), contentText.getText().toString(),formattedDate);
            db.addContract(contract);

        }
    }
}
