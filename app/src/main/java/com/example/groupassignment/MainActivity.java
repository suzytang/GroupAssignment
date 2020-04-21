package com.example.groupassignment;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper dB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dB = new DatabaseHelper(this);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setItemIconTintList(null);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_pet, R.id.navigation_learn, R.id.navigation_selflearn, R.id.navigation_shop)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        if (!dB.translated()) {
            translate();
        }
    }
    private void translate() {
        String result = "";
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 11; j++) {
                TranslateRequest tR = new TranslateRequest();
                try {
                    result = tR.execute(dB.getEnglish(i, j)).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String resultFormatted = result.replace("'", "''");
                dB.setTranslation(resultFormatted, i, j);
            }
        }
    }
}