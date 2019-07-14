package com.example.fitnessapp;

import java.util.ArrayList;
import java.util.Collections;

public class WeightedExerciseInstance extends ExerciseInstance {

    private ArrayList<WeightedSet> weightedSets;
    private double oneRepetitionMax;
    private Exercise exercise;

    public WeightedExerciseInstance(Exercise exercise) {
        super(exercise);
        this.exercise = exercise;
        weightedSets = new ArrayList<>();
    }

    public void addSet(WeightedSet w) {
        weightedSets.add(w);
        double addedOneRepetitionMax = w.getOneRepetitionMax();
        if (oneRepetitionMax <= addedOneRepetitionMax) {
            oneRepetitionMax = addedOneRepetitionMax;
        }
    }

    public void removeSet(WeightedSet w) {
        weightedSets.remove(w);
        double removedOneRepetitionMax = w.getOneRepetitionMax();
        //TODO: FINISH
//        if (oneRepetitionMax = removedOneRepetitionMax) {
//            int counter = 0;
//            int max = 0;
//            for (WeightedSet ws : weightedSets) {
//                if (ws.getOneRepetitionMax() == removedOneRepetitionMax) {
//
//                } else if (ws.getOneRepetitionMax() >= removedOneRepetitionMax) {
//
//                }
//            }
//        }
    }

    public void switchIndices(int i, int j) {
        Collections.swap(weightedSets, i, j);
    }

    public Exercise getExercise() {
        return exercise;
    }

}
