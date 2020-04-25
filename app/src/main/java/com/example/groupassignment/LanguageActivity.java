package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import pl.droidsonroids.gif.GifImageView;

import static com.example.groupassignment.Languages.getLanguages;
import static com.example.groupassignment.MainActivity.apiKey;

public class LanguageActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private DatabaseHelper myDb;
    private SQLiteHelper dB;
    private Dialog dialog, languageDialog;
    private ProgressBar progressBar;
    private TextView progressText;
    private ArrayList<Languages> languages;

    //  The following code uses https://github.com/koral--/android-gif-drawable
    private GifImageView gif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);

        // Initialise databases
        myDb = new DatabaseHelper(this);
        dB = new SQLiteHelper(this);

        // Set titles
        this.setTitle("Set Language");

        // Initialise recyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // Initialise layoutManager for recyclerView
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        LanguageAdapter.RecyclerViewClickListener listener = new LanguageAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                clickResponse(position);
            }
        };

        // Get languages from Languages class
        languages = getLanguages();

        // Create adapter object
        adapter = new LanguageAdapter(this, languages, listener);

        // Attach adapter to recycler
        recyclerView.setAdapter(adapter);
    }

    // Set on click response
    public void clickResponse(int position) {
        String lang = languages.get(position).getLanguage();
        if (!myDb.checkCurrent(lang)) {
            myDb.setLanguage(lang);
            adapter.notifyDataSetChanged();
            new TranslateDatabase().execute();
            dB.resetPetData();
            myDb.resetLearnData();
        }
    }

    // The following code is modified from: INFS3634 Week 9 - AsyncTask Simple Example,
    // Oxford Dictionaries API Documentation
    // https://developer.oxforddictionaries.com/documentation#!/Entries/get_entries_source_lang_word_id
    // and Yandex Translate API Domcumentation
    // https://tech.yandex.com/translate/doc/dg/reference/translate-docpage/

    // Translate AsyncTask which calls API and stores into the database
    public class TranslateDatabase extends AsyncTask<String, Integer, Void> {

        // Initialise dialog
        Dialog dialog = new Dialog(LanguageActivity.this);

        public TranslateDatabase() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Set dialog view and link to XML
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
                    String text = myDb.getEnglish(i, j);
                    try {
                        // Create URL and connection
                        URL url = new URL("https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + apiKey
                                + "&text=" + text + "&lang=" + myDb.getCode());
                        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

                        // Read output
                        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        StringBuilder stringBuilder = new StringBuilder();

                        // Get line
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            stringBuilder.append(line + "\n");
                        }

                        // Get String
                        String resultString = stringBuilder.toString().trim();

                        // Get characters between [ and ]
                        resultString = resultString.substring(resultString.indexOf('[') + 1);
                        resultString = resultString.substring(1, resultString.indexOf("]") - 1);

                        Log.d("Translation Result:", resultString);

                        // Format and store translation result
                        String resultFormatted = resultString.replace("'", "''");
                        myDb.setTranslation(resultFormatted, i, j);

                        // Update progress
                        x++;

                        // Publish progress
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
            // Set progress bar based on number of translations
            progressBar.setProgress(values[0]);
            Log.d("TranslateDB", "onProgressUpdate: Update number " + values[0]);

            // Change gifs every 10 translations
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
            // Dismiss dialog and return to main activity
            dialog.dismiss();
            myDb.dbClean();
            Toast.makeText(LanguageActivity.this, "Database loaded", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}