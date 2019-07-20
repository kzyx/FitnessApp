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

    public int size() {
        return weightedSets.size();
    }

    public WeightedSet get(int i) {
        return weightedSets.get(i);
    }

    public void addSet(WeightedSet w) {
        weightedSets.add(w);
        double addedOneRepetitionMax = w.getOneRepetitionMax();
        if (oneRepetitionMax <= addedOneRepetitionMax) {
            oneRepetitionMax = addedOneRepetitionMax;
        }
    }

    public void add(int i, WeightedSet w) {
        weightedSets.add(w);
        double addedOneRepetitionMax = w.getOneRepetitionMax();
        if (oneRepetitionMax <= addedOneRepetitionMax) {
            oneRepetitionMax = addedOneRepetitionMax;
        }
    }

    public int removeSet(WeightedSet w) {
        int index =  weightedSets.indexOf(w);
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
        return index;
    }

    public void remove(int i) {
        double removedOneRepetitionMax = weightedSets.get(i).getOneRepetitionMax();
        weightedSets.remove(i);
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

    public void removeLastSet() {
        if (weightedSets.size() > 0)
            remove(weightedSets.size() - 1);
    }

    public void switchIndices(int i, int j) {
        Collections.swap(weightedSets, i, j);
    }

    public Exercise getExercise() {
        return exercise;
    }

}
