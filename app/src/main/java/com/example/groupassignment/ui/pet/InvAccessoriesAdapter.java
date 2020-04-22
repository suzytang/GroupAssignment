package com.example.groupassignment.ui.pet;

import android.content.Context;
import android.database.Cursor;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment.R;
import com.example.groupassignment.SQLiteHelper;
import com.example.groupassignment.ui.shop.Shop;

public class InvAccessoriesAdapter extends RecyclerView.Adapter<InvAccessoriesAdapter.MyViewHolder> {

    Shop shop = new Shop();
    private Context context;
    private Cursor cursor;

    // The following code is modified from: Coding in Flow (2017)
    // 'SQLite + RecyclerView - Part 2 - CURSOR AND RECYCLERVIEW ADAPTER - Android Studio Tutorial'
    // https://www.youtube.com/watch?v=_m-Ve-BAYe0&t=
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
    public void onBindViewHolder(InvAccessoriesAdapter.MyViewHolder holder, final int position) {
        if(!cursor.moveToPosition(position)){
            return;
        }
        // Cursor gets the name of the accessory
        String accessory = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COL_2));

        // Makes textview saying 'Applied' visible if accessory is applied and decreases opacity of recyclerview list item
        SQLiteHelper sqLiteHelper = new SQLiteHelper(context);
        if (sqLiteHelper.isApplied(accessory)) {
            holder.itemName.setAlpha((float) 0.1);
            holder.image.setAlpha((float) 0.1);
            holder.apply.setText("Remove");
            holder.applied.setVisibility(View.VISIBLE);
        }

        // Set text and image for accessory in recyclerview
        holder.itemName.setText(accessory);
        holder.image.setImageResource(shop.searchAccessories(accessory).getImage());

    }

    // Returns the number of rows in the database based on getAllItems in AccessoriesFragment
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

            if(!cursor.moveToPosition(getAdapterPosition())) {
                return;
            }
            SQLiteHelper sqLiteHelper = new SQLiteHelper(context);

            // Get NAME based on the item selected
            String accessory = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COL_2));

            if(apply.getText().equals("Apply")){

                // Switch statement to apply based on grouping of accessories by SUBCATEGORY
                // User can only apply 1 of each of the following subcategories at a time: Glasses, Wig, Hat
                // If user attempts to apply another item of the same SUBCATEGORY, current item is removed and replaced
                switch(accessory){
                    case "Glasses":
                    case "Sunglasses":
                        sqLiteHelper.applyAccessories("'"+accessory+"'","'Glasses'");
                        break;
                    case "Wig":
                        sqLiteHelper.applyAccessories("'"+accessory+"'","'Wig'");
                        break;
                    case "Pirate Hat":
                    case "Cap":
                    case "Top Hat":
                        sqLiteHelper.applyAccessories("'"+accessory+"'","'Hat'");
                        break;
                }

                // Makes textview saying 'Applied' visible if accessory is applied and decreases opacity of recyclerview list item
                if (sqLiteHelper.isApplied(accessory)) {
                    itemName.setAlpha((float) 0.1);
                    image.setAlpha((float) 0.1);
                    apply.setText("Remove");
                    applied.setVisibility(View.VISIBLE);
                }

                // Toast feedback to user to inform them that the accessory has been applied
                Toast.makeText(context,  "The accessory has been applied",
                        Toast.LENGTH_LONG).show();
            } else {

                sqLiteHelper.removeItem("'"+accessory+"'");

                // Reverts to original view upon pressing Remove
                if (sqLiteHelper.isApplied(accessory) == false) {
                    itemName.setAlpha((float) 1);
                    image.setAlpha((float) 1);
                    apply.setText("Apply");
                    applied.setVisibility(View.INVISIBLE);
                }
                // Toast feedback to user to inform them that the accessory has been removed
                Toast.makeText(context,  "The accessory has been removed",
                        Toast.LENGTH_LONG).show();
            }
        }

    }
}
