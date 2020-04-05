package com.example.groupassignment.ui.shop;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment.R;
import com.example.groupassignment.ui.DataHolder;
import com.example.groupassignment.ui.Inventory;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AccessoriesList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Dialog dialog;



    final Shop shop = new Shop();
    //final Inventory inventory = new Inventory();
    //ArrayList<Shop> accessories;
    //ArrayList <Inventory> accessoriesInventory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_list);

        //accessories = new ArrayList<Shop>();



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        AccessoriesAdapter.RecyclerViewClickListener listener = new AccessoriesAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                clickResponse(position);

            }
        };

        adapter = new AccessoriesAdapter(shop.getAccessories(), listener);
        recyclerView.setAdapter(adapter);

    }
    public void clickResponse (final int position){

        final Dialog dialog = new Dialog(this);



        dialog.setContentView(R.layout.shop_popup);
        Button cancelButton = (Button) dialog.findViewById(R.id.cancelButton);
        Button buyButton = (Button) dialog.findViewById(R.id.buyButton);
        TextView shopItem = (TextView) dialog.findViewById(R.id.shopItem);
        TextView shopPrice = (TextView) dialog.findViewById(R.id.shopPrice);

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


                /*if ((DataHolder.getInstance().inventory.getCoins()) - (shop.getAccessories().get(position).getItemPrice()) > 0){
                    int paid = (DataHolder.getInstance().inventory.getCoins()) - (shop.getAccessories().get(position).getItemPrice());
                    DataHolder.getInstance().inventory.setCoins(paid);


                    dialog.dismiss();
                    Toast.makeText(AccessoriesList.this,
                            shop.getAccessories().get(position).getItemName() + " has been added to your inventory!", Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(AccessoriesList.this,
                            "You don't have enough coins to purchase this!", Toast.LENGTH_LONG).show();
                }*/


                try {
                    Connection conn = DriverManager.getConnection("jdbc:sqlite:infs3634.db");
                    Statement st = conn.createStatement();

                    String selectQuery = "SELECT coins FROM Pet";

                    ResultSet rs = st.executeQuery(selectQuery);

                    int coins = rs.getInt(1);

                    if(coins - (shop.getAccessories().get(position).getItemPrice()) > 0){
                        int paid = coins - (shop.getAccessories().get(position).getItemPrice());
                        String updateQuery = "UPDATE Pet SET coins = ?";
                        PreparedStatement ps = conn.prepareStatement(updateQuery);
                        ps.setInt(1,paid);

                        dialog.dismiss();
                        Toast.makeText(AccessoriesList.this,
                                shop.getAccessories().get(position).getItemName() + " has been added to your inventory!", Toast.LENGTH_LONG).show();
                    } else{
                        Toast.makeText(AccessoriesList.this,
                                "You don't have enough coins to purchase this!", Toast.LENGTH_LONG).show();
                    }


                } catch(Exception e) {
                    System.out.println("Could not get coins");
                }




            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }



}
