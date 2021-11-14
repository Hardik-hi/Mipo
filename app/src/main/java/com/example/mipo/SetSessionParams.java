package com.example.mipo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class SetSessionParams extends AppCompatActivity {
    EditText budget,startDate,endDate;
    Button saveButton,cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_session_params);

        budget = (EditText) findViewById(R.id.sessionParamsBudget);
        startDate = (EditText) findViewById(R.id.sessionParamsStartDate);
        endDate = (EditText) findViewById(R.id.sessionParamsEndDate);
        saveButton = (Button) findViewById(R.id.saveButton);

        //extracting from shared pref
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        Float s1 = sh.getFloat("budget", 0);
        String startDateVal=sh.getString("start","13/11/21");
        String endDateVal=sh.getString("end","13/12/21");

        // Setting the fetched data in the EditTexts
        budget.setText(String.valueOf(s1));
        startDate.setText(startDateVal);
        endDate.setText(endDateVal);

        //setting the date
        setDateField(startDate);
        setDateField(endDate);

        saveButton.setOnClickListener(view -> {

            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();

            // write all the data entered by the user in SharedPreference and apply
            myEdit.putFloat("budget", Float.parseFloat(budget.getText().toString()));
            myEdit.putString("start", startDate.getText().toString());
            myEdit.putString("end", endDate.getText().toString());
            myEdit.apply();

            Toast msg = Toast.makeText(getBaseContext(),"Budget set successfully",Toast.LENGTH_LONG);
            msg.show();

            finish();
        });




    }

    public void setDateField(EditText eText){

        final DatePickerDialog[] picker = new DatePickerDialog[1];
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker[0] = new DatePickerDialog(SetSessionParams.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker[0].show();
            }
        });


    }
}