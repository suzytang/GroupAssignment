package com.example.groupassignment.ui.pet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment.R;
import com.example.groupassignment.ui.shop.Shop;

import java.util.ArrayList;

public class InvAccessoriesAdapter extends RecyclerView.Adapter<InvAccessoriesAdapter.MyViewHolder> {

    final Shop shop = new Shop();
    ArrayList<Shop> accessories = new ArrayList<>();

    public InvAccessoriesAdapter(ArrayList<Shop> accessories) {
        this.accessories = accessories;
    }


    @Override
    public InvAccessoriesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_item, parent, false);
        return new InvAccessoriesAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(InvAccessoriesAdapter.MyViewHolder holder, int position) {
        holder.itemName.setText(shop.getAccessories().get(position).getItemName());

    }

    @Override
    public int getItemCount() {
        return shop.getAccessories().size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView itemName;
        Button apply;
        ImageView image;

        public MyViewHolder( View itemView) {
            super(itemView);

            this.itemName = itemView.findViewById(R.id.itemName);
            this.apply = itemView.findViewById(R.id.apply);
            this.image = itemView.findViewById(R.id.image);
        }

    }
}

