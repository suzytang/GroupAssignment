package com.example.groupassignment;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class TranslateRequest extends AsyncTask<String, Integer, String> {

    String apiKey = "trnsl.1.1.20200406T153746Z.c0ca0cc13fd27e06.701385e6275a5b9d89b1707cac023168fd297934";
    String language = "en-fr";

    public TranslateRequest() {
    }

    @Override
    protected String doInBackground(String... params) {
        String text = params[0];
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
            return resultString;

        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}