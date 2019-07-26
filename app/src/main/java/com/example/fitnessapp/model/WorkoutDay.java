package com.example.fitnessapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WorkoutDay implements Comparable<WorkoutDay> {
    private LocalDate date;
    private List<WeightedExerciseInstance> exerciseInstances;

    public WorkoutDay(LocalDate date) {
        this.date = date;
        this.exerciseInstances = new ArrayList<>();
    }

    public void add(WeightedExerciseInstance ei){
        exerciseInstances.add(ei);
    }

    public void add(int i, WeightedExerciseInstance ei) {
        exerciseInstances.add(i, ei);
    }

    public void remove(int i) {
        exerciseInstances.remove(i);
    }

    public int remove(WeightedExerciseInstance ei) {
        int ind = exerciseInstances.indexOf(ei);
        exerciseInstances.remove(ei);
        return ind;
    }

    public WeightedExerciseInstance get(int i) {
        return exerciseInstances.get(i);
    }

    public int size() {
        return exerciseInstances.size();
    }

    public LocalDate getDate() {
        return date;
    }


    @Override
    public int compareTo(WorkoutDay workoutDay) {
        return this.date.compareTo(workoutDay.getDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkoutDay)) return false;
        WorkoutDay that = (WorkoutDay) o;
        return date.equals(that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
