package com.example.fitnessapp.model;

import java.util.ArrayList;
import java.util.Collections;

public class WeightedExerciseInstance extends ExerciseInstance {
    private ArrayList<WeightedSet> weightedSets;
    private double oneRepetitionMax;

    public WeightedExerciseInstance(Exercise exercise) {
        super(exercise);
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
        findAndUpdateOneRepetitionMax();
        return index;
    }

    public void remove(int i) {
        double removedOneRepetitionMax = weightedSets.get(i).getOneRepetitionMax();
        weightedSets.remove(i);
        findAndUpdateOneRepetitionMax();
    }

    private void findAndUpdateOneRepetitionMax() {
        double max = Double.MIN_VALUE;
        for (WeightedSet ws : weightedSets) {
            double currentOneRepetitionMax = ws.getOneRepetitionMax();
            if (max < currentOneRepetitionMax) {
                max = currentOneRepetitionMax;
            }
        }
        oneRepetitionMax = max;
    }

    public void removeLastSet() {
        if (weightedSets.size() > 0)
            remove(weightedSets.size() - 1);
    }

    public void switchIndices(int i, int j) {
        Collections.swap(weightedSets, i, j);
    }

    public Exercise getExercise() {
        return super.getExercise();
    }

}
