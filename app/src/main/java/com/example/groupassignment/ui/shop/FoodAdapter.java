package com.example.groupassignment.ui.shop;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment.R;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {

    RecyclerViewClickListener listener;
    Dialog dialog;

    public FoodAdapter(RecyclerViewClickListener listener) {

        this.listener = listener;
    }



    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    @Override
    public FoodAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_list_item, parent, false);
        return new FoodAdapter.MyViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(FoodAdapter.MyViewHolder holder, int position) {
        holder.itemName.setText("Food");
        holder.itemPrice.setText("20 coins");
        holder.image.setImageResource(R.drawable.food_full);


    }

    @Override
    public int getItemCount() {
        return 1;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView itemName;
        TextView itemPrice;
        ImageView image;
        RecyclerViewClickListener listener;

        public MyViewHolder( View itemView, RecyclerViewClickListener listener) {
            super(itemView);

            itemView.setOnClickListener(this);

            this.listener = listener;
            this.itemName = itemView.findViewById(R.id.itemName);
            this.itemPrice = itemView.findViewById(R.id.itemPrice);
            this.image = itemView.findViewById(R.id.image);
        }

        @Override
        public void onClick(View v) {

            listener.onClick(v,getAdapterPosition());

        }
    }
}

