package com.example.fitnessapp.model;

import java.sql.Time;
import java.util.Calendar;

public abstract class ExerciseInstance {

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

    public Time getLastModifiedTime() {
        return lastModifiedTime;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public abstract void addSet(WeightedSet ws);
    public abstract int removeSet(WeightedSet ws);
}
