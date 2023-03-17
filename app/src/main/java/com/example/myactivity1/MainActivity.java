package com.example.myactivity1;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v(TAG, "This is a verbase log.");
        Log.v(TAG, "This is a debug log.");
        Log.v(TAG, "This is a info log.");
        Log.v(TAG, "This is a warn log.");
        Log.v(TAG, "This is a error log.");

        Button button2 = (Button) findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Button Clicked");
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                startActivity(intent);
            }
        });

        }
    }
