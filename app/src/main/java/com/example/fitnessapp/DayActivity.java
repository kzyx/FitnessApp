package com.example.fitnessapp;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.fitnessapp.ExerciseType.WEIGHTED;

public class DayActivity extends AppCompatActivity {
    //Button addButton;
    TextView title;
    RecyclerView dayRecyclerView;
    WorkoutDay wd;
    DayRecyclerViewAdapter adapter;
    GridLayoutManager myManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Exercise e = new Exercise("DIPS", ExerciseEnum.Muscle.CHEST, WEIGHTED, new ExerciseMap());
        Exercise e2 = new Exercise("DB PRESS", ExerciseEnum.Muscle.CHEST, WEIGHTED, new ExerciseMap());

        ExerciseInstance ei = new WeightedExerciseInstance(e);
        ei.addSet(new WeightedSet(e, 99, 9));
        ExerciseInstance ei2 = new WeightedExerciseInstance(e2);
        ei2.addSet(new WeightedSet(e, 27, 14));

        List<ExerciseInstance> lei = new ArrayList<>();
        lei.add(ei);
        lei.add(ei2);

        wd = new WorkoutDay(lei);

        setContentView(R.layout.activity_day);

//        weightInput = findViewById(R.id.weightInput);
//        repetitionInput = findViewById(R.id.repetitionInput);
//        weRecyclerView = findViewById(R.id.weRecyclerView);
//        title = findViewById(R.id.textView);

        //title.setText(wei.getExercise().getName().toLowerCase());
        dayRecyclerView = findViewById(R.id.exerciseView);


        adapter = new DayRecyclerViewAdapter(wd, getApplication());
        dayRecyclerView.setAdapter(adapter);
        myManager = new GridLayoutManager(this, 1);
        dayRecyclerView.setLayoutManager(myManager);


        //addButton = findViewById(R.id.addbutton);
//        addButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(getApplicationContext(),SendPhotos.class);
////                startActivity(intent);
////                try {
////                    double weight = Double.parseDouble(weightInput.getEditText().getText().toString());
////                    int rep = Integer.parseInt(repetitionInput.getEditText().getText().toString());
////                    wei.addSet(new WeightedSet(wei.getExercise(), weight, rep));
////                    adapter.notifyDataSetChanged();
////                } catch (NumberFormatException nfm) {
////                }
//            }
//        });
    }

}
