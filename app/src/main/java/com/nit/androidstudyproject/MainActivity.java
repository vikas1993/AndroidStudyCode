package com.nit.androidstudyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openPanel(View view) {
        startActivity(new Intent(this, graphicsActivity.class));
    }

    public void openDrag(View view) {
        startActivity(new Intent(this, dragAndDrawActivity.class));

    }

    public void openSong(View view) {
        startActivity(new Intent(this, SongActivity.class));

    }

    public void openVideo(View view) {
        startActivity(new Intent(this, VideoActivity.class));

    }
}