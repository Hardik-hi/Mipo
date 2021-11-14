package com.example.mipo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ViewExpenses extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expenses);

        DBHelper dbHelper = new DBHelper(ViewExpenses.this);

    }

}