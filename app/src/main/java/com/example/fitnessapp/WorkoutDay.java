package com.example.fitnessapp;

import java.time.LocalDate;

public class WorkoutDay {

    LocalDate date;
    ExerciseInstanceList exercises;

    WorkoutDay() {
        date = LocalDate.now();
        exercises = new ExerciseInstanceList();
    }




}
