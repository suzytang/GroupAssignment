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

public class FoodFrag extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    private Dialog dialog;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_food, container, false);

        TextView coins = (TextView) root.findViewById(R.id.coins);
        Button buyFood = (Button) root.findViewById(R.id.buyFood);

        SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
        coins.setText(sqLiteHelper.getData(SQLiteHelper.COL_4, 1)+ " coins");

        buyFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
                dialog = new Dialog(getActivity());

                dialog.setContentView(R.layout.shop_popup);

                TextView itemName = (TextView) dialog.findViewById(R.id.shopItem);
                TextView itemPrice = (TextView) dialog.findViewById(R.id.shopPrice);
                ImageView image = (ImageView) dialog.findViewById(R.id.image);

                itemName.setText("Food");
                itemPrice.setText("20 coins");
                image.setImageResource(R.drawable.food_full);


                Button cancelButton = (Button) dialog.findViewById(R.id.cancelButton);
                Button buyButton = (Button) dialog.findViewById(R.id.buyButton);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                buyButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        int coinsCurrrent = Integer.parseInt(sqLiteHelper.getData(SQLiteHelper.COL_4, 1));
                        int itemPrice = 20;

                        if (coinsCurrrent - itemPrice >= 0){
                            int paid = (coinsCurrrent - itemPrice);
                            sqLiteHelper.update(1, "'Coins'", "'Coins'", paid);
                            int foodQty = Integer.parseInt(sqLiteHelper.getData(SQLiteHelper.COL_4,2));
                            sqLiteHelper.update(2, "'Food'", "'Food'", foodQty + 1);

                            updateUI();
                            dialog.dismiss();
                            Toast.makeText(getContext(), "Food has been added to your inventory!", Toast.LENGTH_LONG).show();

                        } else{
                            Toast.makeText(getContext(),"You don't have enough coins to purchase this!", Toast.LENGTH_LONG).show();
                        }

                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });


        return root;

    }

    public void updateUI(){
        TextView coins = (TextView) getActivity().findViewById(R.id.coins);
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
        coins.setText(sqLiteHelper.getData(SQLiteHelper.COL_4, 1));
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }
}
