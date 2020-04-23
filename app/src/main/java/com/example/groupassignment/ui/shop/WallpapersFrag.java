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
import androidx.viewpager.widget.ViewPager;

import com.example.groupassignment.R;
import com.example.groupassignment.SQLiteHelper;

public class WallpapersFrag extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Dialog dialog;


    final Shop shop = new Shop();
    SQLiteHelper sqLiteHelper;
    TextView coins;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.shop_list, container, false);
        sqLiteHelper = new SQLiteHelper(getActivity());
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);

        WallpapersAdapter.RecyclerViewClickListener listener = new WallpapersAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                clickResponse(position);

            }
        };

        adapter = new WallpapersAdapter(getActivity(),listener);
        recyclerView.setAdapter(adapter);

        coins = root.findViewById(R.id.coins);
        coins.setText(sqLiteHelper.getData(SQLiteHelper.COL_4, 1)+ " onCreate");

        return root;

    }

    public void clickResponse (int position){
        sqLiteHelper = new SQLiteHelper(getActivity());
        // Checks that the item hasn't been bought yet to open dialog to buy. Bought items are not clickable
        if (!sqLiteHelper.isBought(shop.getWallpapers().get(position).getItemName())) {
            openDialog(position);
        }
    }

    public void openDialog(final int position){
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.shop_popup);
        Button cancelButton = (Button) dialog.findViewById(R.id.cancelButton);
        Button buyButton = (Button) dialog.findViewById(R.id.buyButton);
        ImageView image = (ImageView) dialog.findViewById(R.id.image);
        TextView shopItem = (TextView) dialog.findViewById(R.id.shopItem);
        TextView shopPrice = (TextView) dialog.findViewById(R.id.shopPrice);

        shopItem.setText(shop.getWallpapers().get(position).getItemName());
        shopPrice.setText(shop.getWallpapers().get(position).getItemPrice() + " coins");
        image.setImageResource(shop.getWallpapers().get(position).getImage());


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
                String wallpaper = shop.getWallpapers().get(position).getItemName();
                // Checks database for whether user has purchased the wallpaper before
                int wallpaperQty = sqLiteHelper.getItem(SQLiteHelper.COL_4, SQLiteHelper.COL_2,"'"+wallpaper+"'");
                // if statement checks they have not purchased it before i.e. qty = 0
                if (wallpaperQty == 0){
                    // Extracts how many coins the user has from the database
                    int coinsCurrrent = Integer.parseInt(sqLiteHelper.getData(SQLiteHelper.COL_4, 1));
                    // Gets item price
                    int itemPrice = shop.getWallpapers().get(position).getItemPrice();

                    // Checks if the user has enough coins to purchase the product
                    if (coinsCurrrent - itemPrice >= 0){
                        // Subtracts itemPrice from user's coins
                        int paid = (coinsCurrrent - itemPrice);
                        // Updates database with new coins value
                        sqLiteHelper.update(1, "Coins", "Coins", paid);
                        // Update amount of wallpaper to 1
                        int id = sqLiteHelper.getItem(SQLiteHelper.COL_1, SQLiteHelper.COL_2, "'"+wallpaper+"'");
                        sqLiteHelper.updateData("Amount", 1, id);

//                        updateUI();
                        coins.setText(sqLiteHelper.getData(SQLiteHelper.COL_4, 1));

                        dialog.dismiss();
                        Toast.makeText(getActivity(),
                                shop.getWallpapers().get(position).getItemName() + " wallpaper has been added to your inventory!", Toast.LENGTH_LONG).show();
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

//    public void updateUI(){
//        // Updates coins value in UI
////        TextView coins = (TextView) getActivity().findViewById(R.id.coins);
////        SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
//        coins.setText(sqLiteHelper.getData(SQLiteHelper.COL_4, 1));
//
//    }


    @Override
    public void onResume(){
        super.onResume();
//        updateUI();
        coins.setText(sqLiteHelper.getData(SQLiteHelper.COL_4, 1));
    }
}
