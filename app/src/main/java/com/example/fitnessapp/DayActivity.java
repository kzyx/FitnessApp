package com.example.fitnessapp;

import android.app.DatePickerDialog;

import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    BottomNavigationView bottomNav;
    PopupWindow popupExercise;
    ListView popupLV;
    boolean pickingMuscle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Exercise e = new Exercise("DIPS", ExerciseEnum.Muscle.CHEST, WEIGHTED, new ExerciseMap());
        Exercise e2 = new Exercise("DB PRESS", ExerciseEnum.Muscle.CHEST, WEIGHTED, new ExerciseMap());

        ExerciseInstance ei = new WeightedExerciseInstance(e);
        ei.addSet(new WeightedSet(e, 99, 9));
        ExerciseInstance ei2 = new WeightedExerciseInstance(e2);
        ei2.addSet(new WeightedSet(e, 27, 14));

        ExerciseInstance ei3 = new WeightedExerciseInstance(e2);
        ei3.addSet(new WeightedSet(e2, 10, 10));

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
//        lei.add(ei3);

        wd = new WorkoutDay(lei);

        setContentView(R.layout.activity_day);

        dayRecyclerView = findViewById(R.id.exerciseView);
        adapter = new DayRecyclerViewAdapter(wd, getApplication(), dayRecyclerView);
        dayRecyclerView.setAdapter(adapter);
        myManager = new GridLayoutManager(this, 1);
        dayRecyclerView.setLayoutManager(myManager);



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

        bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.equals(bottomNav.getMenu().getItem(0))) {
                    pickingMuscle = !pickingMuscle;

                    LayoutInflater layoutInflater = (LayoutInflater) getApplication().getSystemService(LAYOUT_INFLATER_SERVICE);
                    ViewGroup container  = (ViewGroup) layoutInflater.inflate(R.layout.muscle_popup,null);

                    popupExercise = new PopupWindow(container,800,1300,true);
                    popupLV = container.findViewById(R.id.exerciseLV);

                    if (pickingMuscle) {
                        final List<String> enumNames = Stream.of(ExerciseEnum.Muscle.values())
                                .map(Enum::name)
                                .collect(Collectors.toList());
                        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(DayActivity.this, android.R.layout.simple_list_item_1, enumNames);
                        popupLV.setAdapter(arrayAdapter1);
                        popupLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Toast.makeText(DayActivity.this, enumNames.get(i), Toast.LENGTH_SHORT).show();
                            }
                        });
                        popupLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position,
                                                    long id) {
                                ExerciseEnum ee = new ExerciseEnum();
                                ExerciseMap myExerciseMap = ee.getExerciseMap();
                                System.out.println(position);
                                List<Exercise> exerList = myExerciseMap.getExerciseList(ExerciseEnum.Muscle.values()[position]);
                                List<String> exerStringList = myExerciseMap.getExercisesStringList(position);
                                ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(DayActivity.this, android.R.layout.simple_list_item_1, exerStringList);
                                popupLV.setAdapter(arrayAdapter2);
                                popupLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        Toast.makeText(DayActivity.this, exerStringList.get(i), Toast.LENGTH_SHORT).show();
                                        wd.add(new WeightedExerciseInstance(exerList.get(i)));
                                        adapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        });
                    }
                    else {

                    }

                    pickingMuscle = !pickingMuscle;

                    popupExercise.showAsDropDown(findViewById(R.id.bottomNav));
                }
                else if (menuItem.equals(bottomNav.getMenu().getItem(1))) {
                    adapter.removeLastItem();
                    adapter.notifyDataSetChanged();
                } else if (menuItem.equals(bottomNav.getMenu().getItem(2))) {
                    // TODO
                }
                return true;
            }
        });


//        news1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                layoutInflater = (LayoutInflater) getApplication().getSystemService(LAYOUT_INFLATER_SERVICE);
//                ViewGroup container  = (ViewGroup) layoutInflater.inflate(R.layout.news,null);
//
//                popupWindow = new PopupWindow(container,800,1300,true);
//                popupWindow.showAsDropDown(news1);
//                container.setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View view, MotionEvent motionEvent) {
//                        popupWindow.dismiss();
//                        return false;
//                    }
//                });
//            }
//        });

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

    public void openAddMenu() {
        Intent intent = new Intent(this, DayActivity.class);
        startActivity(intent);
    }

}
