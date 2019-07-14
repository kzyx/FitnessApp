//package com.example.fitnessapp;
//
////import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class StringAdapter extends RecyclerView.Adapter<StringAdapter.StringHolder>{
//
//    private List<String> stringList;
//
//    public StringAdapter(List<String> stringList) {
//        this.stringList = stringList;
//    }
//
//    @Override
//    public StringHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.string_list_row, parent, false);
//
//        return new StringHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(StringHolder holder, int position) {
//        holder.name.setText(stringList.get(position));
//       // holder.author.setText(bookList.get(position).getAuthor());
//    }
//
//    @Override
//    public int getItemCount() {
//        return stringList.size();
//    }
//
//    public class StringHolder extends RecyclerView.ViewHolder {
//        public TextView name;
//
//        public StringHolder(View view) {
//            super(view);
//            name = (TextView) view.findViewById(R.id.name);
//            //author = (TextView) view.findViewById(R.id.author);
//        }
//    }
//}
