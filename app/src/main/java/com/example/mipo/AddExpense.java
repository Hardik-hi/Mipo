package com.example.mipo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;
import java.util.Objects;


public class AddExpense extends BottomSheetDialogFragment {

    public static final String TAG="ActionBottomDialog";

    EditText newExpenseAmount;
    private EditText newExpensePerson;
    private EditText newExpenseRemarks;
    private EditText newExpenseDate;
    private Spinner newExpenseMethod;
    private Button newExpenseSaveButton;

    public static AddExpense newInstance(){
        return new AddExpense();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.DialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_add_expense, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        //dropdown list for selecting expense method
        Spinner spinner = (Spinner) view.findViewById(R.id.newExpenseMethod);
        String[] values={"Cash","Card","UPI","Net Banking"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), R.layout.expense_method_spinner, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        //showing the date picker and setting the edittext with the date value

        final FragmentManager fm = ((AppCompatActivity)getActivity()).getSupportFragmentManager();
        EditText newExpenseDate=view.findViewById(R.id.newExpenseDate);

        newExpenseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePickerDialog(v);
            }
        });


        return view;
    }

    public void openDatePickerDialog(final View v) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        // Get Current Date
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                (view, year, monthOfYear, dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                    switch (v.getId()) {
                        case R.id.newExpenseDate:
                            ((EditText)v).setText(selectedDate);
                            break;
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));


        datePickerDialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
        datePickerDialog.show();
    }

    /*public void buttonActivator(EditText e){
        e.addTextChangedListener(new TextWatcher() {


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    newExpenseSaveButton.setEnabled(false);
                    newExpenseSaveButton.setTextColor(Color.GRAY);
                }
                else{
                    newExpenseSaveButton.setEnabled(true);
                    newExpenseSaveButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark));
                }
            }

        });
    }
*/
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newExpenseAmount = requireView().findViewById(R.id.newExpenseAmount);
        newExpensePerson = requireView().findViewById(R.id.newExpensePerson);
        newExpenseRemarks = getView().findViewById(R.id.newExpenseRemark);
        newExpenseMethod = requireView().findViewById(R.id.newExpenseMethod);
        newExpenseDate = requireView().findViewById(R.id.newExpenseDate);
        newExpenseSaveButton = getView().findViewById(R.id.newExpenseButton);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

        boolean isUpdate = false;

        //DATABASE COMMANDS OPENING AND SETTING CONN

        final Bundle bundle = getArguments();
        if(bundle != null){
            isUpdate = true;

            //setting the amount
            String amount = Double.toString(bundle.getDouble("amount"));
            newExpenseAmount.setText(amount);
            assert amount != null;

            //setting the person
            String person = bundle.getString("person");
            newExpensePerson.setText(person);
            assert person != null;

            //setting the remark
            String remarks = bundle.getString("remarks");
            newExpenseRemarks.setText(remarks);

            /*setting the method
            String method = bundle.getString("method");
            newExpenseMethod.
            assert method != null;*/

            //setting the date
            String date = bundle.getString("date");
            newExpenseDate.setText(date);
            assert date != null;

            if(amount.length()>0 && person.length()>0)
                newExpenseSaveButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark));
        }

        final boolean finalIsUpdate = isUpdate;
        newExpenseSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //amount of expense
                Double amount=-1.0;
                amount = Double.parseDouble(newExpenseAmount.getText().toString());

                //name of person
                String person=newExpensePerson.getText().toString();

                //remarks
                String remarks=newExpenseRemarks.getText().toString();

                //date in DD/MM/YYYY format
                String date=newExpenseDate.getText().toString();

                //method of expense
                String method=newExpenseMethod.getSelectedItem().toString();
                ExpenseModel expenseModel;
                DBHelper dbhelper = new DBHelper(getActivity());


                if(finalIsUpdate){
                    //id of expense
                    int id=bundle.getInt("id");

                    boolean success = dbhelper.edit_record(id, date, person, method, amount, remarks);
                    Toast.makeText(getActivity(), "success="+String.valueOf(success), Toast.LENGTH_SHORT).show();
                    if(success == true) {
                        Toast.makeText(getActivity(), "Successfully updated record", Toast.LENGTH_SHORT).show();
                    } else Toast.makeText(getActivity(), "Failed to update record", Toast.LENGTH_SHORT).show();

                }
                else {
                    try{
                        expenseModel = new ExpenseModel(date, person, method, amount, remarks);
                        Toast.makeText(getActivity(), "Successfully created new record", Toast.LENGTH_SHORT).show();
                    }
                    catch(Exception e){
                        Toast.makeText(getActivity(), "Failed to create new record", Toast.LENGTH_SHORT).show();
                        expenseModel = new ExpenseModel("null", "none", "null", -1.0, "invalid");
                    }

                    boolean success = dbhelper.add_data(expenseModel);

                    if(success == true) {
                        Toast.makeText(getActivity(), "Successfully added transaction", Toast.LENGTH_SHORT).show();
                    } else Toast.makeText(getActivity(), "Failed to add transaction", Toast.LENGTH_SHORT).show();
                }
                //Return values of following functions to be made use of
                Double temp1 = dbhelper.get_highest();
                Double temp2 = dbhelper.get_avg();
                Double temp3 = dbhelper.get_sum();




                // write all data in SharedPreference
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putFloat("spend", temp3.floatValue());
                myEdit.putFloat("avg", temp2.floatValue());
                myEdit.putFloat("highest", temp1.floatValue());

                myEdit.apply();

                //While actual calls, remove toasts
                /*Toast.makeText(getContext(), temp1.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(), temp2.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(), temp3.toString(), Toast.LENGTH_SHORT).show();*/
                Activity activity=getActivity();

                dismiss();
                activity.recreate();
            }
        });
    }



    @Override
    public void onDismiss(@NonNull DialogInterface dialog){
        Activity activity = getActivity();

        if(activity instanceof DialogCloseListener)
            ((DialogCloseListener)activity).handleDialogClose(dialog);
    }

}
