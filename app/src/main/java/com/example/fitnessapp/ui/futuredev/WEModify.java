package com.example.fitnessapp.ui.futuredev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.model.WeightedExerciseInstance;
import com.example.fitnessapp.model.WeightedSet;
import com.example.fitnessapp.ui.WERecyclerViewAdapter;
import com.google.android.material.textfield.TextInputLayout;


// Could be used in a future release
//      This class is a fullscreen activity for editing a weighted exercise instance
public class WEModify extends AppCompatActivity {
    Button addButton;
    TextView title;
    TextInputLayout weightInput;
    TextInputLayout repetitionInput;
    RecyclerView weRecyclerView;
    WeightedExerciseInstance wei;
    WERecyclerViewAdapter adapter;
    GridLayoutManager myManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Exercise e = new Exercise("DIPS", ExerciseEnum.Muscle.CHEST, WEIGHTED, new ExerciseMap());
//        wei = new WeightedExerciseInstance(e);
//
//        wei.addSet(new WeightedSet(e, 99, 9));

        setContentView(R.layout.activity_wemodify);

        weightInput = findViewById(R.id.weightInput);
        repetitionInput = findViewById(R.id.repetitionInput);
        weRecyclerView = findViewById(R.id.weRecyclerView);
        title = findViewById(R.id.textView);

        title.setText(wei.getExercise().getName().toLowerCase());

        adapter = new WERecyclerViewAdapter(wei, getApplication());
        weRecyclerView.setAdapter(adapter);
        myManager = new GridLayoutManager(this, 3);
        weRecyclerView.setLayoutManager(myManager);


        addButton = findViewById(R.id.addbutton);
        addButton.setOnClickListener(v -> {
            try {
                double weight = Double.parseDouble(weightInput.getEditText().getText().toString());
                int rep = Integer.parseInt(repetitionInput.getEditText().getText().toString());
                wei.addSet(new WeightedSet(wei.getExercise(), weight, rep));
                adapter.notifyDataSetChanged();
            } catch (NumberFormatException nfm) {
            }
        });
    }

}
