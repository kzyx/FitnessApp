package com.example.fitnessapp.model;

import java.util.TreeSet;

public class ExerciseEnum {

    public enum Muscle {
        CARDIO,
        ABS, CHEST, BACK, SHOULDERS, BICEPS, TRICEPS, LEGS,
        OTHER
    }


    public static ExerciseMap getExerciseMap() {

        ExerciseMap returnMap = new ExerciseMap();

        TreeSet<Exercise> abExercises = new TreeSet<>();
        abExercises.add(new Exercise("Weighted Leg Raises", Muscle.ABS,
                ExerciseType.WEIGHTED, returnMap));

        TreeSet<Exercise> chestExercises = new TreeSet<>();
        chestExercises.add(new Exercise("Barbell Bench Press", Muscle.CHEST,
                ExerciseType.WEIGHTED, returnMap));
        chestExercises.add(new Exercise("Dumbbell Bench Press", Muscle.CHEST,
                ExerciseType.WEIGHTED, returnMap));
        chestExercises.add(new Exercise("Incline Barbell Bench Press", Muscle.CHEST,
                ExerciseType.WEIGHTED, returnMap));
        chestExercises.add(new Exercise("Incline Dumbbell Bench Press", Muscle.CHEST,
                ExerciseType.WEIGHTED, returnMap));
        chestExercises.add(new Exercise("Decline Barbell Bench Press", Muscle.CHEST,
                ExerciseType.WEIGHTED, returnMap));
        chestExercises.add(new Exercise("Decline Dumbbell Bench Press", Muscle.CHEST,
                ExerciseType.WEIGHTED, returnMap));
        chestExercises.add(new Exercise("Chest Dip", Muscle.CHEST,
                ExerciseType.WEIGHTED, returnMap));
        chestExercises.add(new Exercise("Push Up", Muscle.CHEST,
                ExerciseType.WEIGHTED, returnMap));

        TreeSet<Exercise> backExercises = new TreeSet<>();
        backExercises.add(new Exercise("Deadlift", Muscle.BACK,
                ExerciseType.WEIGHTED, returnMap));
        backExercises.add(new Exercise("Barbell Row", Muscle.BACK,
                ExerciseType.WEIGHTED, returnMap));
        backExercises.add(new Exercise("Pullup", Muscle.BACK,
                ExerciseType.WEIGHTED, returnMap));
        backExercises.add(new Exercise("V Bar Cable Pull", Muscle.BACK,
                ExerciseType.WEIGHTED, returnMap));
        backExercises.add(new Exercise("Lat Pulldown", Muscle.BACK,
                ExerciseType.WEIGHTED, returnMap));

        TreeSet<Exercise> shoulderExercises = new TreeSet<>();
        shoulderExercises.add(new Exercise("Overhead Press", Muscle.SHOULDERS,
                ExerciseType.WEIGHTED, returnMap));
        shoulderExercises.add(new Exercise("Dumbbell Shoulder Press", Muscle.SHOULDERS,
                ExerciseType.WEIGHTED, returnMap));
        shoulderExercises.add(new Exercise("Cable Rope Pull", Muscle.SHOULDERS,
                ExerciseType.WEIGHTED, returnMap));

        TreeSet<Exercise> bicepExercises = new TreeSet<>();
        bicepExercises.add(new Exercise("Dumbbell Curl", Muscle.BICEPS,
                ExerciseType.WEIGHTED, returnMap));
        bicepExercises.add(new Exercise("Barbell Curl", Muscle.BICEPS,
                ExerciseType.WEIGHTED, returnMap));
        bicepExercises.add(new Exercise("Cable Rope Curl", Muscle.BICEPS,
                ExerciseType.WEIGHTED, returnMap));

        TreeSet<Exercise> tricepExercises = new TreeSet<>();
        tricepExercises.add(new Exercise("Overhead Tricep Extension", Muscle.TRICEPS,
                ExerciseType.WEIGHTED, returnMap));
        tricepExercises.add(new Exercise("Rope Tricep Extension", Muscle.TRICEPS,
                ExerciseType.WEIGHTED, returnMap));
        tricepExercises.add(new Exercise("VBar Tricep Extension", Muscle.TRICEPS,
                ExerciseType.WEIGHTED, returnMap));
        tricepExercises.add(new Exercise("Tricep Dip", Muscle.TRICEPS,
                ExerciseType.WEIGHTED, returnMap));

        TreeSet<Exercise> legExercises = new TreeSet<>();
        legExercises.add(new Exercise("Leg Machine Extension", Muscle.LEGS,
                ExerciseType.WEIGHTED, returnMap));
        legExercises.add(new Exercise("Back Squat", Muscle.LEGS,
                ExerciseType.WEIGHTED, returnMap));
        legExercises.add(new Exercise("Leg Press", Muscle.LEGS,
                ExerciseType.WEIGHTED, returnMap));
        legExercises.add(new Exercise("Lunges", Muscle.LEGS,
                ExerciseType.WEIGHTED, returnMap));
        legExercises.add(new Exercise("Front Squat", Muscle.LEGS,
                ExerciseType.WEIGHTED, returnMap));

        return returnMap;
    }
}
