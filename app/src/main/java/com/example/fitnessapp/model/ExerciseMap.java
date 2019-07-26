package com.example.fitnessapp.model;

import com.example.fitnessapp.model.ExerciseEnum.Muscle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class ExerciseMap {
    private HashMap<Muscle, TreeSet<Exercise>> exerciseMap;

    public ExerciseMap() {
        exerciseMap = new HashMap<>();
        for (Muscle tm : Muscle.values()) {
            exerciseMap.put(tm, new TreeSet<>());
        }
    }

    public void addExercise(Exercise exercise) {
        if (!exerciseMap.get(exercise.getMuscle()).contains(exercise)) {
            TreeSet<Exercise> exercises = exerciseMap.get(exercise.getMuscle());
            exercises.add(exercise);
        }
    }

    public ArrayList<String> getExerciseStringList(int position) {
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

}
