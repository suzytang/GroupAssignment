package com.example.groupassignment.ui.selflearn;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment.R;

import java.util.ArrayList;

public class LeaderboardRecyclerAdapter extends RecyclerView.Adapter<LeaderboardRecyclerAdapter.MyViewHolder> {
    private static final String TAG = "LeaderboardAdapter";


    ArrayList<String> myArray;
    //RecyclerViewClickListener listener;

    //public LeaderboardRecyclerAdapter(ArrayList<String> myArray, RecyclerViewClickListener listener) {
    public LeaderboardRecyclerAdapter(ArrayList<String> myArray) {
        this.myArray = myArray;
        //this.listener = listener;
    }



    /*public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }*/

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.leaderboard_list, parent, false);
        //return new MyViewHolder(v, listener);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText("Leaderboard " + (position + 1));

    }

    @Override
    public int getItemCount() {
        return myArray.size();
    }


    //public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        //RecyclerViewClickListener listener;

        //public MyViewHolder( View itemView, RecyclerViewClickListener listener) {
        public MyViewHolder( View itemView) {
            super(itemView);

            //itemView.setOnClickListener(this);

            //this.listener = listener;
            this.textView = itemView.findViewById(R.id.textView);
        }

        /*@Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: inside myadapter on click");
            listener.onClick(v,getAdapterPosition());
        }*/
    }
}
