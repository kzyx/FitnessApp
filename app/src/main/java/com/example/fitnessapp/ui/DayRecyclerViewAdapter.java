package com.example.fitnessapp.ui;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.model.WeightedExerciseInstance;
import com.example.fitnessapp.model.WorkoutDay;

public class DayRecyclerViewAdapter extends RecyclerView.Adapter<DayViewHolder> {
    WorkoutDay wd;
    Context context;
    int selectedIndex;
    GridLayoutManager glm;


    public DayRecyclerViewAdapter(WorkoutDay wd, Context context) {
        this.wd = wd;
        this.context = context;
        selectedIndex = wd.size() - 1;
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
        holder.exerciseName.setText(wd.get(position).getExercise().getName());
        holder.muscle.setText(Utility.capitalize(wd.get(position).getExercise().getMuscle().toString()));
        holder.oneRepetitionMax.setText(Integer.toString((int) Math.round(wd.get(position).getOneRepetitionMax())));
        WERecyclerViewAdapter insideAdapter = new WERecyclerViewAdapter(wd.get(position), context);
        holder.dayView.setAdapter(insideAdapter);
        glm = new GridLayoutManager(context, 3);
        holder.dayView.setLayoutManager(glm);

        if(selectedIndex == position) {
            holder.itemView.setBackgroundColor(Color.parseColor("#00FA9A"));
            insideAdapter.setSelected();
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
            insideAdapter.setDeselected();
        }

        holder.itemView.setOnClickListener(v -> {
            selectedIndex = position;
            notifyDataSetChanged();
        });
    }


    @Override
    public int getItemCount() {
        return wd.size();
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public int getSelectedIndex() { return selectedIndex;}


    public WeightedExerciseInstance getSelectedExercise() {
        return wd.get(selectedIndex);
    }


    public void insert(int position, WeightedExerciseInstance ei) {
        wd.add(position, ei);
        notifyItemInserted(position);
    }


    public void remove(WeightedExerciseInstance ei) {
        int removedIndex = wd.remove(ei);
        notifyItemRemoved(removedIndex);
    }


    public void removeLastItem() {
        if (wd.size() >= 1)
            wd.remove(wd.get(wd.size() - 1));
        if (selectedIndex >= wd.size())
            selectedIndex = wd.size() - 1; /* -1 is selected index if empty */
    }
}
