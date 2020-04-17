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
    private FragmentCommunication mCommunicator;

    public InvAccessoriesAdapter(Context context, Cursor cursor, FragmentCommunication mCommunicator) {
        this.context = context;
        this.cursor = cursor;
        this.mCommunicator = mCommunicator;
    }

    @Override
    public InvAccessoriesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_item, parent, false);
        return new InvAccessoriesAdapter.MyViewHolder(v, mCommunicator);
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
        public FragmentCommunication mCommunicator;

        public MyViewHolder(View itemView, FragmentCommunication mCommunicator) {
            super(itemView);

            this.itemName = itemView.findViewById(R.id.itemName);
            this.apply = itemView.findViewById(R.id.apply);
            this.image = itemView.findViewById(R.id.image);
            this.mCommunicator = mCommunicator;

            apply.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            if(!cursor.moveToPosition(getAdapterPosition())){
                return;
            }
            String accessory = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COL_2));
            System.out.println("adapter "+accessory);


            mCommunicator.respond(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COL_2)));
            Toast.makeText(context,  "The accessory has been applied",
                    Toast.LENGTH_LONG).show();
        }

    }
}
