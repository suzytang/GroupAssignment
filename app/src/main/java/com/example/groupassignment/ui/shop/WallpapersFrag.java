package com.example.groupassignment.ui.shop;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment.R;
import com.example.groupassignment.SQLiteHelper;

import java.util.ArrayList;

public class WallpapersFrag extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Dialog dialog;


    final Shop shop = new Shop();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.shop_list, container, false);
        final SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());

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

        adapter = new WallpapersAdapter(listener);
        recyclerView.setAdapter(adapter);

        TextView coins = (TextView) root.findViewById(R.id.coins);
        coins.setText(sqLiteHelper.getData(SQLiteHelper.COL_4, 1)+ " coins");

        return root;

    }

    public void clickResponse (final int position){

        final SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
        final Dialog dialog = new Dialog(getActivity());


        dialog.setContentView(R.layout.shop_popup);
        Button cancelButton = (Button) dialog.findViewById(R.id.cancelButton);
        Button buyButton = (Button) dialog.findViewById(R.id.buyButton);
        TextView shopItem = (TextView) dialog.findViewById(R.id.shopItem);
        TextView shopPrice = (TextView) dialog.findViewById(R.id.shopPrice);

        shopItem.setText(shop.getWallpapers().get(position).getItemName());
        shopPrice.setText(shop.getWallpapers().get(position).getItemPrice() + " coins");


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
                        onViewCreated(v,null);
                        Toast.makeText(getActivity(),
                                shop.getWallpapers().get(position).getItemName() + " wallpaper has been added to your inventory!", Toast.LENGTH_LONG).show();

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
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        TextView coins = (TextView) getActivity().findViewById(R.id.coins);
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
        coins.setText(sqLiteHelper.getData(SQLiteHelper.COL_4, 1)+ " coins");
    }


}
