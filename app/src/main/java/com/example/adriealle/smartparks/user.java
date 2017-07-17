package com.example.adriealle.smartparks;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Adriealle on 08-04-2017.
 */

public class user extends SQLiteOpenHelper {


    user(Context context) {
        super(context,"REG", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE REG ( _id INTEGER PRIMARY KEY AUTOINCREMENT, plate TEXT, loc TEXT, slot TEXT, hour INTEGER, min INTEGER, date TEXT, limit TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "REG");
        onCreate(db);
    }

    void all(String sw, String nw,String kw,int fw,int mf,String ow,String lo) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("plate", sw);
        values.put("loc",kw);
        values.put("slot", nw);
        values.put("hour",fw);
        values.put("limt",lo);
        values.put("min",mf);
        values.put("date",ow);
        db.insert("REG", null, values);
        db.close();


    }
    void can(String sw) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("loc","null");
        values.put("slot", "null");
        values.put("hour","null");
        values.put("min","null");
        values.put("date","null");
        values.put("plate","null");
        db.update("REG",values,"plate=?", new String[]{sw});
        db.close();


    }

}