package com.nit.androidstudyproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Edit:
                Toast.makeText(getApplicationContext(), "You Clicked On Edit Menu Item", Toast.LENGTH_LONG).show();
                break;
            case R.id.SAVE:
                Toast.makeText(getApplicationContext(), "You Clicked On Save Menu Item", Toast.LENGTH_LONG).show();
                break;
            case R.id.MENU_DELETE:
                Toast.makeText(getApplicationContext(), "You Clicked On Delete Menu Item", Toast.LENGTH_LONG).show();
                break;
            case R.id.OPEN:
                Toast.makeText(getApplicationContext(), "You Clicked On Open Menu Item", Toast.LENGTH_LONG).show();
                break;

            case R.id.Add:
                Toast.makeText(getApplicationContext(), "You Clicked On Add Menu Item", Toast.LENGTH_LONG).show();
                break;

            case R.id.Refresh:
                Toast.makeText(getApplicationContext(), "You Clicked On Refresh Menu Item", Toast.LENGTH_LONG).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void openContext(View view) {
        startActivity(new Intent(MainActivity.this,ContextMenuActivity.class));
    }

    public void showMenu(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        // This activity implements OnMenuItemClickListener
        popup.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) this);
        popup.inflate(R.menu.main);
        popup.show();
    }

    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.archive:
                Toast.makeText(getApplicationContext(), "You Clicked On Archive", Toast.LENGTH_LONG).show();

                return true;
            case R.id.delete:
                Toast.makeText(getApplicationContext(), "You Clicked On Delete", Toast.LENGTH_LONG).show();
                return true;
            default:
                return false;
        }
    }

    public void showTab(View view) {
        startActivity(new Intent(MainActivity.this,TabbedActivity.class));

    }
}