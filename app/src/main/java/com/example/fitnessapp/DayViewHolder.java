package com.example.fitnessapp;

import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class DayViewHolder extends RecyclerView.ViewHolder {

    CardView cv;
    TextView exerciseName;
    TextView muscle;
    RecyclerView exerciseView;
//    TextView weight;
//    TextView repetition;
//    TextView listingNum;

    DayViewHolder(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardView);
        exerciseView = itemView.findViewById(R.id.exerciseView);
        exerciseName = (TextView) itemView.findViewById(R.id.exerciseNameText);
        muscle = (TextView) itemView.findViewById(R.id.muscleText);
    }
}
