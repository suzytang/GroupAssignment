package com.example.groupassignment.ui.shop;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment.R;
import com.example.groupassignment.SQLiteHelper;

import java.util.ArrayList;

public class WallpapersList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Dialog dialog;


    final Shop shop = new Shop();
    ArrayList<Shop> wallpapers;
    SQLiteHelper sqLiteHelper = new SQLiteHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_list);

        wallpapers = new ArrayList<Shop>();


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        WallpapersAdapter.RecyclerViewClickListener listener = new WallpapersAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                clickResponse(position);

            }
        };

        adapter = new WallpapersAdapter(shop.getWallpapers(), listener);
        recyclerView.setAdapter(adapter);

        TextView coins = (TextView) findViewById(R.id.coins);


        SQLiteHelper sqLiteHelper = new SQLiteHelper(WallpapersList.this);

        coins.setText(sqLiteHelper.getData(SQLiteHelper.COL_4, 1)+ " coins");

    }
    public void clickResponse (final int position){

        final Dialog dialog = new Dialog(this);


        dialog.setContentView(R.layout.shop_popup);
        Button cancelButton = (Button) dialog.findViewById(R.id.cancelButton);
        Button buyButton = (Button) dialog.findViewById(R.id.buyButton);
        TextView shopItem = (TextView) dialog.findViewById(R.id.shopItem);
        TextView shopPrice = (TextView) dialog.findViewById(R.id.shopPrice);

        shopItem.setText(shop.getWallpapers().get(position).getItemName());
        shopPrice.setText(shop.getWallpapers().get(position).getItemPrice() + " coins");

        final TextView coins = (TextView) findViewById(R.id.coins);
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
                int wallpaperQty = sqLiteHelper.getItem(SQLiteHelper.COL_4, SQLiteHelper.COL_2,"'"+wallpaper+"'");
                if (wallpaperQty == 0){
                    int coinsCurrrent = Integer.parseInt(sqLiteHelper.getData(SQLiteHelper.COL_4, 1));
                    int itemPrice = shop.getWallpapers().get(position).getItemPrice();

                    if (coinsCurrrent - itemPrice >= 0){
                        int paid = (coinsCurrrent - itemPrice);
                        sqLiteHelper.update(1, "Coins", "Coins", paid);
                        int id = sqLiteHelper.getItem(SQLiteHelper.COL_1, SQLiteHelper.COL_2, "'"+wallpaper+"'");
                        sqLiteHelper.updateData("Amount", 1, id);

                        dialog.dismiss();
                        coins.setText(sqLiteHelper.getData(SQLiteHelper.COL_4, 1)+ " coins");
                        Toast.makeText(WallpapersList.this,
                                shop.getWallpapers().get(position).getItemName() + " wallpaper has been added to your inventory!", Toast.LENGTH_LONG).show();

                    } else{
                        Toast.makeText(WallpapersList.this,
                                "You don't have enough coins to purchase this!", Toast.LENGTH_LONG).show();
                    }
                } else{
                    Toast.makeText(WallpapersList.this,
                            "You already have this item in your inventory", Toast.LENGTH_LONG).show();
                }

            }
        });



    }



}
