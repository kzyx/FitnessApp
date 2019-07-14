package com.example.fitnessapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
/**
 * Created by JUNED on 6/10/2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    String[] SubjectValues;
    Context context;
    View view1;
    ViewHolder viewHolder1;
    TextView textView;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void OnItemClick(int itemIndex);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public RecyclerViewAdapter(Context context1, String[] SubjectValues1){

        SubjectValues = SubjectValues1;
        context = context1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;

        public ViewHolder(View v, final OnItemClickListener listener){
            super(v);
            textView = (TextView)v.findViewById(R.id.subject_textview);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int index = getAdapterPosition();
                        if (index != RecyclerView.NO_POSITION) {
                            listener.OnItemClick(index);
                        }
                    }
                }
            });
        }
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        view1 = LayoutInflater.from(context).inflate(R.layout.recyclerview_items, parent,false);
        viewHolder1 = new ViewHolder(view1, listener);
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        holder.textView.setText(SubjectValues[position]);
        //holder.textView.setHeight(50);
    }

    @Override
    public int getItemCount(){
        return SubjectValues.length;
    }
}