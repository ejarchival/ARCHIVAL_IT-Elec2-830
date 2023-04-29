package com.example.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyContentProvider extends ContentProvider {

    //Define constants
    public static final String AUTHORITY = "com.example.database";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/my_table");

    //Define a database
    private MyDatabaseHelper dbHelper;

    //Implement

    @Override
    public boolean onCreate() {
        dbHelper = new MyDatabaseHelper(getContext());
        return true;
    }

    //Implement
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor= db.query("my_table", projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    //Implement the insert
    @Override
    public Uri insert(Uri uri, ContentValues values){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long rowId = db.insert("my_table", null, values);
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(CONTENT_URI, rowId);
    }


    //Implement delete
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = db.delete("my_table", selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }


    //update
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = db.update("my_table", values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    //implement the gettype
    @Override
    public String getType(Uri uri) {
        return "vnd.android.cursor.dir/vnd,example.my_table";
    }


}
