package com.example.groupassignment.ui.learn;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment.DatabaseHelper;
import com.example.groupassignment.LearnData;
import com.example.groupassignment.MainActivity;
import com.example.groupassignment.R;

import java.util.ArrayList;

public class LearnRecyclerAdapter extends RecyclerView.Adapter<LearnRecyclerAdapter.MyViewHolder> {
    private static final String TAG = "LearnAdapter";
    private Context mContext;

    ArrayList<String> myArray;
    RecyclerViewClickListener listener;

    public LearnRecyclerAdapter(Context context,ArrayList<String> myArray, RecyclerViewClickListener listener) {
        this.myArray = myArray;
        this.listener = listener;
        this.mContext = context;
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
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.levelText.setText(LearnData.getLearnData().get(10*position+1).getCategory());
        holder.learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(mContext, LearnFlashcards.class);
                intent2.putExtra("level",myArray.get(position));
                mContext.startActivity(intent2);
            }
        });

        holder.quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(mContext, QuizTest.class);
                intent2.putExtra("level", myArray.get(position));
                mContext.startActivity(intent2);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myArray.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView levelText;
        RecyclerViewClickListener listener;
        Button quiz;
        Button learn;

        public MyViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);

            itemView.setOnClickListener(this);
            this.listener = listener;
            this.levelText = itemView.findViewById(R.id.category);
            this.quiz = itemView.findViewById(R.id.quiz);
            this.learn = itemView.findViewById(R.id.learn);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: inside myadapter on click");
            listener.onClick(v,getAdapterPosition());
        }
    }
}
