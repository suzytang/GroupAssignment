package com.example.groupassignment;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dB;
    private Dialog dialog;
    private ProgressBar progressBar;
    private TextView progressText;
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
            new TranslateRequest1().execute();
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
//    public class TranslateRequestDB extends AsyncTask<String, Integer, Void> {
//        private static final String TAG = "MyTask";
//        Dialog dialog = new Dialog(MainActivity.this);
//
//        public TranslateRequestDB() {
//        }
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            this.dialog.setContentView(R.layout.load_database);
//            this.dialog.setCanceledOnTouchOutside(false);
//            this.dialog.setCancelable(false);
//            this.dialog.show();
////            progressBar = this.dialog.findViewById(R.id.progressBar);
////            progressBar.setMax(100);
//            textView2 = this.dialog.findViewById(R.id.textView2);
//            textView2.setText(String.valueOf("works"));
//        }
//
//        @Override
//        protected Void doInBackground(String... params) {
//            textView2.setText(String.valueOf("works"));
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void x) {
//            dB = new DatabaseHelper(getApplicationContext());
//            String result = "";
//            for (int i = 1; i < 10; i++) {
//                for (int j = 1; j < 11; j++) {
//                    TranslateRequest tR = new TranslateRequest();
//                    try {
//                        result = tR.execute(dB.getEnglish(i, j)).get();
//                    } catch (ExecutionException e) {
//                        e.printStackTrace();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    String resultFormatted = result.replace("'", "''");
//                    dB.setTranslation(resultFormatted, i, j);
//                }
//            }
//            TextView textView2 = this.dialog.findViewById(R.id.textView2);
//            textView2.setText(String.valueOf("Database Loaded"));
//            dB.setTranslation("S''il vous plaît",1,6);
//            dialog.dismiss();
//            super.onPostExecute(x);
//        }
//    }

    public class TranslateRequest1 extends AsyncTask<String, Integer, Void> {

        String apiKey = "trnsl.1.1.20200406T153746Z.c0ca0cc13fd27e06.701385e6275a5b9d89b1707cac023168fd297934";
        String language = "en-fr";
        private static final String TAG = "MyTask";
        Dialog dialog = new Dialog(MainActivity.this);

        public TranslateRequest1() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setContentView(R.layout.load_database);
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setCancelable(false);
            this.dialog.show();
            progressBar = this.dialog.findViewById(R.id.progressBar);
            progressBar.setMax(90);
            progressText = this.dialog.findViewById(R.id.progressText);
            progressText.setText(("Database loading..."));
        }

        @Override
        protected Void doInBackground(String... params) {
            int x = 0;
            for (int i = 1; i < 10; i++) {
                for (int j = 1; j < 11; j++) {
                    String text = dB.getEnglish(i, j);
                    try {
                        URL url = new URL("https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + apiKey
                                + "&text=" + text + "&lang=" + language);
                        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

                        // read the output from the server
                        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        StringBuilder stringBuilder = new StringBuilder();

                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            stringBuilder.append(line + "\n");
                        }

                        //Making result human readable
                        String resultString = stringBuilder.toString().trim();

                        //Getting the characters between [ and ]
                        resultString = resultString.substring(resultString.indexOf('[') + 1);
                        resultString = resultString.substring(1, resultString.indexOf("]") - 1);

                        Log.d("Translation Result:", resultString);

                        String resultFormatted = resultString.replace("'", "''");
                        dB.setTranslation(resultFormatted, i, j);
                        x++;
                        publishProgress(x);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
            progressText.setText("Loaded "+values[0]+" of 90");
            Log.d(TAG, "onProgressUpdate: Update number " + values[0]);
        }

        @Override
        protected void onPostExecute(Void x) {
            super.onPostExecute(x);
            dialog.dismiss();
        }
    }
}