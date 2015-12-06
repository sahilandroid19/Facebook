package com.hfad.facebook;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


public class Connected extends Activity {
    private String name;
    private String age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connected);
        Intent intent = getIntent();
        name = intent.getStringExtra("names");
        age = intent.getStringExtra("age");
        String text = String.format("%s,%s", name, age);
        TextView textView = (TextView) findViewById(R.id.cTextView);
        textView.setText(text);
        //setting listview
        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position >= 0 && position <= 2) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("position", position);
                    Details details = new Details();
                    details.setArguments(bundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.frag_container, details);
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }else{
                    Intent intent1 = new Intent(Connected.this, FbLogin.class);
                    startActivity(intent1);
                }
            }
        };
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(onItemClickListener);
    }
}

