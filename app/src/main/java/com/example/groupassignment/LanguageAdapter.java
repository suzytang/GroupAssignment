package com.example.groupassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.MyViewHolder> {
    Context context;
    DatabaseHelper myDb;
    private ArrayList<Languages> languages;
    RecyclerViewClickListener listener;

    // The following code is modified from: Coding in Flow (2017)
    // 'SQLite + RecyclerView - Part 2 - CURSOR AND RECYCLERVIEW ADAPTER - Android Studio Tutorial'
    // https://www.youtube.com/watch?v=_m-Ve-BAYe0&t=

    // Constructor
    public LanguageAdapter(Context context,ArrayList<Languages> languages, RecyclerViewClickListener listener) {
        this.languages = languages;
        this.context = context;
        this.listener = listener;
    }

    // Create listener
    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    // Inflate languages_list and return view
    @Override
    public LanguageAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.languages_list, parent, false);
        return new LanguageAdapter.MyViewHolder(v, listener);
    }

    // Replace contents of the view with data
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String name = (languages.get(position).getLanguage());

        myDb = new DatabaseHelper(context);
        // Decreases opacity of item if language has been selected and makes "selected" textview visible
        if (myDb.checkCurrent(name)) {
            holder.language.setAlpha((float) 0.1);
            holder.difficulty.setAlpha((float) 0.1);
            holder.image.setAlpha((float) 0.1);
            holder.selected.setVisibility(View.VISIBLE);
        } else {
            holder.language.setAlpha((float) 1);
            holder.difficulty.setAlpha((float) 1);
            holder.image.setAlpha((float) 1);
            holder.selected.setVisibility(View.INVISIBLE);
        }

        holder.language.setText(name);
        holder.difficulty.setText(languages.get(position).getDifficulty());
        holder.image.setImageResource(languages.get(position).getImage());

    }

    // Return size of dataset
    @Override
    public int getItemCount() {
        return languages.size();
    }

    // Create ViewHolder for languages_list
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView language;
        TextView difficulty;
        ImageView image;
        TextView selected;
        LanguageAdapter.RecyclerViewClickListener listener;

        public MyViewHolder(View itemView, LanguageAdapter.RecyclerViewClickListener listener) {
            super(itemView);

            itemView.setOnClickListener(this);

            this.listener = listener;
            this.language = itemView.findViewById(R.id.language);
            this.difficulty = itemView.findViewById(R.id.difficulty);
            this.image = itemView.findViewById(R.id.image);
            this.selected = itemView.findViewById(R.id.selected);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v,getAdapterPosition());
        }
    }
}
