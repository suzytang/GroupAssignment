package com.example.groupassignment.ui.pet;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment.R;
import com.example.groupassignment.SQLiteHelper;
import com.example.groupassignment.ui.shop.Shop;

import java.util.ArrayList;

public class InvAccessoriesAdapter extends RecyclerView.Adapter<InvAccessoriesAdapter.MyViewHolder> {

    private Context context;
    private Cursor cursor;

    public InvAccessoriesAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public InvAccessoriesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_item, parent, false);
        return new InvAccessoriesAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(InvAccessoriesAdapter.MyViewHolder holder, int position) {
        if(!cursor.moveToPosition(position)){
            return;
        }
        String accessory = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COL_2));
        holder.itemName.setText(accessory);

    }

    @Override
    public int getItemCount() {
        return cursor.getCount();

    }

    public void swapCursor (Cursor newCursor){
        if (cursor != null){
            cursor.close();
        }

        cursor = newCursor;

        if (newCursor != null){
            notifyDataSetChanged();
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView itemName;
        public Button apply;
        public ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.itemName = itemView.findViewById(R.id.itemName);
            this.apply = itemView.findViewById(R.id.apply);
            this.image = itemView.findViewById(R.id.image);
        }

    }
}

