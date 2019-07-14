package com.example.fitnessapp;

import com.example.fitnessapp.ExerciseEnum.Muscle;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class ExerciseMap {
    private HashMap<Muscle, TreeSet<Exercise>> exerciseMap;

    public ExerciseMap() {
        exerciseMap = new HashMap<>();
        for (Muscle tm : Muscle.values()) {
            exerciseMap.put(tm, new TreeSet<Exercise>());
        }
    }

    public void addExercise(Exercise exercise) {
        TreeSet<Exercise> exercises = exerciseMap.get(exercise.getTargetedMuscle());
        exercises.add(exercise);
    }

    public TreeSet<Exercise> getExerciseSet() {
        TreeSet<Exercise> returnSet = new TreeSet<>();
        Collection<TreeSet<Exercise>> allExercises = exerciseMap.values();

        for (TreeSet<Exercise> set : allExercises) {
            returnSet.addAll(set);
        }
        return returnSet;
    }

    public TreeSet<String> getTargetedMuscleStrings() {
        Set<Muscle> allTargetedMuscles = exerciseMap.keySet();
        TreeSet<String> returnSet = new TreeSet<>();
        for (Muscle t : allTargetedMuscles) {
            returnSet.add(t.toString().substring(0,1).toUpperCase() +
                              t.toString().substring(1).toLowerCase());
        }

        return returnSet;
    }

    public String[] getTMString() {
        Set<Muscle> allTargetedMuscles = exerciseMap.keySet();
        TreeSet<String> returnSet = new TreeSet<>();
        for (Muscle t : allTargetedMuscles) {
            returnSet.add(t.toString().substring(0,1).toUpperCase() +
                    t.toString().substring(1).toLowerCase());
        }
        String[] arr = new String[returnSet.size()];
        int i = 0;
        for (String s : returnSet) {
            arr[i] = s;
            i++;
        }
        return arr;
    }

}
