package com.hfad.facebook;

import android.app.Activity;
import android.content.ContentProvider;
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
import android.widget.ShareActionProvider;
import android.widget.Toast;


public class FbLogin extends Activity  {
    private String text1;
    private String password1;
    private String ages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb_login);
        if(savedInstanceState!=null){
            text1 = savedInstanceState.getString("usernames");
            password1 = savedInstanceState.getString("passwords");
            EditText editText = (EditText)findViewById(R.id.editText);
            editText.setText(text1);
            EditText editText1 = (EditText)findViewById(R.id.editText2);
            editText1.setText(password1);
        }
    }

    public void clickButton (View view) {
    new Update().execute();
    }

    public void clickButton1(View view){
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_fb_login, menu);

       return super.onCreateOptionsMenu(menu);

       }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        EditText editText = (EditText)findViewById(R.id.editText);
        text1 = editText.getText().toString();
        switch (item.getItemId()){
            case R.id.share_actions:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, text1);
                Intent cIntent = Intent.createChooser(intent, "Send Message Using...");
                startActivity(cIntent);
            case R.id.action_settings:
                //code
        }

            return super.onOptionsItemSelected(item);
    }

@Override
    protected void onSaveInstanceState(Bundle bundle){
    EditText editText = (EditText)findViewById(R.id.editText);
     text1 = editText.getText().toString();
    EditText editText1 = (EditText)findViewById(R.id.editText2);
     password1 = editText1.getText().toString();
    bundle.putString("usernames", text1);
    bundle.putString("passwords", password1);
}

    private class Update extends AsyncTask<Void, Void, Boolean>{
        private int c=0;
        private EditText editText1;
        protected void onPreExecute(){
            EditText editText = (EditText) findViewById(R.id.editText);
            text1 = editText.getText().toString();
            editText1 = (EditText) findViewById(R.id.editText2);
            password1 = editText1.getText().toString();
        }
        protected Boolean doInBackground(Void...voids){
            SQLiteOpenHelper update = new Records(FbLogin.this);
            try{
                SQLiteDatabase db = update.getReadableDatabase();
                Cursor cursor = db.query("RECORD", new String[] {"AGE"},
                        "NAME = ? AND PASSWORD=?", new String[] {text1, password1}, null, null, null);
                if(cursor.moveToFirst()) {
                    ages = cursor.getString(0);
                    c=1;
                }
                return true;
            }catch (SQLiteException e){
                return false;
            }
        }
        protected void onPostExecute(Boolean success){
            if(success) {
                if(c==1) {
                    Intent intent = new Intent(FbLogin.this, Connected.class);
                    intent.putExtra("names", text1);
                    intent.putExtra("age", ages);
                    startActivity(intent);
                   }else {
                    Toast toast = Toast.makeText(FbLogin.this, "Invalid Username or Password", Toast.LENGTH_LONG);
                    toast.show();
                    editText1.setText("");
                }
            }else{
                Toast toast = Toast.makeText(FbLogin.this, "Database Unavailable", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

}
