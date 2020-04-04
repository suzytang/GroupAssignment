package com.example.groupassignment.ui.shop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AccessoriesAdapter extends RecyclerView.Adapter<AccessoriesAdapter.MyViewHolder> {

    RecyclerViewClickListener listener;
    final Shop shop = new Shop();
    ArrayList<Shop> accessories = new ArrayList<>();

    public AccessoriesAdapter(ArrayList<Shop> accessories, RecyclerViewClickListener listener) {
        this.accessories = accessories;
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
        holder.itemName.setText(shop.getAccessories().get(position).getItemName());
        holder.itemPrice.setText(shop.getAccessories().get(position).getItemPrice());


    }

    @Override
    public int getItemCount() {
        return shop.getAccessories().size();
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
