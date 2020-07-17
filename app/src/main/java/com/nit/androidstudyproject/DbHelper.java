package com.nit.androidstudyproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ContactsInfo.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "ContactDetails";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create a table with three columns: columns id, name, and phone// Execute the create table query when an object of the OpenHelper
        //// class is created
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(Name Text, PhoneNo INTEGER, Email_ID TEXT, PhoneType TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertContact (String name, int phone, String email, String phoneType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("PhoneNo", phone);
        contentValues.put("Email_ID", email);
        contentValues.put("PhoneType", phoneType);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }
    public ArrayList<String> getData() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add("Name = "+res.getString(res.getColumnIndex("Name"))+"  ||  Email = "+res.getString(res.getColumnIndex("Email_ID")));
            res.moveToNext();
        }
        return array_list;
    }

    public boolean updateContact (String name, int phone, String email, String phoneType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("PhoneNo", phone);
        contentValues.put("Email_ID", email);
        contentValues.put("PhoneType", phoneType);
        db.update(TABLE_NAME, contentValues, "PhoneNo = ? ", new String[] { Integer.toString(phone) } );
        return true;
    }

    public Integer deleteContact (int phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,
                "PhoneNo = ? ",
                new String[] { Integer.toString(phone) });
    }
}

