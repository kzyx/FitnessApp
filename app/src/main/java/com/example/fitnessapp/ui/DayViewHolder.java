package com.example.fitnessapp.ui;

import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;

public class DayViewHolder extends RecyclerView.ViewHolder {
    CardView cv;
    TextView exerciseName;
    TextView muscle;
    TextView oneRepetitionMax;
    RecyclerView dayView;

    DayViewHolder(View itemView) {
        super(itemView);
        cv = itemView.findViewById(R.id.cardView);
        dayView = itemView.findViewById(R.id.dayView);
        exerciseName = itemView.findViewById(R.id.exerciseNameText);
        muscle = itemView.findViewById(R.id.muscleText);
        oneRepetitionMax = itemView.findViewById(R.id.oneRepetitionMaxText);
    }
}
