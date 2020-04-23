package com.example.groupassignment.ui.shop;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment.R;
import com.example.groupassignment.SQLiteHelper;

public class AccessoriesFrag extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Dialog dialog;
    SQLiteHelper sqLiteHelper;
    final Shop shop = new Shop();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.shop_list, container, false);
        sqLiteHelper = new SQLiteHelper(getActivity());
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);

        AccessoriesAdapter.RecyclerViewClickListener listener = new AccessoriesAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                clickResponse(position);
            }
        };

        adapter = new AccessoriesAdapter(getActivity(), listener);
        recyclerView.setAdapter(adapter);


        TextView coins = (TextView) root.findViewById(R.id.coins);
        coins.setText(sqLiteHelper.getData(SQLiteHelper.COL_4, 1)+ " coins");

        return root;
    }

    public void clickResponse (int position){
        // Checks that the item hasn't been bought yet to open dialog to buy. Bought items are not clickable
        sqLiteHelper = new SQLiteHelper(getActivity());
        if (!sqLiteHelper.isBought(shop.getAccessories().get(position).getItemName())) {
            openDialog(position);
        }
    }

    public void updateUI(){
        // Updates coins value in UI
        TextView coins = (TextView) getActivity().findViewById(R.id.coins);
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
        coins.setText(sqLiteHelper.getData(SQLiteHelper.COL_4, 1)+ " coins");
    }

    public void openDialog(final int position) {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.shop_popup);
        Button cancelButton = (Button) dialog.findViewById(R.id.cancelButton);
        Button buyButton = (Button) dialog.findViewById(R.id.buyButton);
        ImageView image = (ImageView) dialog.findViewById(R.id.image);
        TextView shopItem = (TextView) dialog.findViewById(R.id.shopItem);
        TextView shopPrice = (TextView) dialog.findViewById(R.id.shopPrice);

        shopItem.setText(shop.getAccessories().get(position).getItemName());
        shopPrice.setText(shop.getAccessories().get(position).getItemPrice() + " coins");
        image.setImageResource(shop.getAccessories().get(position).getImage());

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        buyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String accessory = shop.getAccessories().get(position).getItemName();
                // Checks database for whether user has purchased the accessory before
                int accessoryQty = sqLiteHelper.getItem(SQLiteHelper.COL_4,SQLiteHelper.COL_2,"'"+accessory+"'");
                // if statement checks they have not purchased it before i.e. qty = 0
                if (accessoryQty == 0){
                    // Extracts how many coins the user has from the database
                    int coinsCurrrent = Integer.parseInt(sqLiteHelper.getData(SQLiteHelper.COL_4, 1));
                    // Gets item price
                    int itemPrice = shop.getAccessories().get(position).getItemPrice();

                    // Checks if the user has enough coins to purchase the product
                    if (coinsCurrrent - itemPrice >= 0){
                        // Subtracts itemPrice from user's coins
                        int paid = (coinsCurrrent - itemPrice);
                        // Updates database with new coins value
                        sqLiteHelper.update(1, "Coins", "Coins", paid);
                        // Update amount of accessory to 1
                        int id = sqLiteHelper.getItem(SQLiteHelper.COL_1,SQLiteHelper.COL_2,"'"+accessory+"'");
                        sqLiteHelper.updateData("Amount", 1, id);

                        updateUI();
                        dialog.dismiss();
                        Toast.makeText(getActivity(),
                                shop.getAccessories().get(position).getItemName() + " has been added to your inventory!", Toast.LENGTH_LONG).show();
                        adapter.notifyDataSetChanged();
                    } else{
                        Toast.makeText(getActivity(),
                                "You don't have enough coins to purchase this!", Toast.LENGTH_LONG).show();
                    }
                } else{
                    Toast.makeText(getActivity(),
                            "You already have this item in your inventory", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}
