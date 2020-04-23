package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class Splash extends AppCompatActivity {
    private DatabaseHelper dB;
    ProgressBar progressBar;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        dB = new DatabaseHelper(this);
        progressBar = findViewById(R.id.progressBar);
        //Added progress bar in XML

        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        new MyTask().execute();
    }

    public class MyTask extends AsyncTask<Void,Integer,Integer> {
        private static final String TAG = "MyTask";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar.setMax(100);
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            //Publish progress at every iteration, and then wait for 100 milliseconds
            int i = 0;
            for(int x = 0; x<100;x++) {
                publishProgress(x);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
            //i now
            return i;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            progressBar.setProgress(values[0]);
            Log.d(TAG, "onProgressUpdate: Update number " + values[0]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            //Integer is i from doInBackground
            Log.d(TAG, "onPostExecute: INTEGER IS  " + integer);
            super.onPostExecute(integer);
        }


    }
}
