package com.example.fitnessapp.model;

import java.util.ArrayList;
import java.util.Collections;

public class WeightedExerciseInstance extends ExerciseInstance {
    private ArrayList<WeightedSet> weightedSets;
    private double oneRepetitionMax;


    public WeightedExerciseInstance(Exercise exercise) {
        super(exercise);
        weightedSets = new ArrayList<>();
        oneRepetitionMax = 0;
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
        oneRepetitionMax = Double.max(oneRepetitionMax, addedOneRepetitionMax);
    }

    public void add(int i, WeightedSet w) {
        weightedSets.add(i, w);
        double addedOneRepetitionMax = w.getOneRepetitionMax();
        oneRepetitionMax = Double.max(oneRepetitionMax, addedOneRepetitionMax);
    }

    public int removeSet(WeightedSet w) {
        int index =  weightedSets.indexOf(w);
        weightedSets.remove(w);
        findAndUpdateOneRepetitionMax();
        return index;
    }

    public void remove(int i) {
        weightedSets.remove(i);
        findAndUpdateOneRepetitionMax();
    }

    private void findAndUpdateOneRepetitionMax() {
        double maxValue = 0;
        for (WeightedSet ws : weightedSets) {
            double currentOneRepetitionMax = ws.getOneRepetitionMax();
            maxValue = Double.max(currentOneRepetitionMax, maxValue);
        }
        oneRepetitionMax = maxValue;
    }

    public void removeLastSet() {
        if (weightedSets.size() > 0)
            remove(weightedSets.size() - 1);
        findAndUpdateOneRepetitionMax();
    }

    public void switchIndices(int i, int j) {
        Collections.swap(weightedSets, i, j);
    }

    public Exercise getExercise() {
        return super.getExercise();
    }

    public double getOneRepetitionMax() {
        return oneRepetitionMax;
    }
}
