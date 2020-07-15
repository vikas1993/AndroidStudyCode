package com.nit.androidstudyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void shareText(View view) {
        //Sharing with share sheet
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }
    public void shareTextCustom(View view) {
        // creating custom chooser
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    public void ExplicitIntent(View view) {
        Intent mExplicitIntent = new Intent(this, SecondActivity.class);
        startActivity(mExplicitIntent);
    }

    public void ImplicitIntent(View view) {
        Intent mImplicitIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:555- 3333"));
                startActivity(mImplicitIntent);

    }
}