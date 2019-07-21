package com.example.fitnessapp;

import com.example.fitnessapp.ExerciseEnum.Muscle;

import java.util.ArrayList;
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
        if (!exerciseMap.get(exercise.getTargetedMuscle()).contains(exercise)) {
            TreeSet<Exercise> exercises = exerciseMap.get(exercise.getTargetedMuscle());
            exercises.add(exercise);
        }
    }

    public ArrayList<String> getExercisesStringList(int position) {
//        ExerciseEnum.Muscle em = ExerciseEnum.Muscle
        ArrayList<Exercise> curr = new ArrayList<>(exerciseMap.get(ExerciseEnum.Muscle.values()[position]));
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < curr.size(); i++) {
            result.add(curr.get(i).getName());
        }
        return result;
    }

    public ArrayList<Exercise> getExerciseList(ExerciseEnum.Muscle muscle) {
        ArrayList<Exercise> returnSet = new ArrayList<>();
        TreeSet<Exercise> allExercises = exerciseMap.get(muscle);

        for (Exercise e : allExercises) {
            returnSet.add(e);
        }
        return returnSet;
    }

    public TreeSet<Exercise> getExerciseSet() {
        TreeSet<Exercise> returnSet = new TreeSet<>();
        Collection<TreeSet<Exercise>> allExercises = exerciseMap.values();

        for (TreeSet<Exercise> set : allExercises) {
            returnSet.addAll(set);
        }
        return returnSet;
    }

    public ArrayList<String> getTargetedMuscleStrings() {
        Set<Muscle> allTargetedMuscles = exerciseMap.keySet();
        ArrayList<String> returnSet = new ArrayList<>();
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
