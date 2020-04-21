package com.example.groupassignment.ui.learn;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment.R;

import java.util.ArrayList;

public class LearnRecyclerAdapter extends RecyclerView.Adapter<LearnRecyclerAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<LearnCategories> categories;

    // Constructor
    public LearnRecyclerAdapter(Context context,ArrayList<LearnCategories> categories) {
        this.categories = categories;
        this.mContext = context;
    }

    // Inflate learn_levels and return view
    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.learn_levels, parent, false);
        return new MyViewHolder(v);
    }

    // Replace contents of the view with data
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.levelText.setText(categories.get(position).getCategoryName());
        holder.categoryImage.setImageResource(categories.get(position).getImage());

        // On click buttons start respective activities and passes on the category
        holder.learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, LearnFlashcards.class);
                intent.putExtra("category",categories.get(position).getLevel());
                mContext.startActivity(intent);
            }
        });
        holder.practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Practice.class);
                intent.putExtra("category",categories.get(position).getLevel());
                mContext.startActivity(intent);
            }
        });

        holder.quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, QuizTest.class);
                intent.putExtra("category", categories.get(position).getLevel());
                mContext.startActivity(intent);
            }
        });
    }

    // Return size of dataset
    @Override
    public int getItemCount() {
        return categories.size();
    }

    // Create ViewHolder for learn_levels
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView levelText;
        Button quiz;
        Button learn;
        Button practice;
        ImageView categoryImage;


        public MyViewHolder(View itemView) {
            super(itemView);

            this.levelText = itemView.findViewById(R.id.category);
            this.quiz = itemView.findViewById(R.id.storeButton);
            this.learn = itemView.findViewById(R.id.learn);
            this.practice = itemView.findViewById(R.id.practice);
            this.categoryImage = itemView.findViewById(R.id.categoryImage);
        }
    }
}
