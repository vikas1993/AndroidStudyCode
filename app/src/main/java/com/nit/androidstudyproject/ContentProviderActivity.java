package com.nit.androidstudyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContentProviderActivity extends AppCompatActivity {
    TextView data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        data = findViewById(R.id.data);

    }

    public void insert(View view) {
        // Add a new book record
        ContentValues values = new ContentValues();
        values.put(MyCustomProvider.NAME,"Android Dev");


        Uri uri = getContentResolver().insert(
                MyCustomProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void fetch(View view) {
        // Retrieve student records
        String URL = "content://com.nit.androidstudyproject.MyCustomProvider";

        Uri students = Uri.parse(URL);
        Cursor c = managedQuery(students, null, null, null, "name");

        if (c.moveToFirst()) {
            do{
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(MyCustomProvider._ID)) +
                                ", " +  c.getString(c.getColumnIndex( MyCustomProvider.NAME))
                        ,
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }

    }
}