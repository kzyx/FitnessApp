package com.example.fitnessapp;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

public class ExerciseInstanceList implements Iterable<ExerciseInstance> {

    private ArrayList<ExerciseInstance> exercises;

    @Override
    public Iterator<ExerciseInstance> iterator() {
        return exercises.iterator();
    }

    @Override
    public void forEach(@NonNull Consumer<? super ExerciseInstance> action) {
        // TODO
    }

    public void addExercise(ExerciseInstance e) {
        exercises.add(e);
    }

    public void removeExercise(ExerciseInstance e) {
        exercises.remove(e);
    }
}
