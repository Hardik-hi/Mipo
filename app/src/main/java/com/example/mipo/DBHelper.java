package com.example.mipo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String TRANSACTIONS = "TRANSACTIONS";
    public static final String ID = "id";
    public static final String DATE = "date";
    public static final String DETAILS = "details";
    public static final String PAYMENT_MODE = "payment_mode";
    public static final String AMOUNT = "amount";
    public static final String REMARKS = "remarks";

    public DBHelper(Context context) {
        super(context, TRANSACTIONS + ".db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TRANSACTIONS + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE + " DATE, " + DETAILS + " TEXT, " + PAYMENT_MODE + " TEXT, " + AMOUNT + " DOUBLE, " + REMARKS + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean add_data(TransactionModel transactionModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(AMOUNT, transactionModel.getAmount());
        cv.put(DETAILS, transactionModel.getDetail());
        cv.put(DATE, transactionModel.getDate());
        cv.put(REMARKS, transactionModel.getRemarks());
        cv.put(PAYMENT_MODE, transactionModel.getPayment_mode());

        long insert = db.insert(TRANSACTIONS, null, cv);

        db.close();
        return insert != -1;
    }

    public List<TransactionModel> viewAll(){
        List<TransactionModel> transactionModelList = new ArrayList<>();
        // get data from database
        String query = "SELECT * FROM " + TRANSACTIONS;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        // loop through the cursor and create new Transaction object for each of the result.
        if(cursor.moveToFirst()) do {
            int id = cursor.getInt(0);
            String date = cursor.getString(1);
            String payment_mode = cursor.getString(2);
            String detail = cursor.getString(3);
            Double amount = cursor.getDouble(4);
            String remarks = cursor.getString(5);

            TransactionModel transactionModel = new TransactionModel(id, date, detail, payment_mode, amount, remarks);
            transactionModelList.add(transactionModel);
        } while (cursor.moveToFirst());

        cursor.close();
        db.close();
        return transactionModelList;
    }

    public boolean delete_record(TransactionModel transactionModel){
        //find the record from the db. If found, delete, else return false

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TRANSACTIONS + " WHERE " + ID + "=" + transactionModel.getId();

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst() == true) {
            return true;
        }
        return false;
    }
}
