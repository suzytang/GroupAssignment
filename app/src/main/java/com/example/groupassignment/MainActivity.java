package com.example.groupassignment;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dB;
    private Dialog dialog;
    private ProgressBar progressBar;
    private TextView progressText;
    private GifImageView gif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dB = new DatabaseHelper(this);

        if (!dB.translated()) {

            new TranslateDatabase().execute();
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

    public class TranslateDatabase extends AsyncTask<String, Integer, Void> {

        String apiKey = "trnsl.1.1.20200406T153746Z.c0ca0cc13fd27e06.701385e6275a5b9d89b1707cac023168fd297934";
        String language = "en-fr";
        private static final String TAG = "MyTask";
        Dialog dialog = new Dialog(MainActivity.this);


        public TranslateDatabase() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setContentView(R.layout.load_database);
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setCancelable(false);
            this.dialog.show();
            progressBar = this.dialog.findViewById(R.id.progressBar);
            gif = this.dialog.findViewById(R.id.gifImageView);
            gif.setImageResource(R.drawable.gif1);
            progressBar.setMax(90);
            progressText = this.dialog.findViewById(R.id.progressText);
            progressText.setText(("Database loading..."));
        }

        @Override
        protected Void doInBackground(String... params) {
            int x = 1;
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
//            progressText.setText("Loading "+values[0]+" of 90");
            Log.d(TAG, "onProgressUpdate: Update number " + values[0]);
            switch (values[0]) {
                case 10:
                case 50:
                    gif.setImageResource(R.drawable.gif2);
                    break;
                case 20:
                case 60:
                    gif.setImageResource(R.drawable.gif3);
                    break;
                case 30:
                case 70:
                    gif.setImageResource(R.drawable.gif4);
                    break;
                case 40:
                case 80:
                    gif.setImageResource(R.drawable.gif5);
                    break;
            }
        }

        @Override
        protected void onPostExecute(Void x) {
            super.onPostExecute(x);
            dialog.dismiss();
            Toast.makeText(MainActivity.this, "Database loaded", Toast.LENGTH_SHORT).show();;
        }
    }
}