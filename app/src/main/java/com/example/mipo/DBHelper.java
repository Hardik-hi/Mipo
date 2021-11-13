package com.example.mipo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
        cv.put(REMARKS, transactionModel.getRemark());
        cv.put(PAYMENT_MODE, transactionModel.getPayment_mode());

        long insert = db.insert(TRANSACTIONS, null, cv);
        return insert != -1;
    }
}
