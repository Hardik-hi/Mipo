package com.example.mipo;

//import static com.example.mipo.ViewExpenses.expenseAdapter;
//import static com.example.mipo.ViewExpenses.expenseList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity implements DialogCloseListener{
    private FloatingActionButton fab;
    private ExpenseAdapter expenseAdapter;
    private List<ExpenseModel> expenseList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expenseAdapter = new ExpenseAdapter(MainActivity.this);


        //progress bar
        CircularProgressBar circularProgressBar = (CircularProgressBar) findViewById(R.id.circularProgress);
        circularProgressBar.setProgress(50);
        circularProgressBar.setProgressColor(Color.CYAN);
        circularProgressBar.setTextColor(Color.BLACK);



        //start the view expense activity

        Button button1 = findViewById(R.id.viewExpensesButton);
        button1.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ViewExpenses.class);
            view.getContext().startActivity(intent);
        });

        //to add an expense, starts a new fragment
        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddExpense.newInstance().show(getSupportFragmentManager(), AddExpense.TAG);
            }
        });




    }

    //to handle closing of the dialog
    @Override
    public void handleDialogClose(DialogInterface dialog){
       //expenseList = db.getAllTasks();

        expenseAdapter.notifyDataSetChanged();
    }
}