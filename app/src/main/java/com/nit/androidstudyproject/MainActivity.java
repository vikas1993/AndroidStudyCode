package com.nit.androidstudyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.util.Log;
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
    public void contentprovider(View view) {
        startActivity(new Intent(this,ContentProviderActivity.class));
    }
    public void fetch(View view) {
       ArrayList<String> cr = dbHelper.getData();
       data.setText("");
       for(String s : cr){
           data.append(s+"\n");
       }
    }
    public void nativeContentProvider(View view){
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null, null, null, null);
        StringBuilder stringBuilder = new StringBuilder();
        while (cursor.moveToNext()) {
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                    String hasPhone = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                Log.e("NativeContacts",hasPhone +contactId);
            if (Boolean.parseBoolean(hasPhone)) {
                // You know it has a number so now query it like this
                Cursor phones = getContentResolver().query( ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ contactId, null, null);
                while (phones.moveToNext()) {
                    String phoneNumber = phones.getString(phones.getColumnIndex( ContactsContract.CommonDataKinds.Phone.NUMBER));
                    stringBuilder.append("Phone "+phoneNumber+" || ");
                }
                phones.close();
            }

            Cursor emails = getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + contactId, null, null);

            while (emails.moveToNext()) {
                // This would allow you get several email addresses
                String emailAddress = emails.getString(
                        emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                stringBuilder.append("Phone "+emailAddress+" || \n");

            }

            emails.close();
        }

        Toast.makeText(this, stringBuilder, Toast.LENGTH_SHORT).show();
        //Read more: http://mrbool.com/android-content-provider-how-to-use-content-provider-for-data-access/30446#ixzz6SRGV04LO
    }
}