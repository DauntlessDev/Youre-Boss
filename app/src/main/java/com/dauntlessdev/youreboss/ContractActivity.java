package com.dauntlessdev.youreboss;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dauntlessdev.youreboss.Model.Contract;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ContractActivity extends AppCompatActivity {
    Toolbar toolbar;
    String formattedDate;
    TextView titleText;
    TextView contentText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract);

        toolbar =  findViewById(R.id.contract_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Contract Details");

        titleText = findViewById(R.id.itemTitle);
        contentText = findViewById(R.id.contentTextView);

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Contract contract = new Contract(titleText.getText().toString(), contentText.getText().toString(),formattedDate);
        DatabaseHelper db = new DatabaseHelper(this);
        db.addContract(contract);
    }
}
