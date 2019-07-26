package com.example.fitnessapp.ui;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;

public class WEViewHolder extends RecyclerView.ViewHolder {

    CardView cv;
    RelativeLayout relativeLayout;
    TextView weight;
    TextView repetition;
    TextView listingNum;

    WEViewHolder(View itemView) {
        super(itemView);
        cv = itemView.findViewById(R.id.cardView);
        relativeLayout = itemView.findViewById(R.id.weightRepLayout);
        listingNum = itemView.findViewById(R.id.listingNum);
        weight = itemView.findViewById(R.id.weightText);
        repetition = itemView.findViewById(R.id.repetitionText);
    }
}
