package com.example.fitnessapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TMActivity extends AppCompatActivity {

    Context context;
    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    ExerciseMap em = new ExerciseMap();
    String[] subjects = em.getTMString();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intent = new Intent(this, MainActivity.class);

        requestWindowFeature(Window.FEATURE_ACTION_BAR);

        setContentView(R.layout.activity_tm);

        context = getApplicationContext();

        relativeLayout = (RelativeLayout) findViewById(R.id.relativelayout1);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);

        recyclerViewLayoutManager = new LinearLayoutManager(context);

        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        recyclerViewAdapter = new RecyclerViewAdapter(context, subjects);

        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int itemIndex) {
//                b.putInt("key", 1); //Your id
//                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
            }
        });
    }
}
