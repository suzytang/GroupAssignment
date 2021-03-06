package com.example.groupassignment.ui.shop;

import com.example.groupassignment.R;

import java.util.ArrayList;

public class Shop {

    private String itemName;
    private String itemCategory;
    private int itemPrice;
    private int itemQuantity;
    private int image;


    public Shop(){

    }

    public Shop(String itemName, String itemCategory, int itemPrice, int itemQuantity, int image) {
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.image = image;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public static ArrayList<Shop> getAccessories() {
        ArrayList<Shop> accessories = new ArrayList<>();
        accessories.add(new Shop("Sunglasses", "Accessories", 50 ,0, R.drawable.sunglasses));
        accessories.add(new Shop("Cap", "Accessories", 100,0, R.drawable.cap));
        accessories.add(new Shop("Top Hat", "Accessories", 100,0, R.drawable.top_hat));
        accessories.add(new Shop("Glasses", "Accessories", 50,0, R.drawable.glasses));
        accessories.add(new Shop("Pirate Hat", "Accessories", 100,0, R.drawable.pirate_hat));
        accessories.add(new Shop("Wig", "Accessories", 100,0, R.drawable.wig));


        return accessories;

    }

    public static ArrayList<Shop> getWallpapers() {
        ArrayList<Shop> wallpapers = new ArrayList<>();
        wallpapers.add(new Shop("Striped", "Wallpapers", 150,0, R.drawable.striped_wallpaper));
        wallpapers.add(new Shop("Polka Dots", "Wallpapers", 150,0, R.drawable.polkadot_wallpaper));
        wallpapers.add(new Shop("Pink", "Wallpapers", 100,0, R.drawable.pink_wallpaper));
        wallpapers.add(new Shop("Black", "Wallpapers", 100,0, R.drawable.black_wallpaper));
        wallpapers.add(new Shop("Red", "Wallpapers", 100,0, R.drawable.red_wallpaper));
        wallpapers.add(new Shop("Green", "Wallpapers", 100,0, R.drawable.green_wallpaper));



        return wallpapers;

    }

    public static Shop searchWallpapers(String search) {
        ArrayList<Shop> wallpapers = Shop.getWallpapers();
        int i;
        for (i = 0; i <  wallpapers.size(); i++){
            if (getWallpapers().get(i).itemName.contains(search)) {
                return wallpapers.get(i);
            }
        }
        return null;
    }

    public static Shop searchAccessories(String search) {
        ArrayList<Shop> accessories = Shop.getAccessories();
        int i;
        for (i = 0; i <  accessories.size(); i++){
            if (getAccessories().get(i).itemName.contains(search)) {
                return accessories.get(i);
            }
        }
        return null;
    }

}
