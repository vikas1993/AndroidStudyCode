package com.nit.androidstudyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DbHelper dbHelper;
    TextView data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DbHelper(this);
        data = findViewById(R.id.data);
    }

    public void insert(View view) {
       if( dbHelper.insertContact("Rahul",986857567,"rahul@nit.com","office"))
           Toast.makeText(this, "Data is entered", Toast.LENGTH_SHORT).show();
    }
    public void update(View view) {
        if( dbHelper.updateContact("Rahul Sir",986857567,"rahul@nit.com","home"))
            Toast.makeText(this, "Data is updated", Toast.LENGTH_SHORT).show();
    }
    public void delete(View view) {
        dbHelper.deleteContact(986857567);
            Toast.makeText(this, "Data is deleted", Toast.LENGTH_SHORT).show();
    }
    public void fetch(View view) {
       ArrayList<String> cr = dbHelper.getData();
       data.setText("");
       for(String s : cr){
           data.append(s+"\n");
       }
    }
}