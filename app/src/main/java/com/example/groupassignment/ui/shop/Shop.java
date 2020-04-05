package com.example.groupassignment.ui.shop;

import java.util.ArrayList;

public class Shop {

    private String itemName;
    private String itemCategory;
    private int itemPrice;
    private int itemQuantity;
    //private int image;


    public Shop(){

    }

    public Shop(String itemName, String itemCategory, int itemPrice, int itemQuantity) {
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public static ArrayList<Shop> getAccessories() {
        ArrayList<Shop> accessories = new ArrayList<>();
        accessories.add(new Shop("Sunglasses", "Accessories", 20 ,0));
        accessories.add(new Shop("Cap", "Accessories", 20,0));
        accessories.add(new Shop("Top hat", "Accessories", 20,0));
        accessories.add(new Shop("Glasses", "Accessories", 20,0));
        accessories.add(new Shop("Pirate Hat", "Accessories", 20,0));
        accessories.add(new Shop("Wig", "Accessories", 20,0));


        return accessories;

    }

    public static ArrayList<Shop> getWallpapers() {
        ArrayList<Shop> wallpapers = new ArrayList<>();
        wallpapers.add(new Shop("Striped", "Wallpapers", 20,0));
        wallpapers.add(new Shop("Polka Dots", "Wallpapers", 20,0));
        wallpapers.add(new Shop("Pink", "Wallpapers", 20,0));
        wallpapers.add(new Shop("Black", "Wallpapers", 20,0));
        wallpapers.add(new Shop("Red", "Wallpapers", 20,0));
        wallpapers.add(new Shop("Green", "Wallpapers", 20,0));



        return wallpapers;

    }

}
