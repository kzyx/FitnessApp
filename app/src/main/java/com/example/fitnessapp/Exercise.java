package com.example.fitnessapp;

public class Exercise implements Comparable<Exercise> {
    private String name;
    private ExerciseEnum.Muscle targetedMuscle;
    private ExerciseType exerciseType;

    public Exercise(String name, ExerciseEnum.Muscle targetedMuscle,
                    ExerciseType exerciseType, ExerciseMap exerciseMap) {
        this.name = name;
        this.targetedMuscle = targetedMuscle;
        exerciseMap.addExercise(this);
    }

    public String getName() {
        return name;
    }

    public ExerciseEnum.Muscle getTargetedMuscle() {
        return targetedMuscle;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exercise)) return false;
        Exercise exercise = (Exercise) o;
        return name.equals(exercise.name) &&
                targetedMuscle == exercise.targetedMuscle &&
                exerciseType == exercise.exerciseType;
    }

    public int compareTo(Exercise exercise) {
        return name.compareTo(exercise.getName());
    }
}
