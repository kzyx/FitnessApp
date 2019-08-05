package com.example.fitnessapp.ui;

import com.example.fitnessapp.R;
import com.example.fitnessapp.model.Exercise;
import com.example.fitnessapp.model.ExerciseEnum;
import com.example.fitnessapp.model.ExerciseMap;
import com.example.fitnessapp.model.WeightedExerciseInstance;
import com.example.fitnessapp.model.WeightedSet;
import com.example.fitnessapp.model.WorkoutDay;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.app.DatePickerDialog;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayActivity extends FragmentActivity {
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
    private AlertDialog settingsAlert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadData();

        setContentView(R.layout.activity_day);
        setupRecyclerView();
        setupButtonsAndTextInputs();
        setDatePickerButtonListener(LocalDate.now());
    }


    private void setupRecyclerView() {
        dayRecyclerView = findViewById(R.id.dayView);
        adapter = new DayRecyclerViewAdapter(currentWorkoutDay, getApplication());
        dayRecyclerView.setAdapter(adapter);
        myManager = new GridLayoutManager(this, 1);
        dayRecyclerView.setLayoutManager(myManager);
    }


    private void setupButtonsAndTextInputs() {
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

//        addSetButton.setBackgroundColor(Color.parseColor("#f5f2d0"));
//        removeSetButton.setBackgroundColor(Color.WHITE);
//        datePickerButton.setBackgroundColor(Color.WHITE);
    }


    private void setAddSetButtonListener() {
        addSetButton.setOnClickListener(view -> {
            try {
                WeightedExerciseInstance curr = adapter.getSelectedExercise();
                double weight = Double.parseDouble(weightInput.getEditText().getText().toString());
                int reps = Integer.parseInt(repetitionInput.getEditText().getText().toString());
                curr.addSet(new WeightedSet(curr.getExercise(), weight, reps));
                adapter.notifyDataSetChanged();

                dayRecyclerView.smoothScrollToPosition(adapter.getSelectedIndex());

                View currentView = this.getCurrentFocus();
                if (currentView != null) Utility.hideKeyboardFrom(this, currentView);
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            } catch (ArrayIndexOutOfBoundsException ae) {
                ae.printStackTrace();
            }
        });
    }


    private void setRemoveSetButtonListener() {
        removeSetButton.setOnClickListener(view -> {
            try {
                WeightedExerciseInstance curr = adapter.getSelectedExercise();
                curr.removeLastSet();
                adapter.notifyDataSetChanged();

                dayRecyclerView.smoothScrollToPosition(adapter.getSelectedIndex());

                View currentView = this.getCurrentFocus();
                if (currentView != null) Utility.hideKeyboardFrom(this, currentView);
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            } catch (ArrayIndexOutOfBoundsException ae) {
                ae.printStackTrace();
            }
        });
    }


    private void setBottomNavClickListener() {
        bottomNav.setOnNavigationItemSelectedListener(menuItem -> {
            if (menuItem.equals(bottomNav.getMenu().getItem(0))) {
                showAddExerciseMenu();
            } else if (menuItem.equals(bottomNav.getMenu().getItem(1))) {
                adapter.removeLastItem();
                adapter.notifyDataSetChanged();
            } else if (menuItem.equals(bottomNav.getMenu().getItem(2))) {
                String message = "Created by K.\nCheck out my github at https://github.com/kzyx";
                showMessage("Info", message);
            }
            return true;
        });
    }

    private void showAddExerciseMenu() {
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
            popupLV.setOnItemClickListener((adapterView, view, i, l) ->
                    Toast.makeText(DayActivity.this, enumNames.get(i), Toast.LENGTH_SHORT).show());
            popupLV.setOnItemClickListener((parent, view, position, id) -> {
                ExerciseMap myExerciseMap = ExerciseEnum.getExerciseMap();
                System.out.println(position);
                List<Exercise> exerList = myExerciseMap.getExerciseList(ExerciseEnum.Muscle.values()[position]);
                List<String> exerStringList = myExerciseMap.getExerciseStringList(position);
                ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(DayActivity.this, android.R.layout.simple_list_item_1, exerStringList);
                popupLV.setAdapter(arrayAdapter2);
                popupLV.setOnItemClickListener((adapterView, view1, i, l) -> {
                    Toast.makeText(DayActivity.this, exerStringList.get(i), Toast.LENGTH_SHORT).show();
                    currentWorkoutDay.add(new WeightedExerciseInstance(exerList.get(i)));
                    adapter.notifyDataSetChanged();
                });
            });
        }
        pickingMuscle = !pickingMuscle;
        popupExercise.showAsDropDown(findViewById(R.id.bottomNav));
    }


    private void setDatePickerButtonListener(LocalDate localDate) {
        final int dYear = localDate.getYear();
        final int dMonth = localDate.getMonthValue() - 1; // Subtract 1 because getMonthValue() indexed starting at one
        final int dDay = localDate.getDayOfMonth();

        // Print date in format 01 Jan 1999
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        datePickerButton.setText(localDate.format(formatter));
        datePickerButton.setTextSize(16);

        datePickerButton.setOnClickListener(view -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(DayActivity.this,
                    (datePicker, year, month, day) ->
                            updateDatePickerButton(day, month, year), dYear, dMonth, dDay);
            datePickerDialog.show();
        });
    }


    private void updateDatePickerButton(int day, int month, int year) {
        LocalDate localDate = LocalDate.of(year, month + 1, day);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        datePickerButton.setText(localDate.format(formatter));
        if (workoutDays.contains(new WorkoutDay(localDate))) {
            for (WorkoutDay workoutDay : workoutDays) {
                if (workoutDay.getDate().equals(localDate)) {
                    currentWorkoutDay = workoutDay;
                    break;
                }
            }
        } else {
            currentWorkoutDay = new WorkoutDay(localDate);
            workoutDays.add(currentWorkoutDay);
        }
        setupRecyclerView();
        setDatePickerButtonListener(localDate);
    }


    public void showMessage (String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Return", (dialog, id) -> {
            if (settingsAlert != null) settingsAlert.cancel();
        });
        settingsAlert = builder.create();
        settingsAlert.show();
    }


    private void saveData() {
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(workoutDays);
        editor.putString("myworkoutdays", json);
        editor.apply();
    }


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
        }
        else if (workoutDays.contains(new WorkoutDay(currDate))) {
            for (WorkoutDay workoutDay : workoutDays) {
                if (workoutDay.getDate().equals(currDate)) {
                    currentWorkoutDay = workoutDay;
                    break;
                }
            }
        } else {
            currentWorkoutDay = new WorkoutDay(LocalDate.now());
            workoutDays.add(currentWorkoutDay);
        }
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