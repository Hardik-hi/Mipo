package com.example.mipo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String EXPENSES = "expenses";
    public static final String ID = "id";
    public static final String DATE = "date";
    public static final String PERSON = "person";
    public static final String PAYMENT_MODE = "payment_mode";
    public static final String AMOUNT = "amount";
    public static final String REMARKS = "remarks";

    public DBHelper(@Nullable Context context) {
        super(context, EXPENSES + ".db", null, 1);
//        Toast.makeText(context, "Created Database", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + EXPENSES + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE + " DATE, " + PERSON + " TEXT, " + PAYMENT_MODE + " TEXT, " + AMOUNT + " DOUBLE, " + REMARKS + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EXPENSES);
        onCreate(db);
    }

    public boolean add_data(ExpenseModel expenseModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(AMOUNT, expenseModel.getAmount());
        cv.put(PERSON, expenseModel.getPerson());
        cv.put(DATE, expenseModel.getDate());
        cv.put(REMARKS, expenseModel.getRemarks());
        cv.put(PAYMENT_MODE, expenseModel.getPayment_mode());

        long insert = db.insert(EXPENSES, null, cv);

//        db.close();
        return insert != -1;
    }

    public List<ExpenseModel> viewAll(){
        List<ExpenseModel> expenseModelList = new ArrayList<>();
        // get data from database
        String query = "SELECT * FROM " + EXPENSES;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        // loop through the cursor and create new Transaction object for each of the result.
        if(cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String date = cursor.getString(1);
                String payment_mode = cursor.getString(2);
                String detail = cursor.getString(3);
                Double amount = cursor.getDouble(4);
                String remarks = cursor.getString(5);

                ExpenseModel expenseModel = new ExpenseModel(id, date, detail, payment_mode, amount, remarks);
                expenseModelList.add(expenseModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return expenseModelList;
    }

    public boolean delete_record(ExpenseModel expenseModel){
        //find the record from the db. If found, delete, else return false

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + EXPENSES + " WHERE " + ID + "=" + expenseModel.getId();

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst() == true) {
            return true;
        }
        return false;
    }
}
