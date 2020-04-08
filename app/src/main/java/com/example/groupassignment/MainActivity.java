package com.example.groupassignment;

import android.app.FragmentManager;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.groupassignment.ui.learn.LearnFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper dB = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            loadDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setItemIconTintList(null);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_pet, R.id.navigation_learn, R.id.navigation_leaderboard, R.id.navigation_shop)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }

//    public Cursor createDatabase() {
//        DatabaseHelper dB = new DatabaseHelper(this);
//        return dB.getAllData();
//    }

    private void loadDatabase() throws SQLException {
        Database.createPetTable();
        Database.createAccessoriesTable();
        Database.createWallpapersTable();

    }
}
