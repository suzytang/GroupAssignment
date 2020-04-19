package com.example.groupassignment.ui.learn;

import android.content.Context;
import android.content.Intent;
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
    private static final String TAG = "LearnAdapter";
    private Context mContext;

    ArrayList<LearnCategories> categories;

    public LearnRecyclerAdapter(Context context,ArrayList<LearnCategories> categories) {
        this.categories = categories;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.learn_levels, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.levelText.setText(categories.get(position).getCategoryName());
        holder.categoryImage.setImageResource(categories.get(position).getImage());

        holder.learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, LearnFlashcards.class);
                intent.putExtra("level",categories.get(position).getLevel());
                intent.putExtra("table","learn_table");
                mContext.startActivity(intent);
            }
        });
        holder.practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Practice.class);
                intent.putExtra("level",categories.get(position).getLevel());
                intent.putExtra("table","learn_table");
                mContext.startActivity(intent);
            }
        });

        holder.quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, QuizTest.class);
                intent.putExtra("level", categories.get(position).getLevel());
                intent.putExtra("table","learn_table");
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
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
