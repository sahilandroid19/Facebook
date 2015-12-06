package com.hfad.facebook;


import android.app.FragmentTransaction;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Details extends Fragment {
    private View view;
    private int position;

    public Details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_details, container, false);
        position = getArguments().getInt("position");

        //referring database
        try {
            SQLiteOpenHelper facebook = new FacebookDatabaseHelper(inflater.getContext());
            SQLiteDatabase db = facebook.getReadableDatabase();
            Cursor cursor = db.query("FACEBOOK", new String[]{"NAME", "DETAIL", "RESOURCE"},
                    "_id=?", new String[]{Integer.toString(position + 1)}, null, null, null);
            if (cursor.moveToFirst()) {
                String name = cursor.getString(0);
                String detail = cursor.getString(1);
                int resource = cursor.getInt(2);
                ImageView imageView = (ImageView) view.findViewById(R.id.fimage);
                imageView.setImageResource(resource);
                TextView textView = (TextView) view.findViewById(R.id.ftext);
                textView.setText(name);
                TextView textView1 = (TextView) view.findViewById(R.id.ftext1);
                textView1.setText(detail);
            }
            cursor.close();
            db.close();
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(inflater.getContext(), "Database Unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
        return view;
    }
}
