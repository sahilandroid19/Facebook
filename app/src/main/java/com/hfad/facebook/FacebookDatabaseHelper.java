package com.hfad.facebook;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FacebookDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "facebook";
    private static final int DB_VERSION = 1;

    FacebookDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        updateDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        updateDatabase(db, oldVersion, newVersion);
    }

    public void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("CREATE TABLE FACEBOOK(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        + "NAME TEXT,"
        + "DETAIL TEXT,"
        + "RESOURCE INTEGER);");
        insertData(db,"Notifications","You have 7 notifications",  R.drawable.notification);
        insertData(db, "Messages", "You have 1 message",  R.drawable.message);
        insertData(db, "Friend Requests","You have 1 friend request", R.drawable.friendrequest);
    }

    public void insertData(SQLiteDatabase db,String name, String detail, int resource){
        ContentValues values = new ContentValues();
        values.put("NAME", name);
        values.put("DETAIL", detail);
        values.put("RESOURCE", resource);
        db.insert("FACEBOOK", null, values);
    }
}
