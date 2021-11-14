package com.example.mipo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    Button btn_view, btn_budget;
    TextView tw_avg, tw_highest, tw_comm, tw_avail;
    private FloatingActionButton fab;

    public void openExpensesActivity(){
        Intent intent = new Intent(this, ViewExpenses.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //progress bar
        CircularProgressBar circularProgressBar = (CircularProgressBar) findViewById(R.id.circularProgress);
        circularProgressBar.setProgress(50);
        circularProgressBar.setProgressColor(Color.CYAN);
        circularProgressBar.setTextColor(Color.BLACK);

        btn_view = (Button) findViewById(R.id.viewExpensesButton);
        tw_avg = (TextView)findViewById(R.id.textView3);
        tw_highest = (TextView) findViewById(R.id.textView4);
        tw_comm = (TextView) findViewById(R.id.textView);
        tw_avail = (TextView) findViewById(R.id.textView2);
        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddExpense.newInstance().show(getSupportFragmentManager(), AddExpense.TAG);
            }
        });
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExpensesActivity();
            }
        });
    }
}