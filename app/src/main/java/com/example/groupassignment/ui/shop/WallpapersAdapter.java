package com.example.groupassignment.ui.shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment.R;
import com.example.groupassignment.SQLiteHelper;

import java.util.ArrayList;

public class WallpapersAdapter extends RecyclerView.Adapter<WallpapersAdapter.MyViewHolder> {

    WallpapersAdapter.RecyclerViewClickListener listener;
    final Shop shop = new Shop();
    Context context;
    SQLiteHelper db;


    public WallpapersAdapter(Context context, WallpapersAdapter.RecyclerViewClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    @Override
    public WallpapersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_list_item, parent, false);
        return new WallpapersAdapter.MyViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(WallpapersAdapter.MyViewHolder holder, int position) {

        holder.imageBG.setVisibility(View.INVISIBLE);

        String name = shop.getWallpapers().get(position).getItemName();

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
        holder.itemPrice.setText(Integer.toString(shop.getWallpapers().get(position).getItemPrice()));
        holder.image.setImageResource(shop.getWallpapers().get(position).getImage());


    }

    @Override
    public int getItemCount() {
        return shop.getWallpapers().size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView itemName, itemPrice;
        ImageView image;
        ImageView imageBG;
        ImageView coinImage;
        TextView owned;
        WallpapersAdapter.RecyclerViewClickListener listener;

        public MyViewHolder( View itemView, WallpapersAdapter.RecyclerViewClickListener listener) {
            super(itemView);

            itemView.setOnClickListener(this);

            this.listener = listener;
            this.itemName = itemView.findViewById(R.id.itemName);
            this.itemPrice = itemView.findViewById(R.id.itemPrice);
            this.image = itemView.findViewById(R.id.image);
            this.coinImage = itemView.findViewById(R.id.coinImage);
            this.imageBG = itemView.findViewById(R.id.imageBG);
            this.owned = itemView.findViewById(R.id.owned);
        }

        @Override
        public void onClick(View v) {

            listener.onClick(v,getAdapterPosition());

        }
    }
}
