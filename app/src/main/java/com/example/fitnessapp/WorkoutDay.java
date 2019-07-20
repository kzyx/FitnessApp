package com.example.fitnessapp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WorkoutDay {

    LocalDate date;
    List<ExerciseInstance> exerciseInstances;
    List<WERecyclerViewAdapter> recyclerViewAdapters;

    WorkoutDay(List<ExerciseInstance> exerciseInstances) {
        date = LocalDate.now();
        this.exerciseInstances = exerciseInstances;
    }

    public void add(ExerciseInstance ei){
        exerciseInstances.add(ei);
        //recyclerViewAdapters.add(new WERecyclerViewAdapter(ei), getApplicationContext());
    }

    public void add(int i, ExerciseInstance ei) {
        exerciseInstances.add(i, ei);
    }

    public void remove(int i) {
        exerciseInstances.remove(i);
    }

    public int remove(ExerciseInstance ei) {
        int ind = exerciseInstances.indexOf(ei);
        exerciseInstances.remove(ei);
        return ind;
    }

    public ExerciseInstance get(int i) {
        return exerciseInstances.get(i);
    }

    public int size() {
        return exerciseInstances.size();
    }

}
