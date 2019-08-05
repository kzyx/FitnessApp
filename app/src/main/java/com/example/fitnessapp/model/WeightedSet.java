package com.example.fitnessapp.model;

public class WeightedSet {
    private final Exercise exercise;
    private double weight;
    private int repetitions;
    private double oneRepetitionMax;


    public WeightedSet(Exercise exercise, double weight, int repetitions) {
        this.exercise = exercise;
        this.weight = weight;
        this.repetitions = repetitions;
        calculateOneRepetitionMax();
    }

    // Uses Epley formula to calculate one repetition max
    private void calculateOneRepetitionMax() {
        oneRepetitionMax = weight * (1.0 + repetitions / ((double) 30));
    }

    public Exercise getExercise() {
        return exercise;
    }

    public double getWeight() {
        return weight;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public double getOneRepetitionMax() {
        return oneRepetitionMax;
    }

    public void setWeight(double weight) {
        this.weight = weight;
        calculateOneRepetitionMax();
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
        calculateOneRepetitionMax();
    }
}
