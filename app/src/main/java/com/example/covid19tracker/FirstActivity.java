package com.example.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//simple first page with a button
public class FirstActivity extends AppCompatActivity {
    Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        start=(Button) findViewById(R.id.starttracker);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onclick of "start tracker"button it will move to next page/activity
                Intent intent= new Intent(FirstActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}