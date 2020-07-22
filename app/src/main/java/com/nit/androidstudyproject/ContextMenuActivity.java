package com.nit.androidstudyproject;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class ContextMenuActivity extends ListActivity {
    private String selectedName = "";
    private String[] nameList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nameList = getResources().getStringArray(R.array.name_list);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameList));
        registerForContextMenu(getListView());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it ispresent.

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
// Inflate the menu; this adds items to the action bar if it ispresent.
        getMenuInflater().inflate(R.menu.menu, menu);

    }

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo adapInfo = (AdapterView.AdapterContextMenuInfo) item
            .getMenuInfo();
        selectedName = nameList[(int) adapInfo.id];
        switch (item.getItemId()) {
            case R.id.Edit: Toast.makeText(ContextMenuActivity.this,
                    "You have pressed Edit Context Menu for" + selectedName, Toast.LENGTH_LONG).show();
                return true;
            case R.id.MENU_DELETE:
                Toast.makeText(ContextMenuActivity.this,
                        "You have pressed Delete Context Menu for" + selectedName,
                        Toast.LENGTH_LONG).show();
                return true;
            case R.id.linkc: Toast.makeText(ContextMenuActivity.this,
                    "You have pressed Link Contact Context Menu for" + selectedName, Toast.LENGTH_LONG).show();
                return true;
            case R.id.markasdef:
                Toast.makeText(ContextMenuActivity.this,
                        "You have pressed Mark as Deafult Context Menu for " + selectedName,
                        Toast.LENGTH_LONG).show();
                return true;
            case R.id.addtofav:
                Toast.makeText(ContextMenuActivity.this,
                        "You have pressed Add to Favorites Context Menu for " + selectedName,
                        Toast.LENGTH_LONG).show();
                return true;
        }
        return false;
    }
}



