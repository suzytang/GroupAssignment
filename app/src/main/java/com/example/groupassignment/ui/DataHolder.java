package com.example.groupassignment.ui;

public class DataHolder {
    public Inventory inventory = new Inventory();



    private static final DataHolder holder = new DataHolder();

    public static DataHolder getInstance() {
        return holder;
    }
}
