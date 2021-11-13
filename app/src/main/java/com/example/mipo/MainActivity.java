package com.example.mipo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et_amount, et_detail, et_remarks, et_date;
    Button add_btn, date_btn;
    Spinner spin_method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CircularProgressBar circularProgressBar = (CircularProgressBar) findViewById(R.id.circularProgress);
        circularProgressBar.setProgress(50);
        circularProgressBar.setProgressColor(Color.CYAN);
        circularProgressBar.setTextColor(Color.BLACK);

        et_amount = (EditText) findViewById(R.id.newExpenseAmount);
        et_detail = (EditText) findViewById(R.id.newExpensePerson);
        et_remarks = (EditText) findViewById(R.id.newExpenseRemark);
        add_btn = (Button) findViewById(R.id.newExpenseButton);
        spin_method = (Spinner) findViewById(R.id.newExpenseMethod);
        date_btn = (Button) findViewById(R.id.newExpenseDate);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                
                TransactionModel transactionModel;
                try{
                    transactionModel = new TransactionModel(et_date.getText().toString(), et_detail.getText().toString(), spin_method.getSelectedItem().toString(), Double.parseDouble(et_amount.getText().toString()), et_remarks.getText().toString());
                    Toast.makeText(MainActivity.this, "Successfully created new transaction", Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "Error creating transaction", Toast.LENGTH_SHORT).show();
                    transactionModel = new TransactionModel("null", "failed", "null", -1.0, "invalid");
                }

                DBHelper dbhelper = new DBHelper(MainActivity.this);

                boolean success = dbhelper.add_data(transactionModel);

                if(success == true) {
                    Toast.makeText(MainActivity.this, "Successfully added transaction", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(MainActivity.this, "Failed to add transaction", Toast.LENGTH_SHORT).show();
            }
        });
    }
}