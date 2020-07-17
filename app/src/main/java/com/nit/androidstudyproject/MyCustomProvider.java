package com.nit.androidstudyproject;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import java.util.HashMap;


public class MyCustomProvider extends ContentProvider {


    static final String PROVIDER_NAME = "com.nit.androidstudyproject.MyCustomProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/books";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String _ID = "_id";
    static final String NAME = "name";

    private static HashMap<String, String> BOOKS_PROJECTION_MAP;

    private static final int ALLROWS = 1;
    private static final int SINGLE_ROW = 2;


    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "books", ALLROWS);
        uriMatcher.addURI(PROVIDER_NAME, "books/#", SINGLE_ROW);
    }

    /**
     * Database specific constant declarations
     */

    private SQLiteDatabase db;
    static final String DATABASE_NAME = "MyBooks";
    static final String BOOKS_TABLE_NAME = "books";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE =
            " CREATE TABLE " + BOOKS_TABLE_NAME +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " name TEXT NOT NULL);";

    /**
     * Helper class that actually creates and manages
     * the provider's underlying data repository.
     */

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " +  BOOKS_TABLE_NAME);
            onCreate(db);
        }
    }
    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        /**
         * Create a write able database which will trigger its
         * creation if it doesn't already exist.
         */

        db = dbHelper.getWritableDatabase();
        return db != null;
    }


    @Override
    public Cursor query( Uri uri, String[] strings, String s, String[] strings1, String s1) {
        SQLiteQueryBuilder sqlBuilder = new SQLiteQueryBuilder();

        sqlBuilder.setTables(BOOKS_TABLE_NAME);

        // Retrieve records as specified by the query parameters

        Cursor c = sqlBuilder.query(db, strings, s, strings1, null, null, s1);

        return c;
    }


    @Override
    public String getType( Uri uri) {
        int code = uriMatcher.match(uri);
        switch (code)
        {
            case ALLROWS:
                return "vnd.android.cursor.dir/BookDetails";
            case SINGLE_ROW:
                return "vnd.android.cursor.item/BookDetails";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }


    @Override
    public Uri insert( Uri uri, ContentValues contentValues) {
        // Add a new record into the table
        Long rowID = db.insert(BOOKS_TABLE_NAME, "", contentValues);




        // If the record is successfully added
        if (rowID > 0)
        {
            /* Append the rowID at the end of the CONTENT_URI and return the resulting URI where CONTENT_URI is the URI for the content provider. */
            return ContentUris.withAppendedId(CONTENT_URI, rowID);
        }

        throw new SQLException("Failed to add new item into " + uri);
    }

    @Override
    public int delete( Uri uri, String s, String[] strings) {
        int count = 0;
        switch (uriMatcher.match(uri)){
            case ALLROWS:
                count = db.delete(BOOKS_TABLE_NAME, s, strings);
                break;

            case SINGLE_ROW:
                String id = uri.getPathSegments().get(1);
                count = db.delete( BOOKS_TABLE_NAME, _ID +  " = " + id +
                                (!TextUtils.isEmpty(s) ? " AND (" + s + ')' : ""), strings);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        return count;
    }

    @Override
    public int update( Uri uri, ContentValues contentValues, String s, String[] strings) {

            int count = 0;
            switch (uriMatcher.match(uri)) {
                case ALLROWS:
                    count = db.update(BOOKS_TABLE_NAME, contentValues, s, strings);
                    break;

                case SINGLE_ROW:
                    count = db.update(BOOKS_TABLE_NAME, contentValues,
                            _ID + " = " + uri.getPathSegments().get(1) +
                                    (!TextUtils.isEmpty(s) ? " AND (" +s + ')' : ""), strings);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown URI " + uri );
            }

            getContext().getContentResolver().notifyChange(uri, null);
            return count;
    }
}
