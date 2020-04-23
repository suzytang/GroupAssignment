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

    // The following code is modified from: Coding in Flow (2017)
    // 'SQLite + RecyclerView - Part 2 - CURSOR AND RECYCLERVIEW ADAPTER - Android Studio Tutorial'
    // https://www.youtube.com/watch?v=_m-Ve-BAYe0&t=
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
        // Cursor gets the name of the wallpaper
        final String wallpaper = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COL_2));


        // Makes textview saying 'Applied' visible if wallpaper is applied and decreases opacity of recyclerview list item
        SQLiteHelper sqLiteHelper = new SQLiteHelper(context);
        if (sqLiteHelper.isApplied(wallpaper)) {
            holder.itemName.setAlpha((float) 0.1);
            holder.image.setAlpha((float) 0.1);
            holder.apply.setText("Remove");
            holder.applied.setVisibility(View.VISIBLE);
        } else {
            holder.itemName.setAlpha((float) 1);
            holder.image.setAlpha((float) 1);
            holder.apply.setText("Apply");
            holder.applied.setVisibility(View.INVISIBLE);
        }

        // Set text and image for wallpaper in recyclerview
        holder.itemName.setText(wallpaper);
        holder.image.setImageResource(shop.searchWallpapers(wallpaper).getImage());

    }

    // Returns the number of rows in the database based on getAllItems in WallpapersFragment
    @Override
    public int getItemCount() {
        return cursor.getCount();

    }

    // Creates a new cursor for every time the database needs to be updated
    public void swapCursor (Cursor newCursor){
        // Deletes the cursor if it isn't null
        if (cursor != null){
            cursor.close();
        }
        // Assigns newCursor to cursor
        cursor = newCursor;

        if (newCursor != null){
            notifyDataSetChanged();
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView itemName;
        public TextView applied;
        public Button apply;
        public ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.itemName = itemView.findViewById(R.id.itemName);
            this.applied = itemView.findViewById(R.id.applied);
            this.apply = itemView.findViewById(R.id.apply);
            this.image = itemView.findViewById(R.id.image);

            apply.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            if(!cursor.moveToPosition(getAdapterPosition())){
                return;
            }
            // Get NAME based on the item selected
            final String wallpaper = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COL_2));

            SQLiteHelper sqLiteHelper = new SQLiteHelper(context);

            if(apply.getText().equals("Apply")){
                // applyInventory clears APPLIED column (where 1 = applied, and 0 = not applied) of all
                // rows where CATEGORY = 'Wallpapers' then sets APPLIED = 1 where NAME = wallpaper
                // This makes sure only 1 wallpaper can be applied at one time
                sqLiteHelper.applyInventory("'"+wallpaper+"'", "'Wallpapers'");

                // Toast feedback to user to inform them that the accessory has been applied
                Toast.makeText(context,  "The wallpaper has been applied",
                        Toast.LENGTH_SHORT).show();
            } else {
                sqLiteHelper.removeItem("'"+wallpaper+"'");

                // Toast feedback to user to inform them that the wallpaper has been removed
                Toast.makeText(context,  "The wallpaper has been removed",
                        Toast.LENGTH_SHORT).show();
            }
            notifyDataSetChanged();
        }

    }
}
