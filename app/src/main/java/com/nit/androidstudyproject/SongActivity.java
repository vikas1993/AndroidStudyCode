package com.nit.androidstudyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

public class SongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.song);
        mediaPlayer.start();
    }
}