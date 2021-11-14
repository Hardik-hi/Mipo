package com.example.mipo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ViewExpenses extends AppCompatActivity implements DialogCloseListener{

    //private DatabaseHandler db;
    private RecyclerView expenseRecyclerView;
    private ExpenseAdapter expenseAdapter;
    private List<ExpenseModel> expenseList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expenses);

        expenseList = new ArrayList<>();
        expenseRecyclerView = findViewById(R.id.expenseRecyclerView);
        expenseRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        expenseAdapter = new ExpenseAdapter(ViewExpenses.this);
        expenseRecyclerView.setAdapter(expenseAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerItemTouchHelper(expenseAdapter));
        itemTouchHelper.attachToRecyclerView(expenseRecyclerView);

        DBHelper dbHelper = new DBHelper(ViewExpenses.this);
        List<ExpenseModel> expenseModelList = dbHelper.viewAll();

//        Iterator it = expenseModelList.iterator();
//
//        while(it.hasNext()){
//            expenseList.add((ExpenseModel) it.next());
//        }
        for(int i = 0; i < expenseModelList.size(); i++){
            expenseList.add(expenseModelList.get(i));
        }

        //expenseList = db.getAllTasks();
        //Collections.reverse(expenseList);
        expenseAdapter.setExpenseList(expenseList);


    }

    //to handle closing of the dialog
    @Override
    public void handleDialogClose(DialogInterface dialog){
        //expenseList = db.getAllTasks();
        Collections.reverse(expenseList);
        expenseAdapter.setExpenseList(expenseList);
        expenseAdapter.notifyDataSetChanged();
    }
}