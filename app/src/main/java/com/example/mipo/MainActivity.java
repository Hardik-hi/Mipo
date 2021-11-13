package com.example.mipo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et_amount, et_detail, et_remarks, et_date, et_pmode;
    Button add_btn;

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
        add_btn = (Button) findViewById(R.id.newTaskButton);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                
                TransactionModel transactionModel;
                try{
                    transactionModel = new TransactionModel(et_date.getText().toString(), et_detail.getText().toString(), et_pmode.getText().toString(), Double.parseDouble(et_amount.getText().toString()), et_remarks.getText().toString());
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "Error creating transaction", Toast.LENGTH_SHORT);
                }
            }
        });
    }
}