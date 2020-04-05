package com.example.groupassignment.ui;

import com.example.groupassignment.R;

import java.util.ArrayList;

public class Inventory {
    private String name;
    private int image;
    private int coins = 100;
    private int foodQty = 0;


    public Inventory(){

    }

    public Inventory(String name, int image){
        this.name = name;
        this.image = image;
    }

    public Inventory(int coins, int foodQty){
        this.coins = coins;
        this.foodQty = foodQty;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getFoodQty() {
        return foodQty;
    }

    public void setFoodQty(int foodQty) {
        this.foodQty = foodQty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public static ArrayList<Inventory> getAccessoriesInventory() {
        ArrayList<Inventory> accessoriesInventory = new ArrayList<>();
        accessoriesInventory.add(new Inventory("Glasses", R.drawable.carnival));

        return accessoriesInventory;

    }

    public static ArrayList<Inventory> getWallpapersInventory() {
        ArrayList<Inventory> wallpapersInventory = new ArrayList<>();
        wallpapersInventory.add(new Inventory("Pink", R.drawable.wallpaper));

        return wallpapersInventory;

    }





}
