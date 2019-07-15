package com.example.fitnessapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class DayRecyclerViewAdapter extends RecyclerView.Adapter<DayViewHolder> {

    //List<WeightedSet> list;
    WorkoutDay wd;
    Context context;

    public DayRecyclerViewAdapter(WorkoutDay wd, Context context) {
        this.wd = wd;
        this.context = context;
    }


    @Override
    public DayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.we_exerciselayout, parent, false);
        DayViewHolder holder = new DayViewHolder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(DayViewHolder holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        //holder.cv.setText(list.get(position).title);
        holder.exerciseName.setText(wd.get(position).getExercise().getName());
        holder.muscle.setText(wd.get(position).getExercise().getTargetedMuscle().toString().toLowerCase());
        //TODO: FIX THIS PASSING STUFF
        // holder.exerciseView.setAdapter(new WERecyclerViewAdapter(new Weight));
        //animate(holder);

    }

    @Override
    public int getItemCount() {
        //returns the number of elements the RecyclerView will display
        return wd.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, ExerciseInstance ei) {
        wd.add(position, ei);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(ExerciseInstance ei) {
        // int position = list.indexOf(weightedSet);
        int removedIndex = wd.remove(ei);
        notifyItemRemoved(removedIndex);
    }



}
