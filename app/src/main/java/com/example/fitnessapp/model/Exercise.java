package com.example.fitnessapp.model;

public class Exercise implements Comparable<Exercise> {
    private String name;
    private ExerciseEnum.Muscle muscle;
    private ExerciseType exerciseType;

    public Exercise(String name, ExerciseEnum.Muscle muscle,
                    ExerciseType exerciseType, ExerciseMap exerciseMap) {
        this.name = name;
        this.muscle = muscle;
        this.exerciseType = exerciseType;
        exerciseMap.addExercise(this);
    }

    public String getName() {
        return name;
    }

    public ExerciseEnum.Muscle getMuscle() {
        return muscle;
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
                muscle == exercise.muscle &&
                exerciseType == exercise.exerciseType;
    }

    public int compareTo(Exercise exercise) {
        return name.compareTo(exercise.getName());
    }
}
