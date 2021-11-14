package com.example.mipo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {

    private List<ExpenseModel> expenseList;
    //private DatabaseHandler db;
    private ViewExpenses activity;

    public ExpenseAdapter(ViewExpenses activity) {
        //this.db = db;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expense, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        //db.openDatabase();

        final ExpenseModel item = expenseList.get(position);
        holder.amount.setText(item.getAmount().toString());
        holder.personAndDate.setText(item.getPerson()+" | "+item.getDate());
        holder.remarks.setText(item.getRemarks());
    }

    private boolean toBoolean(int n) {
        return n != 0;
    }

    @Override
    public int getItemCount() {

        return expenseList.size();
    }

    public Context getContext() {

        return activity;
    }

    public void setExpenseList(List<ExpenseModel> expenseList) {
        this.expenseList = expenseList;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        ExpenseModel item = expenseList.get(position);

        //db.deleteTask(item.getId());
        expenseList.remove(position);
        notifyItemRemoved(position);
    }

    public void editItem(int position) {
        ExpenseModel item = expenseList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putDouble("amount", item.getAmount());
        bundle.putString("person", item.getPerson());
        bundle.putString("remarks", item.getPerson());
        bundle.putString("method", item.getPayment_mode());
        bundle.putString("date",item.getDate());


        AddExpense fragment = new AddExpense();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddExpense.TAG);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView amount, personAndDate, remarks;

        ViewHolder(View view) {
            super(view);
            amount = view.findViewById(R.id.expenseAmountTitle);
            personAndDate=view.findViewById(R.id.expensePersonTitle);
            remarks=view.findViewById(R.id.expenseRemarkTitle);

        }
    }


}
