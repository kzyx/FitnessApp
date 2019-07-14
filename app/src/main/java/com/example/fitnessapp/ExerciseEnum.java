package com.example.fitnessapp;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import static com.example.fitnessapp.ExerciseType.WEIGHTED;

public class ExerciseEnum {
    public enum Muscle {
        CARDIO,
        ABS, CHEST, BACK, SHOULDERS, BICEPS, TRICEPS, LEGS,
        OTHER
    }

    public Map<Muscle, TreeSet<Exercise>> muscleMap = new HashMap<>();

    void bitch() {
        TreeSet<Exercise> chestExercises = new TreeSet<>();
        chestExercises.add(new Exercise("Barbell Bench Press", Muscle.CHEST,
                                        WEIGHTED, new ExerciseMap()));
        chestExercises.add(new Exercise("Barbell Bench Press", Muscle.CHEST,
                WEIGHTED, new ExerciseMap()));
        chestExercises.add(new Exercise("Barbell Bench Press", Muscle.CHEST,
                WEIGHTED, new ExerciseMap()));
        chestExercises.add(new Exercise("Barbell Bench Press", Muscle.CHEST,
                WEIGHTED, new ExerciseMap()));
        chestExercises.add(new Exercise("Barbell Bench Press", Muscle.CHEST,
                WEIGHTED, new ExerciseMap()));
        chestExercises.add(new Exercise("Barbell Bench Press", Muscle.CHEST,
                WEIGHTED, new ExerciseMap()));

    }

}
