package com.example.mipo;

//import static com.example.mipo.ViewExpenses.expenseAdapter;
//import static com.example.mipo.ViewExpenses.expenseList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity implements DialogCloseListener{
    private FloatingActionButton fab;
    private ExpenseAdapter expenseAdapter;
    private List<ExpenseModel> expenseList;

    String percentString, savingMessage;
    int savingColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expenseAdapter = new ExpenseAdapter(MainActivity.this);

        //start the view expense activity

        Button button1 = findViewById(R.id.viewExpensesButton);
        button1.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ViewExpenses.class);
            view.getContext().startActivity(intent);
        });

        //start the edit device session data and budget activity
        Button button2 = findViewById(R.id.changeBudgetButton);
        button2.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SetSessionParams.class);
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
    @Override
    protected void onStart() {
        super.onStart();
        //set the variables for budget and other metrics
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        float budget = sh.getFloat("budget", 1000);
        float spend = sh.getFloat("spend", 200);
        float highestSpend = sh.getFloat("highest", 100);
        float avgSpend = sh.getFloat("avg", 70);

        String highestSpendMessage,avgSpendMessage;
        highestSpendMessage=convertToNumeral(highestSpend);
        avgSpendMessage=convertToNumeral(avgSpend);


        float percent=(float) (((budget-spend)/budget)*(100.0));
        percentString= String.format("%.2f", percent);

        if(percent>=0 && percent<=40.0){
            savingMessage="Time to save!\uD83D\uDEA8";
            savingColor=Color.RED;
        }

        else if(percent>40.0 && percent<=70.0){
            savingMessage="Smart Saver\uD83D\uDE0E";
            savingColor=Color.YELLOW;
        }
        else{
            savingMessage="Fabulous!\uD83D\uDD25";
            savingColor=Color.GREEN;
        }





        //progress bar
        CircularProgressBar circularProgressBar = (CircularProgressBar) findViewById(R.id.circularProgress);
        circularProgressBar.setProgress(50);
        circularProgressBar.setProgressColor(Color.CYAN);
        circularProgressBar.setProgress((int) percent);
        circularProgressBar.setProgressColor(savingColor);
        circularProgressBar.setTextColor(Color.BLACK);

        //set the saving message
        TextView sMessage = (TextView) findViewById (R.id.savingMessage);
        TextView leftMessage = (TextView) findViewById (R.id.savingLeftMessage);
        TextView avgMessage = (TextView) findViewById (R.id.avgMessage);
        TextView highestMessage = (TextView) findViewById (R.id.highestMessage);

        sMessage.setText(savingMessage);
        leftMessage.setText((int)(budget-spend)+"/"+(int) budget+" left");
        avgMessage.setText(avgSpendMessage);
        highestMessage.setText(highestSpendMessage);


    }

    public String convertToNumeral(float num){
        if(num<1000)
            return String.valueOf((int)num);
        else if(num>=1000 && num<100000){
            int dig=(int)(num/1000);
            return String.valueOf(dig)+"K";
        }
        else if(num>=100000){
            int dig=(int)(num/100000);
            return String.valueOf(dig)+"L";
        }
        return "--";
    }
    //to handle closing of the dialog
    @Override
    public void handleDialogClose(DialogInterface dialog){
        //expenseList = db.getAllTasks();

        expenseAdapter.notifyDataSetChanged();
    }
}
