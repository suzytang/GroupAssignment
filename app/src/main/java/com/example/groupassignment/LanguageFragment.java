package com.example.groupassignment;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import pl.droidsonroids.gif.GifImageView;

import static com.example.groupassignment.MainActivity.apiKey;

public class LanguageFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private DatabaseHelper myDb;
    private Dialog dialog, languageDialog;
    private ProgressBar progressBar;
    private TextView progressText;
    private GifImageView gif;
    private ArrayList<Languages> languages;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate view with language fragment
        View root = inflater.inflate(R.layout.fragment_language, container, false);

        // Initialise recyclerView
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // Initialise layoutManager for recyclerView
        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);

        LanguageAdapter.RecyclerViewClickListener listener = new LanguageAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                clickResponse(position);
            }
        };

        // Get languages from Languages class
        languages = Languages.getLanguages();

        // Create adapter object
        adapter = new LanguageAdapter(this.getContext(), languages, listener);

        // Attach adapter to recycler
        recyclerView.setAdapter(adapter);

        return root;
    }

    public void clickResponse (int position){
        changeLanguage(languages.get(position).getLanguage());
    }

    public void changeLanguage(String lang) {
        if (!myDb.checkCurrent(lang)) {
            myDb.setLanguage(lang);
            new TranslateDatabase().execute();
        } else {
            Toast.makeText(getActivity(), "Current language is already set to"+lang, Toast.LENGTH_LONG);
        }
    }

    public class TranslateDatabase extends AsyncTask<String, Integer, Void> {

        private static final String TAG = "MyTask";
        Dialog dialog = new Dialog(getActivity());


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
                    String text = myDb.getEnglish(i, j);
                    try {
                        URL url = new URL("https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + apiKey
                                + "&text=" + text + "&lang=" + myDb.getCode());
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
                        myDb.setTranslation(resultFormatted, i, j);
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
            Toast.makeText(getActivity(), "Database loaded", Toast.LENGTH_SHORT).show();;
        }
    }
}