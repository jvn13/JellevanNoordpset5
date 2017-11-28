package com.example.jelle.jellevannoord_pset5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RestoDatabase extends SQLiteOpenHelper {

    private static RestoDatabase instance;
    private String[] titles = {"Do laundry","Get started","Finish project"};

    public static RestoDatabase getInstance(Context context) {
        if(instance == null) {
            instance = new RestoDatabase(context, "pset5", null, 1);
        }
        return instance;
    }

    private RestoDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS orders ( _id INTEGER PRIMARY KEY, name TEXT, price INTEGER, url TEXT, amount INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS orders");
        onCreate(sqLiteDatabase);
    }

    public Cursor selectAll() {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("SELECT * FROM orders", null);
    }

    public boolean containsID(int id) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT 1 FROM orders WHERE _id=" + id, null);
        if((cursor.moveToFirst() && cursor.getCount() != 0)) {
            return true;
        }
        return false;
    }

    public void addItem(int id, String name, int price, String url) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", id);
        contentValues.put("name", name);
        contentValues.put("price",price);
        contentValues.put("url",url);
        contentValues.put("amount",1);
        db.insert("orders", null, contentValues);
    }

    public void updateItem(int id) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM orders WHERE _id=" + id, null);
        if((cursor.moveToFirst() && cursor.getCount() != 0)) {
            int amount = cursor.getInt(cursor.getColumnIndex("amount"));
            amount++;
            ContentValues contentValues = new ContentValues();
            contentValues.put("amount",amount);
            db.update("orders",contentValues,"_id = ?", new String[]{String.valueOf(id)});
        }
    }

    public void delete(String id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("orders", "_id = ?", new String[]{id});
    }

    public int getTotalAmount() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM orders", null);
        int amount = 0;
        if((cursor.moveToFirst() && cursor.getCount() != 0)) {
            amount = cursor.getInt(cursor.getColumnIndex("amount"));
            while (cursor.moveToNext()) {
                amount = amount + cursor.getInt(cursor.getColumnIndex("amount"));
            }
        }
        return amount;
    }

    public void clear() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM orders");
    }
}
