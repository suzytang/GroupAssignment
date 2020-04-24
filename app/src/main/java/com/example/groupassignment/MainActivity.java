package com.example.groupassignment;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.groupassignment.ui.learn.QuizResults;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    public static final String apiKey = "trnsl.1.1.20200406T153746Z.c0ca0cc13fd27e06.701385e6275a5b9d89b1707cac023168fd297934";
    private DatabaseHelper myDb;
    private Dialog dialog, languageDialog;
    private ProgressBar progressBar;
    private TextView progressText;
    private GifImageView gif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        // Start language selection for first launch
        if (!myDb.translated()) {
            Intent intent = new Intent(this, LanguageActivity.class);
            startActivity(intent);
        }
        myDb.dbClean();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setItemIconTintList(null);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_pet, R.id.navigation_learn, R.id.navigation_selflearn, R.id.navigation_shop)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        sqLiteHelper.updateData(SQLiteHelper.COL_4, 10000, 1);
    }
}