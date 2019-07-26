package com.example.fitnessapp.ui;

import android.app.DatePickerDialog;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import com.example.fitnessapp.R;
import com.example.fitnessapp.model.Exercise;
import com.example.fitnessapp.model.ExerciseEnum;
import com.example.fitnessapp.model.ExerciseInstance;
import com.example.fitnessapp.model.ExerciseMap;
import com.example.fitnessapp.model.WeightedExerciseInstance;
import com.example.fitnessapp.model.WeightedSet;
import com.example.fitnessapp.model.WorkoutDay;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayActivity extends AppCompatActivity {

    TextView title;
    private RecyclerView dayRecyclerView;
    private ArrayList<WorkoutDay> workoutDays;
    private WorkoutDay currentWorkoutDay;
    private DayRecyclerViewAdapter adapter;
    private GridLayoutManager myManager;
    private RelativeLayout relativeLayout;
    private Button datePickerButton;
    private TextInputLayout weightInput;
    private TextInputLayout repetitionInput;
    private Button addSetButton;
    private Button removeSetButton;
    private BottomNavigationView bottomNav;
    private PopupWindow popupExercise;
    private ListView popupLV;
    private boolean pickingMuscle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();

        setContentView(R.layout.activity_day);

        setupRecyclerView();


        weightInput = findViewById(R.id.weightInput);
        repetitionInput = findViewById(R.id.repetitionInput);
        addSetButton = findViewById(R.id.addSetButton);
        setAddSetButtonListener();
        removeSetButton = findViewById(R.id.removeSetButton);
        setRemoveSetButtonListener();

        bottomNav = findViewById(R.id.bottomNav);
        setBottomNavClickListener();

        relativeLayout = findViewById(R.id.relativelayout1);
        relativeLayout.setBackgroundColor(Color.parseColor("#ede1d1"));
        datePickerButton = findViewById(R.id.datePickerButton);

        setDatePickerButtonListener(LocalDate.now());

    }

    private void setupRecyclerView() {
        dayRecyclerView = findViewById(R.id.dayView);
        adapter = new DayRecyclerViewAdapter(currentWorkoutDay, getApplication());
        dayRecyclerView.setAdapter(adapter);
        myManager = new GridLayoutManager(this, 1);
        dayRecyclerView.setLayoutManager(myManager);
    }

    private void setRemoveSetButtonListener() {
        removeSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ExerciseInstance curr = adapter.getSelectedExercise();
                    ((WeightedExerciseInstance) curr).removeLastSet();
                    adapter.notifyDataSetChanged();
                } catch (NumberFormatException nfm) {
                    //TODO something
                }
            }
        });
    }

    private void setAddSetButtonListener() {
        addSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    WeightedExerciseInstance curr = adapter.getSelectedExercise();

                    double weight = Double.parseDouble(weightInput.getEditText().getText().toString());
                    int reps = Integer.parseInt(repetitionInput.getEditText().getText().toString());

                    curr.addSet(new WeightedSet(curr.getExercise(), weight, reps));
                    adapter.notifyDataSetChanged();
                } catch (NullPointerException npe) {

                }
            }
        });
    }

    private void setBottomNavClickListener() {
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.equals(bottomNav.getMenu().getItem(0))) {
                    pickingMuscle = !pickingMuscle;

                    LayoutInflater layoutInflater = (LayoutInflater) getApplication().getSystemService(LAYOUT_INFLATER_SERVICE);
                    ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.muscle_popup, null);

                    popupExercise = new PopupWindow(container, 800, 1300, true);
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
                                List<String> exerStringList = myExerciseMap.getExerciseStringList(position);
                                ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(DayActivity.this, android.R.layout.simple_list_item_1, exerStringList);
                                popupLV.setAdapter(arrayAdapter2);
                                popupLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        Toast.makeText(DayActivity.this, exerStringList.get(i), Toast.LENGTH_SHORT).show();
                                        currentWorkoutDay.add(new WeightedExerciseInstance(exerList.get(i)));
                                        adapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        });
                    } else {

                    }

                    pickingMuscle = !pickingMuscle;

                    popupExercise.showAsDropDown(findViewById(R.id.bottomNav));
                } else if (menuItem.equals(bottomNav.getMenu().getItem(1))) {
                    adapter.removeLastItem();
                    adapter.notifyDataSetChanged();
                } else if (menuItem.equals(bottomNav.getMenu().getItem(2))) {
                    // TODO
                    saveData();
                    System.out.println("\n \n \n \n\n\n\n\ninFuncMenuListener\n\n\n\n\n\n\n\n\n");
                }
                return true;
            }
        });
    }

    private void setDatePickerButtonListener(LocalDate ld) {

        final int todayYear = ld.getYear();
        final int todayMonth = ld.getMonthValue() - 1;
        final int todayDay = ld.getDayOfMonth();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        datePickerButton.setText(ld.format(formatter));

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
    }

    private void saveData() {
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(workoutDays);
        editor.putString("myworkoutdays", json);
        editor.apply();
        System.out.println("\n\n\n\n\n\n\nmade it to end of SaveData\n\n\n\n\n\n\n");
    }

    // ASSUMPTION: last elt of workoutdays is current day
    private void loadData() {
        String json = getPreferences(MODE_PRIVATE).getString("myworkoutdays", null);
        Type type = new TypeToken<ArrayList<WorkoutDay>>() {}.getType();
        Gson gson = new Gson();
        workoutDays = gson.fromJson(json, type);

        LocalDate currDate = LocalDate.now();

        if (workoutDays == null) {
            workoutDays = new ArrayList<>();
            currentWorkoutDay = new WorkoutDay(LocalDate.now());
            workoutDays.add(currentWorkoutDay);
            System.out.println("\n\n\n\n\n\n LOAD DATA - CASE 1 \n\n\n\n\n");
        }
        else if (workoutDays.contains(new WorkoutDay(currDate))) {
            for (WorkoutDay wd : workoutDays) {
                if (wd.getDate().equals(currDate)) {
                    currentWorkoutDay = wd;
                    break;
                }
            }
            System.out.println("\n\n\n\n\n\n LOAD DATA - CASE 2 \n\n\n\n\n");
        } else {
            System.out.println("Size = " + workoutDays.size());
            currentWorkoutDay = new WorkoutDay(LocalDate.now());
            workoutDays.add(currentWorkoutDay);
            System.out.println("\n\n\n\n\n\n LOAD DATA - CASE 3 \n\n\n\n\n");
            System.out.println("Size = " + workoutDays.size());
        }
    }


    private void updateDatePickerButton(int day, int month, int year) {
        LocalDate ld = LocalDate.of(year, month + 1, day);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        datePickerButton.setText(ld.format(formatter));
        if (workoutDays.contains(new WorkoutDay(ld))) {
            for (WorkoutDay wd : workoutDays) {
                if (wd.getDate().equals(ld)) {
                    currentWorkoutDay = wd;
                    break;
                }
            }
        } else {
            currentWorkoutDay = new WorkoutDay(ld);
            workoutDays.add(currentWorkoutDay);
        }
        setupRecyclerView();

        setDatePickerButtonListener(ld);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveData();
    }


}