package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import static com.example.fitnessapp.ExerciseType.WEIGHTED;

public class WEModify extends AppCompatActivity {

    Button addButton;
    TextView title;
    TextInputLayout weightInput;
    TextInputLayout repetitionInput;
    RecyclerView weRecyclerView;
    List<WeightedSet> myList;
    WeightedExerciseInstance wei;
    WERecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Exercise e = new Exercise("DIPS", ExerciseEnum.Muscle.CHEST, WEIGHTED, new ExerciseMap());
        wei = new WeightedExerciseInstance(e);

        myList = new ArrayList<>();
        myList.add(new WeightedSet(e, 75, 10));

        setContentView(R.layout.activity_wemodify);

        weightInput = findViewById(R.id.weightInput);
        repetitionInput = findViewById(R.id.repetitionInput);
        weRecyclerView = findViewById(R.id.weRecyclerView);
        title = findViewById(R.id.textView);

        title.setText(wei.getExercise().getName());

        adapter = new WERecyclerViewAdapter(myList , getApplication());
        weRecyclerView.setAdapter(adapter);
        weRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        addButton = findViewById(R.id.addbutton);
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),SendPhotos.class);
//                startActivity(intent);
                try {
                    double weight = Double.parseDouble(weightInput.getEditText().getText().toString());
                    int rep = Integer.parseInt(repetitionInput.getEditText().getText().toString());
                    myList.add(new WeightedSet(wei.getExercise(), weight, rep));
                    adapter.notifyDataSetChanged();
                } catch (NumberFormatException nfm) {
                }
            }
        });
    }

}
