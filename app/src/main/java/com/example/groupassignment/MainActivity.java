package com.example.groupassignment;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
    private DatabaseHelper dB;
    private Dialog dialog;
    private ProgressBar progressBar;
    private TextView textView2;
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
//            loadDatabase();
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
        private static final String TAG = "MyTask";
        Dialog dialog = new Dialog(MainActivity.this);

        public TranslateRequestDB() {
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setContentView(R.layout.load_database);
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setCancelable(false);
            this.dialog.show();
//            progressBar = this.dialog.findViewById(R.id.progressBar);
//            progressBar.setMax(100);
            textView2 = this.dialog.findViewById(R.id.textView2);
            textView2.setText(String.valueOf("works"));
        }

        @Override
        protected Void doInBackground(String... params) {
            textView2.setText(String.valueOf("works"));
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
            TextView textView2 = this.dialog.findViewById(R.id.textView2);
            textView2.setText(String.valueOf("Database Loaded"));
            dB.setTranslation("S''il vous plaît",1,6);
            dialog.dismiss();
            super.onPostExecute(x);
        }
    }

//    private void loadDatabase() {
//        // Create dialog
//        dialog = new Dialog(this);
//        dialog.setContentView(R.layout.load_database);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setCancelable(false);
//        dialog.show();
//        progressBar = dialog.findViewById(R.id.progressBar);
//        Runnable dismissRunner = new Runnable() {
//            public void run() {
//                if (dialog != null)
//                    dialog.dismiss();
//            }
//        };
//        new Handler().postDelayed(dismissRunner, 10000);
//    }
//
//    public class MyTask extends AsyncTask<Void,Integer,Integer> {
//        private static final String TAG = "MyTask";
//        Dialog dialog = new Dialog(MainActivity.this);
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            this.dialog.setContentView(R.layout.load_database);
//            this.dialog.setCanceledOnTouchOutside(false);
//            this.dialog.setCancelable(false);
//            this.dialog.show();
//            progressBar = this.dialog.findViewById(R.id.progressBar);
//            progressBar.setMax(100);
//        }
//
//        @Override
//        protected Integer doInBackground(Void... voids) {
//            //Publish progress at every iteration, and then wait for 100 milliseconds
//            int i = 0;
//            for(int x = 0; x<100;x++) {
//                publishProgress(x);
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                i++;
//            }
//            //i now
//            return i;
//        }
//
//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
//
//            progressBar.setProgress(values[0]);
//            Log.d(TAG, "onProgressUpdate: Update number " + values[0]);
//        }
//
//        @Override
//        protected void onPostExecute(Integer integer) {
//            //Integer is i from doInBackground
//            Log.d(TAG, "onPostExecute: INTEGER IS  " + integer);
//            super.onPostExecute(integer);
//            this.dialog.dismiss();
//        }
//    }
}