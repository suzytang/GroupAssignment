package com.example.groupassignment.ui.pet;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment.R;
import com.example.groupassignment.SQLiteHelper;
import com.example.groupassignment.ui.shop.Shop;

import java.util.ArrayList;

public class InvWallpapersAdapter extends RecyclerView.Adapter<InvWallpapersAdapter.MyViewHolder> {

    Shop shop = new Shop();
    private Context context;
    private Cursor cursor;

    public InvWallpapersAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public InvWallpapersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_item, parent, false);
        return new InvWallpapersAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(InvWallpapersAdapter.MyViewHolder holder, final int position) {
        if(!cursor.moveToPosition(position)){
            return;
        }
        final String wallpaper = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COL_2));
        System.out.println("adapter "+wallpaper);

        holder.itemName.setText(wallpaper);
        holder.image.setImageResource(shop.searchWallpapers(wallpaper).getImage());

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


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView itemName;
        public Button apply;
        public ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.itemName = itemView.findViewById(R.id.itemName);
            this.apply = itemView.findViewById(R.id.apply);
            this.image = itemView.findViewById(R.id.image);

            apply.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            if(!cursor.moveToPosition(getAdapterPosition())){
                return;
            }
            final String wallpaper = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COL_2));

            SQLiteHelper sqLiteHelper = new SQLiteHelper(context);
            sqLiteHelper.applyInventory("'"+wallpaper+"'", "'Wallpapers'");
            System.out.println("onclick test " +wallpaper);

            Toast.makeText(context,  "The wallpaper has been applied",
                    Toast.LENGTH_LONG).show();
        }

    }
}
