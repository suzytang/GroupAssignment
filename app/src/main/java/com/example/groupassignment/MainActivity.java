package com.example.groupassignment;

import android.app.FragmentManager;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteTableLockedException;
import android.os.Bundle;
import android.widget.TextView;

import com.example.groupassignment.ui.learn.LearnFragment;
import com.example.groupassignment.ui.shop.Shop;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.sql.SQLException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper dB;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dB = new DatabaseHelper(this);
        dB.updateData("learn_table","Translation","S''il vous plaît",6);


        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setItemIconTintList(null);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_pet, R.id.navigation_learn, R.id.navigation_leaderboard, R.id.navigation_shop)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        sqLiteHelper.update(1, "coins", "coins", 100);

        try{
            final Shop shop = new Shop();
            ArrayList<Shop> accessories = new ArrayList<>();
            for (int i = 0; i < accessories.size(); i++){
                String name = shop.getAccessories().get(i).getItemName();
                sqLiteHelper.insert("'"+name+"'", "Accessories", 0);
                System.out.println("insert success");
            }
            ArrayList<Shop> wallpapers = new ArrayList<>();
            for (int i = 0; i < wallpapers.size(); i++){
                String name = shop.getWallpapers().get(i).getItemName();
                sqLiteHelper.insert("'"+name+"'", "Wallpapers", 0);
                System.out.println("insert success");
            }

        }catch(Exception e){
            System.out.println("insert failed");
        }

        //System.out.println(sqLiteHelper.getTableAsString(sqLiteDatabase, "inventory_table"));

    }

//    public Cursor createDatabase() {
//        DatabaseHelper dB = new DatabaseHelper(this);
//        return dB.getAllData();
//    }

}
