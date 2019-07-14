//package com.example.fitnessapp;
//
//
//import android.content.Context;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Adapter;
//import android.widget.ArrayAdapter;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//import java.util.TreeSet;
//
//public class TargetedMuscleActivity extends AppCompatActivity {
//
//    private ArrayList<String> muscleStringList;
//    private RecyclerView recyclerView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tm);
//
//        ExerciseMap exerciseMap = new ExerciseMap();
//        TreeSet<Exercise> exercises = exerciseMap.getExerciseSet();
//
//        recyclerView = findViewById(R.id.my_recycler_view);
//
//        // use this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
//        recyclerView.setHasFixedSize(true);
//
//        // use a linear layout manager
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//        ArrayList<Exercise> arrayList = new ArrayList<>(exercises);
//        RecyclerView.Adapter muscleAdapter =
//                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
//        android.R.
//        //RecyclerView.Adapter myAdapter = muscleAdapter;
//        recyclerView.setAdapter(muscleAdapter);
//    }
//}