package com.example.fitnessapp;

import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class WEViewHolder extends RecyclerView.ViewHolder {

    CardView cv;
    TextView weight;
    TextView repetition;

    WEViewHolder(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardView);
        weight = (TextView) itemView.findViewById(R.id.weightText);
        repetition = (TextView) itemView.findViewById(R.id.repetitionText);
    }
}
