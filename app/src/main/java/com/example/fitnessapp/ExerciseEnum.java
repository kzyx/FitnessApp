package com.example.fitnessapp;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public ExerciseMap myExerciseMap = new ExerciseMap();

    public ExerciseMap getExerciseMap() {
//        List<String> MuscleEnumList = new ArrayList<>();
//        MuscleEnumList.add("Cardio");
//        MuscleEnumList.add("Abs");
//        MuscleEnumList.add("Chest");
//        MuscleEnumList.add("Back");
//        MuscleEnumList.add("Shoulders");
//        MuscleEnumList.add("Biceps");
//        MuscleEnumList.add("Triceps");
//        MuscleEnumList.add("Legs");
//        MuscleEnumList.add("Other");

        ExerciseMap myExerciseMap = new ExerciseMap();

        TreeSet<Exercise> chestExercises = new TreeSet<>();
        chestExercises.add(new Exercise("Barbell Bench Press", Muscle.CHEST,
                WEIGHTED, myExerciseMap));
        chestExercises.add(new Exercise("Dumbbell Bench Press", Muscle.CHEST,
                WEIGHTED, myExerciseMap));
        chestExercises.add(new Exercise("Chest Dip", Muscle.CHEST,
                WEIGHTED, myExerciseMap));
        chestExercises.add(new Exercise("Push Up", Muscle.CHEST,
                WEIGHTED, myExerciseMap));

        TreeSet<Exercise> backExercises = new TreeSet<>();
        backExercises.add(new Exercise("Deadlift", Muscle.BACK,
                WEIGHTED, myExerciseMap));
        backExercises.add(new Exercise("Barbell Row", Muscle.BACK,
                WEIGHTED, myExerciseMap));
        backExercises.add(new Exercise("Pullup", Muscle.BACK,
                WEIGHTED, myExerciseMap));
        backExercises.add(new Exercise("Cable Pull", Muscle.BACK,
                WEIGHTED, myExerciseMap));

        TreeSet<Exercise> shoulderExercises = new TreeSet<>();
        shoulderExercises.add(new Exercise("Overhead Press", Muscle.SHOULDERS,
                WEIGHTED, myExerciseMap));
        shoulderExercises.add(new Exercise("Dumbbell Shoulder Press", Muscle.SHOULDERS,
                WEIGHTED, myExerciseMap));

        TreeSet<Exercise> bicepExercises = new TreeSet<>();
        bicepExercises.add(new Exercise("Dumbbell Curl", Muscle.BICEPS,
                WEIGHTED, myExerciseMap));
        bicepExercises.add(new Exercise("Barbell Curl", Muscle.BICEPS,
                WEIGHTED, myExerciseMap));

        TreeSet<Exercise> tricepExercises = new TreeSet<>();
        tricepExercises.add(new Exercise("Overhead Tricep Extension", Muscle.TRICEPS,
                WEIGHTED, myExerciseMap));
        tricepExercises.add(new Exercise("Rope Tricep Extension", Muscle.TRICEPS,
                WEIGHTED, myExerciseMap));
        tricepExercises.add(new Exercise("VBar Tricep Extension", Muscle.TRICEPS,
                WEIGHTED, myExerciseMap));

        TreeSet<Exercise> legExercises = new TreeSet<>();
        legExercises.add(new Exercise("Leg Machine Extension", Muscle.LEGS,
                WEIGHTED, myExerciseMap));
        legExercises.add(new Exercise("Squat", Muscle.LEGS,
                WEIGHTED, myExerciseMap));
        legExercises.add(new Exercise("Leg Press", Muscle.LEGS,
                WEIGHTED, myExerciseMap));

        return myExerciseMap;
    }

}
