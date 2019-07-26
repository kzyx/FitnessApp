package com.example.fitnessapp.model;

import java.util.TreeSet;

public class ExerciseEnum {
    public enum Muscle {
        CARDIO,
        ABS, CHEST, BACK, SHOULDERS, BICEPS, TRICEPS, LEGS,
        OTHER
    }

    public ExerciseMap getExerciseMap() {

        ExerciseMap myExerciseMap = new ExerciseMap();

        TreeSet<Exercise> chestExercises = new TreeSet<>();
        chestExercises.add(new Exercise("Barbell Bench Press", Muscle.CHEST,
                ExerciseType.WEIGHTED, myExerciseMap));
        chestExercises.add(new Exercise("Dumbbell Bench Press", Muscle.CHEST,
                ExerciseType.WEIGHTED, myExerciseMap));
        chestExercises.add(new Exercise("Chest Dip", Muscle.CHEST,
                ExerciseType.WEIGHTED, myExerciseMap));
        chestExercises.add(new Exercise("Push Up", Muscle.CHEST,
                ExerciseType.WEIGHTED, myExerciseMap));

        TreeSet<Exercise> backExercises = new TreeSet<>();
        backExercises.add(new Exercise("Deadlift", Muscle.BACK,
                ExerciseType.WEIGHTED, myExerciseMap));
        backExercises.add(new Exercise("Barbell Row", Muscle.BACK,
                ExerciseType.WEIGHTED, myExerciseMap));
        backExercises.add(new Exercise("Pullup", Muscle.BACK,
                ExerciseType.WEIGHTED, myExerciseMap));
        backExercises.add(new Exercise("Cable Pull", Muscle.BACK,
                ExerciseType.WEIGHTED, myExerciseMap));

        TreeSet<Exercise> shoulderExercises = new TreeSet<>();
        shoulderExercises.add(new Exercise("Overhead Press", Muscle.SHOULDERS,
                ExerciseType.WEIGHTED, myExerciseMap));
        shoulderExercises.add(new Exercise("Dumbbell Shoulder Press", Muscle.SHOULDERS,
                ExerciseType.WEIGHTED, myExerciseMap));

        TreeSet<Exercise> bicepExercises = new TreeSet<>();
        bicepExercises.add(new Exercise("Dumbbell Curl", Muscle.BICEPS,
                ExerciseType.WEIGHTED, myExerciseMap));
        bicepExercises.add(new Exercise("Barbell Curl", Muscle.BICEPS,
                ExerciseType.WEIGHTED, myExerciseMap));

        TreeSet<Exercise> tricepExercises = new TreeSet<>();
        tricepExercises.add(new Exercise("Overhead Tricep Extension", Muscle.TRICEPS,
                ExerciseType.WEIGHTED, myExerciseMap));
        tricepExercises.add(new Exercise("Rope Tricep Extension", Muscle.TRICEPS,
                ExerciseType.WEIGHTED, myExerciseMap));
        tricepExercises.add(new Exercise("VBar Tricep Extension", Muscle.TRICEPS,
                ExerciseType.WEIGHTED, myExerciseMap));

        TreeSet<Exercise> legExercises = new TreeSet<>();
        legExercises.add(new Exercise("Leg Machine Extension", Muscle.LEGS,
                ExerciseType.WEIGHTED, myExerciseMap));
        legExercises.add(new Exercise("Squat", Muscle.LEGS,
                ExerciseType.WEIGHTED, myExerciseMap));
        legExercises.add(new Exercise("Leg Press", Muscle.LEGS,
                ExerciseType.WEIGHTED, myExerciseMap));

        return myExerciseMap;
    }

}
