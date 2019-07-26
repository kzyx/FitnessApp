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

    //List<WeightedSet> list;
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

        if(selected) {
            holder.itemView.setBackgroundColor(Color.parseColor("#00FA9A"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
        }

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

    public void setSelected(WEViewHolder holder) {
        holder.cv.setCardBackgroundColor(Color.parseColor("#000000"));
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
