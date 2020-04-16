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

import java.sql.SQLException;

public class AccessoriesList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Dialog dialog;
    SQLiteHelper sqLiteHelper = new SQLiteHelper(this);

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String COINS = "text";




    final Shop shop = new Shop();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        AccessoriesAdapter.RecyclerViewClickListener listener = new AccessoriesAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                try {
                    clickResponse(position);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        };

        adapter = new AccessoriesAdapter(shop.getAccessories(), listener);
        recyclerView.setAdapter(adapter);

        //TextView coins = (TextView) findViewById(R.id.coins);
        /*try {Integer.parseInt(
            dbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Cursor cursor = dbManager.fetch();
        cursor.moveToFirst();
        coins.setText(Integer.toString(cursor.getInt(3))+ " coins");*/


        TextView coins = (TextView) findViewById(R.id.coins);

        coins.setText(sqLiteHelper.getData(SQLiteHelper.COL_4, 1)+ " coins");


    }
    public void clickResponse (final int position) throws SQLException {

        final Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.shop_popup);
        Button cancelButton = (Button) dialog.findViewById(R.id.cancelButton);
        Button buyButton = (Button) dialog.findViewById(R.id.buyButton);
        TextView shopItem = (TextView) dialog.findViewById(R.id.shopItem);
        TextView shopPrice = (TextView) dialog.findViewById(R.id.shopPrice);

        final TextView coins = (TextView) findViewById(R.id.coins);

        shopItem.setText(shop.getAccessories().get(position).getItemName());
        shopPrice.setText(shop.getAccessories().get(position).getItemPrice() + " coins");

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        buyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String accessory = shop.getAccessories().get(position).getItemName();
                int accessoryQty = sqLiteHelper.getItem(SQLiteHelper.COL_4,SQLiteHelper.COL_2,"'"+accessory+"'");
                if (accessoryQty == 0){
                    int coinsCurrrent = Integer.parseInt(sqLiteHelper.getData(SQLiteHelper.COL_4, 1));
                    int itemPrice = shop.getAccessories().get(position).getItemPrice();

                    if (coinsCurrrent - itemPrice >= 0){
                        int paid = (coinsCurrrent - itemPrice);
                        sqLiteHelper.update(1, "Coins", "Coins", paid);
                        int id = sqLiteHelper.getItem(SQLiteHelper.COL_1,SQLiteHelper.COL_2,"'"+accessory+"'");
                        sqLiteHelper.updateData("Amount", 1, id);

                        dialog.dismiss();
                        coins.setText(sqLiteHelper.getData(SQLiteHelper.COL_4, 1)+ " coins");
                        Toast.makeText(AccessoriesList.this,
                                shop.getAccessories().get(position).getItemName() + " has been added to your inventory!", Toast.LENGTH_LONG).show();

                    } else{
                        Toast.makeText(AccessoriesList.this,
                                "You don't have enough coins to purchase this!", Toast.LENGTH_LONG).show();
                    }
                } else{
                    Toast.makeText(AccessoriesList.this,
                            "You already have this item in your inventory", Toast.LENGTH_LONG).show();
                }

            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }

}