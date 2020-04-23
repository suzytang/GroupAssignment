package com.example.groupassignment;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.groupassignment.ui.learn.QuizTest;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper dB;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dB = new DatabaseHelper(this);
//        String result = "";
//        for (int i = 1; i < 10; i++) {
//            for (int j = 1; j < 11; j++) {
//                TranslateRequest tR = new TranslateRequest();
//                try {
//                    result = tR.execute(dB.getEnglish(i, j)).get();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                String resultFormatted = result.replace("'", "''");
//                dB.setTranslation(resultFormatted, i, j);
//            }
//        }

        if (!dB.translated()) {
            new TranslateRequestDB().execute();
            loadDatabase();
        }


        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setItemIconTintList(null);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_pet, R.id.navigation_learn, R.id.navigation_selflearn, R.id.navigation_shop)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }
    public class TranslateRequestDB extends AsyncTask<String, Integer, Void> {

        public TranslateRequestDB() {
        }

        @Override
        protected Void doInBackground(String... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Void x) {
            dB = new DatabaseHelper(getApplicationContext());
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
            dB.setTranslation("S''il vous plaît",1,6);
            super.onPostExecute(x);
        }
    }

    private void loadDatabase() {
        // Create dialog
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.load_database);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
        Runnable dismissRunner = new Runnable() {
            public void run() {
                if (dialog != null)
                    dialog.dismiss();
            }
        };
        new Handler().postDelayed(dismissRunner, 10000);
    }
    
}