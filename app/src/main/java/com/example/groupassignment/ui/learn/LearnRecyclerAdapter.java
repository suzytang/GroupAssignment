package com.example.groupassignment.ui.learn;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment.R;

import java.util.ArrayList;

public class LearnRecyclerAdapter extends RecyclerView.Adapter<LearnRecyclerAdapter.MyViewHolder> {
    private static final String TAG = "LearnAdapter";


    ArrayList<String> myArray;
    RecyclerViewClickListener listener;

    public LearnRecyclerAdapter(ArrayList<String> myArray, RecyclerViewClickListener listener) {
        this.myArray = myArray;
        this.listener = listener;
    }



    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.learn_levels, parent, false);
        return new MyViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText("Level " + (position + 1));

    }

    @Override
    public int getItemCount() {
        return myArray.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView;
        RecyclerViewClickListener listener;

        public MyViewHolder( View itemView, RecyclerViewClickListener listener) {
            super(itemView);

            itemView.setOnClickListener(this);

            this.listener = listener;
            this.textView = itemView.findViewById(R.id.textView);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: inside myadapter on click");
            listener.onClick(v,getAdapterPosition());
        }
    }
}
