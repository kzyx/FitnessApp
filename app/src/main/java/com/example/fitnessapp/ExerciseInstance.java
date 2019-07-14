package com.example.fitnessapp;

import java.sql.Time;
import java.util.Calendar;

public class ExerciseInstance {

    private Exercise exercise;
    private Time creationTime;
    private Time lastModifiedTime;

    public ExerciseInstance(Exercise exercise) {
        creationTime = new Time(Calendar.getInstance().getTimeInMillis());
        lastModifiedTime = creationTime;
        this.exercise = exercise;
    }

    public void setLastModifiedTime(Time lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public Exercise getExercise() {
        return exercise;
    }
}
