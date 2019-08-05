package com.example.fitnessapp.ui;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.model.WeightedExerciseInstance;
import com.example.fitnessapp.model.WeightedSet;

public class WERecyclerViewAdapter extends RecyclerView.Adapter<WEViewHolder> {
    WeightedExerciseInstance wei;
    Context context;
    boolean selected;


    public WERecyclerViewAdapter(WeightedExerciseInstance wei, Context context) {
        this.wei = wei;
        this.context = context;
        selected = false;
    }


    @Override
    public WEViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.we_rowlayout, parent, false);
        WEViewHolder holder = new WEViewHolder(v);
        return holder;
    }


    @Override
    public void onBindViewHolder(WEViewHolder holder, int position) {
        double w = wei.get(position).getWeight();
        holder.listingNum.setText(String.format("%d", position + 1));
        holder.weight.setText(String.format("%.2f", w));
        holder.repetition.setText(Integer.toString(wei.get(position).getRepetitions()));
        if(selected) {
            holder.itemView.setBackgroundColor(Color.parseColor("#00FA9A"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
        }

    }


    @Override
    public int getItemCount() {
        return wei.size();
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public void insert(int position, WeightedSet weightedSet) {
        wei.add(position, weightedSet);
        notifyItemInserted(position);
    }


    public void remove(WeightedSet weightedSet) {
        int removedIndex = wei.removeSet(weightedSet);
        notifyItemRemoved(removedIndex);
    }


    public void setSelected() {
        selected = true;
        notifyDataSetChanged();
    }


    public void setDeselected() {
        selected = false;
        notifyDataSetChanged();
    }
}
