package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = new Intent(this, TMActivity.class);
//        Bundle b = new Bundle();
//        b.putInt("type", 0); //Your id
//        intent.putExtras(b); //Put your id to your next Intent
        Intent intent = new Intent(this, DayActivity.class);
        startActivity(intent);
    }
}
