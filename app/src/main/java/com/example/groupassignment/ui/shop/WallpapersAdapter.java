package com.example.groupassignment.ui.shop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment.R;

import java.util.ArrayList;

public class WallpapersAdapter extends RecyclerView.Adapter<WallpapersAdapter.MyViewHolder> {

    WallpapersAdapter.RecyclerViewClickListener listener;
    final Shop shop = new Shop();
    ArrayList<Shop> wallpapers = new ArrayList<>();

    public WallpapersAdapter(ArrayList<Shop> wallpapers, WallpapersAdapter.RecyclerViewClickListener listener) {
        this.wallpapers = wallpapers;
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
        holder.itemName.setText(shop.getWallpapers().get(position).getItemName());
        holder.itemPrice.setText(shop.getWallpapers().get(position).getItemPrice() + " coins");
        holder.image.setImageResource(shop.getWallpapers().get(position).getImage());


    }

    @Override
    public int getItemCount() {
        return shop.getWallpapers().size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView itemName, itemPrice;
        ImageView image;
        WallpapersAdapter.RecyclerViewClickListener listener;

        public MyViewHolder( View itemView, WallpapersAdapter.RecyclerViewClickListener listener) {
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
