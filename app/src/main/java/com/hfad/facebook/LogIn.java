package com.hfad.facebook;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class LogIn extends Activity {
    private String text;
    private String password;
    private String age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

    }

    public void clickButtons(View view) {
        EditText editText = (EditText) findViewById(R.id.editText3);
        text = editText.getText().toString();
        EditText editText1 = (EditText) findViewById(R.id.editText5);
        password = editText1.getText().toString();
        EditText editText2 = (EditText) findViewById(R.id.editText4);
        age = editText2.getText().toString();
        new Update().execute();
        Intent intent = new Intent(this, FbLogin.class);
        startActivity(intent);
    }

    private class Update extends AsyncTask<Void, Void, Boolean> {
        ContentValues values;
        protected void onPreExecute() {
            EditText editText = (EditText) findViewById(R.id.editText3);
            text = editText.getText().toString();
            EditText editText1 = (EditText) findViewById(R.id.editText5);
            password = editText1.getText().toString();
            EditText editText2 = (EditText) findViewById(R.id.editText4);
            age = editText2.getText().toString();
            values = new ContentValues();
            values.put("NAME", text);
            values.put("PASSWORD", password);
            values.put("AGE", age);
            }
      protected Boolean doInBackground(Void...voids){
          SQLiteOpenHelper update = new Records(LogIn.this);
          try{
              SQLiteDatabase db = update.getWritableDatabase();
              db.insert("RECORD", null, values);
              return true;
          }catch (SQLiteException e){
              return false;
          }
      }
        protected void onPostExecute(Boolean success){
            if(!success){
                Toast toast = Toast.makeText(LogIn.this, "Database Unavailable", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}
