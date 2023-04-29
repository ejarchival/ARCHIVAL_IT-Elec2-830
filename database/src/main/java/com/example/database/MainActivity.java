package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create a new instance
        dbHelper = new MyDatabaseHelper(this);

        //Delete data
        dbHelper.deleteAllData();

        dbHelper.insertData("John", 25);
        dbHelper.insertData("Luke", 24);

        //get data
        getData();

        //update
        dbHelper.updateData(2, "Mark", 26);

        //get data
        getData();

        //delete
        dbHelper.deleteData(2);

        //get
        getData();


        //shared preferences
        //get shared pref
        SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);

        //save values
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("name", "John");
        editor.putInt("age", 26);
        editor.putBoolean("is_student", true);
        editor.commit();

        //get values from shared
        String name = prefs.getString("name", "");
        int age = prefs.getInt("age", 8);
        boolean isStudent = prefs.getBoolean("is_student", false);
        Log.d("MainActivity", name);
        Log.d("MainActivity", String.valueOf(age));
        Log.d("MainActivity", String.valueOf(isStudent));

        //Remove
        editor.remove("name");
        editor.apply();

        //get values from
        name = prefs.getString("name", "");
        age = prefs.getInt("age", 8);
        isStudent = prefs.getBoolean("is_student", false);
        Log.d("MainActivity", name);
        Log.d("MainActivity", String.valueOf(age));
        Log.d("MainActivity", String.valueOf(isStudent));



    }
    private void getData(){
        Cursor cursor = dbHelper.getData();

        Log.d("MainActivity", "========= START =========");
        if (cursor.getCount() > 0) {
            //Loop
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int age = cursor.getInt(cursor.getColumnIndex("age"));

                Log.d("MainAcitivity", "Record retrieved with ID: " + id + ", name: " + name + ", age: " + age);
            }

        }  else {
            Log.d("MainActivity", "No Records Found. ");
        }

        Log.d ("MainActivity", "========= END =========");
    }
}