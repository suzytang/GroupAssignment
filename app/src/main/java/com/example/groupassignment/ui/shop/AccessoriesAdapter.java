package com.example.groupassignment.ui.shop;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment.R;
import com.example.groupassignment.SQLiteHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AccessoriesAdapter extends RecyclerView.Adapter<AccessoriesAdapter.MyViewHolder> {

    RecyclerViewClickListener listener;
    final Shop shop = new Shop();
    Dialog dialog;
    Context context;
    SQLiteHelper db;

    public AccessoriesAdapter(Context context, RecyclerViewClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    @Override
    public AccessoriesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_list_item, parent, false);
        return new AccessoriesAdapter.MyViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(AccessoriesAdapter.MyViewHolder holder, int position) {

        String name = (shop.getAccessories().get(position).getItemName());

        db = new SQLiteHelper(context);
        // Decreases opacity of item if it has been bought and makes "Owned" textview visible
        if (db.isBought(name)) {
            holder.itemName.setAlpha((float) 0.1);
            holder.itemPrice.setAlpha((float) 0.1);
            holder.image.setAlpha((float) 0.1);
            holder.coinImage.setAlpha((float) 0.1);
            holder.owned.setVisibility(View.VISIBLE);
        } else {
            holder.itemName.setAlpha((float) 1);
            holder.itemPrice.setAlpha((float) 1);
            holder.image.setAlpha((float) 1);
            holder.coinImage.setAlpha((float) 1);
            holder.owned.setVisibility(View.INVISIBLE);
        }

        holder.itemName.setText(name);
        holder.itemPrice.setText(Integer.toString(shop.getAccessories().get(position).getItemPrice()));
        holder.image.setImageResource(shop.getAccessories().get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return shop.getAccessories().size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView itemName;
        TextView itemPrice;
        ImageView image;
        ImageView coinImage;
        TextView owned;
        RecyclerViewClickListener listener;

        public MyViewHolder( View itemView, RecyclerViewClickListener listener) {
            super(itemView);

            itemView.setOnClickListener(this);

            this.listener = listener;
            this.itemName = itemView.findViewById(R.id.itemName);
            this.itemPrice = itemView.findViewById(R.id.itemPrice);
            this.image = itemView.findViewById(R.id.image);
            this.coinImage = itemView.findViewById(R.id.coinImage);
            this.owned = itemView.findViewById(R.id.owned);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v,getAdapterPosition());
        }
    }
}

