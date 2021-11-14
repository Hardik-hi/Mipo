package com.example.mipo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.Collections;
import java.util.List;

public class ViewExpenses extends AppCompatActivity {

    private DatabaseHandler db;
    private RecyclerView expenseRecyclerView;
    private ExpenseAdapter expenseAdapter;
    private List<ExpenseModel> expenseList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expenses);

        expenseRecyclerView = findViewById(R.id.expenseRecyclerView);
        expenseRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        expenseAdapter = new ExpenseAdapter(db,ViewExpenses.this);
        expenseRecyclerView.setAdapter(expenseAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerItemTouchHelper(expenseAdapter));
        itemTouchHelper.attachToRecyclerView(expenseRecyclerView);

        expenseList = db.getAllTasks();
        Collections.reverse(expenseList);

        expenseAdapter.setExpenseList(expenseList);
    }
}