package com.example.groupassignment.ui.pet;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.groupassignment.R;
import com.example.groupassignment.ui.Inventory;

import java.util.ArrayList;

public class PetInventory extends AppCompatActivity {

    final Inventory inventory = new Inventory();
    ArrayList<Inventory> accessoriesInventory;
    ArrayList<Inventory> wallpapersInventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_inventory);


        TextView coins = (TextView) findViewById(R.id.coins);
        TextView wallpapers = (TextView) findViewById(R.id.wallpapers);
        TextView accessories = (TextView) findViewById(R.id.accessories);

        coins.setText(inventory.getCoins() + " coins");

        accessoriesInventory = inventory.getAccessoriesInventory();
        wallpapersInventory = inventory.getWallpapersInventory();

        for(int i = 0; i < inventory.getAccessoriesInventory().size(); i++){
            accessories.setText(inventory.getAccessoriesInventory().get(i).getName() + ", ");

        }

        for(int i = 0; i < inventory.getAccessoriesInventory().size(); i++){
            wallpapers.setText(inventory.getWallpapersInventory().get(i).getName() + ", ");

        }


    }
}
