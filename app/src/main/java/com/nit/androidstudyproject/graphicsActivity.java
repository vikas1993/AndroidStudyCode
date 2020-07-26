package com.nit.androidstudyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class graphicsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Panel(this));

    }
}