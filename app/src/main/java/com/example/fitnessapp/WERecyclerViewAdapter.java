package com.example.fitnessapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WERecyclerViewAdapter extends RecyclerView.Adapter<WEViewHolder> {

    //List<WeightedSet> list;
    WeightedExerciseInstance wei;
    Context context;


    public WERecyclerViewAdapter(WeightedExerciseInstance wei, Context context) {
        this.wei = wei;
        this.context = context;
    }


    @Override
    public WEViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.we_rowlayout, parent, false);
        WEViewHolder holder = new WEViewHolder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(WEViewHolder holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        //holder.cv.setText(list.get(position).title);
        double w = wei.get(position).getWeight();
        holder.listingNum.setText(String.format("%d", position + 1));
        holder.weight.setText(String.format("%.2f", w));
        holder.repetition.setText(Integer.toString(wei.get(position).getRepetitions()));

        //animate(holder);

    }

    @Override
    public int getItemCount() {
        //returns the number of elements the RecyclerView will display
        return wei.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, WeightedSet weightedSet) {
        wei.add(position, weightedSet);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(WeightedSet weightedSet) {
       // int position = list.indexOf(weightedSet);
        int removedIndex = wei.removeSet(weightedSet);
        notifyItemRemoved(removedIndex);
    }




}
