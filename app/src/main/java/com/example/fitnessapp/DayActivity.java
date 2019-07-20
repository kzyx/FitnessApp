package com.example.fitnessapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.NumberFormat;
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
    RelativeLayout relativeLayout;
    static Button datePickerButton;
    TextInputLayout weightInput;
    TextInputLayout repetitionInput;
    Button addSetButton;
    Button removeSetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Exercise e = new Exercise("DIPS", ExerciseEnum.Muscle.CHEST, WEIGHTED, new ExerciseMap());
        Exercise e2 = new Exercise("DB PRESS", ExerciseEnum.Muscle.CHEST, WEIGHTED, new ExerciseMap());

        ExerciseInstance ei = new WeightedExerciseInstance(e);
        ei.addSet(new WeightedSet(e, 99, 9));
        ExerciseInstance ei2 = new WeightedExerciseInstance(e2);
        ei2.addSet(new WeightedSet(e, 27, 14));

        for (int i = 0; i < 10; i++) {
            ei.addSet(new WeightedSet(e, 99, 18));
        }
        for (int i = 0; i < 4; i++) {
            ei2.addSet(new WeightedSet(e, 27, 69));
        }

        List<ExerciseInstance> lei = new ArrayList<>();
        lei.add(ei);
        lei.add(ei2);
        lei.add(ei);
        lei.add(ei);

        wd = new WorkoutDay(lei);

        setContentView(R.layout.activity_day);



        weightInput = findViewById(R.id.weightInput);
        repetitionInput = findViewById(R.id.repetitionInput);
        addSetButton = findViewById(R.id.addSetButton);
        addSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExerciseInstance curr = adapter.getSelectedExercise();
                double weight = Double.parseDouble(weightInput.getEditText().getText().toString());
                int reps = Integer.parseInt(repetitionInput.getEditText().getText().toString());
                curr.addSet(new WeightedSet(curr.getExercise(), weight, reps));
                adapter.notifyDataSetChanged();
            }
        });
        removeSetButton = findViewById(R.id.removeSetButton);
        removeSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ExerciseInstance curr = adapter.getSelectedExercise();
                    ((WeightedExerciseInstance) curr).removeLastSet();
                    adapter.notifyDataSetChanged();
                }
                catch (NumberFormatException nfm) {
                    //TODO something
                }
            }
        });
//        weRecyclerView = findViewById(R.id.weRecyclerView);
//        title = findViewById(R.id.textView);

        //title.setText(wei.getExercise().getName().toLowerCase());
        dayRecyclerView = findViewById(R.id.exerciseView);
        relativeLayout = findViewById(R.id.relativelayout1);
        relativeLayout.setBackgroundColor(Color.parseColor("#ede1d1"));
        datePickerButton = findViewById(R.id.datePickerButton);

        final Calendar c = Calendar.getInstance();
        final int todayYear = c.get(Calendar.YEAR);
        final int todayMonth = c.get(Calendar.MONTH) ;
        final int todayDay = c.get(Calendar.DAY_OF_MONTH);

        datePickerButton.setText(new StringBuffer()
                .append(todayDay+1).append("-").append(todayMonth + 1).append("-").append(todayYear).append(""));
        datePickerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                DatePickerDialog datePickerDialog = new DatePickerDialog(DayActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                updateDatePickerButton(day, month, year);
                            }
                        }, todayYear, todayMonth, todayDay);
                datePickerDialog.show();
            }
        });


        adapter = new DayRecyclerViewAdapter(wd, getApplication(), dayRecyclerView);
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


    private static void updateDatePickerButton(int day, int month, int year) {
        datePickerButton.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(day).append("-")
                        .append(month + 1).append("-")
                        .append(year).append(" "));
    }

}
