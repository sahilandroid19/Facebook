package com.hfad.facebook;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Records extends SQLiteOpenHelper {
    private static final String DB_NAME = "starbuzz1";
    private static final int DB_VERSION = 1;

    Records(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE RECORD(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        + "NAME TEXT,"
        + "PASSWORD TEXT,"
        + "AGE TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
