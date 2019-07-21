package com.example.fitnessapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DayRecyclerViewAdapter extends RecyclerView.Adapter<DayViewHolder> {

    WorkoutDay wd;
    Context context;
    int selectedIndex;
    GridLayoutManager glm;


    public DayRecyclerViewAdapter(WorkoutDay wd, Context context, RecyclerView rv) {
        this.wd = wd;
        this.context = context;
        selectedIndex = RecyclerView.NO_POSITION;
    }


    @Override
    public DayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.we_exerciselayout, parent, false);
        DayViewHolder holder = new DayViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(DayViewHolder holder, final int position) {
        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        //holder.cv.setText(list.get(position).title);
        holder.exerciseName.setText(wd.get(position).getExercise().getName());
        holder.muscle.setText(wd.get(position).getExercise().getTargetedMuscle().toString().toLowerCase());
        //TODO: FIX THIS PASSING STUFF
        holder.exerciseView.setAdapter(new WERecyclerViewAdapter((WeightedExerciseInstance) wd.get(position), context));
        glm = new GridLayoutManager(context, 3);
        holder.exerciseView.setLayoutManager(glm);

        if(selectedIndex == position) {
            holder.itemView.setBackgroundColor(Color.parseColor("#000000"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
            //holder.exerciseView setBackgroundColor(Color.parseColor("#ffffff"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedIndex = position;
                notifyDataSetChanged();

            }
        });
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

    public ExerciseInstance getSelectedExercise() {
        return wd.get(selectedIndex);
    }

    public void removeLastItem() {
        if (wd.size() >= 1)
            wd.remove(wd.get(wd.size() - 1));
    }


}
