package com.example.groupassignment.ui.pet;

import android.content.Context;
import android.database.Cursor;
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
        final String accessory = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COL_2));


        holder.itemName.setText(accessory);
        holder.image.setImageResource(shop.searchAccessories(accessory).getImage());

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
            String accessory = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COL_2));
            SQLiteHelper sqLiteHelper = new SQLiteHelper(context);

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

            //sqLiteHelper.applyInventory("'"+accessory+"'", "'Accessories'");

            System.out.println("adapter "+accessory);

            Toast.makeText(context,  "The accessory has been applied",
                    Toast.LENGTH_LONG).show();
        }

    }
}
