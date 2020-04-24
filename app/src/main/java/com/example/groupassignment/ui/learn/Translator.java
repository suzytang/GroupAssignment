package com.example.groupassignment.ui.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.groupassignment.DatabaseHelper;
import com.example.groupassignment.MainActivity_Self_Learn;
import com.example.groupassignment.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

import static com.example.groupassignment.MainActivity.apiKey;
import static com.example.groupassignment.Languages.getLanguages;

public class Translator extends AppCompatActivity {

    // Declare variables
    private TextToSpeech translatedTTS, englishTTS;
    private ImageButton translatedSpeech, englishSpeech, clearText;
    private Button translate, store;
    private TextView translatedText, yandex;
    private EditText enterWord;
    private Context context;
    private DatabaseHelper myDb = new DatabaseHelper(this);
    private String userInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translator);

        this.setTitle("Translator");

        // Initialise variable
        context = this;

        // Link to XML
        translatedText = findViewById(R.id.translatedText);
        enterWord = findViewById(R.id.enterWord);
        translatedSpeech = findViewById(R.id.translatedSpeech);
        translatedSpeech.setVisibility(View.INVISIBLE);
        englishSpeech = findViewById(R.id.englishSpeech);
        translate = findViewById(R.id.translate);
        store = findViewById(R.id.storeButton);
        clearText = findViewById(R.id.clearText);
        yandex = findViewById(R.id.yandexCredit);
        yandex.setMovementMethod(LinkMovementMethod.getInstance());

        // Disable store on create
        enableStore(false);

        // Clear text on click
        clearText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearInput();
            }
        });

        // Attempt to translate input on click
        translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInput = enterWord.getText().toString();
                // Check if user input is empty
                if (userInput.equals("")) {
                    // Set text error, disable store and make translated TTS button invisible
                    translatedText.setText("Error: please enter an expression first");
                    translatedSpeech.setVisibility(View.INVISIBLE);
                    enableStore(false);
                } else {
                    String result = null;
                    TranslateRequest tR = new TranslateRequest();
                    try {
                        result = tR.execute(userInput).get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // Check if english input matches translation
                    if (result.equals(userInput)) {
                        // Set text error, disable store and make translated TTS button invisible
                        translatedText.setText("Error: no translation available");
                        translatedSpeech.setVisibility(View.INVISIBLE);
                        enableStore(false);
                    } else {
                        // Set text as translation, enable store and
                        // make translated TTS button visible
                        translatedText.setText(result);
                        translatedSpeech.setVisibility(View.VISIBLE);
                        enableStore(true);
                    }
                }
            }
        });

        // Store on click
        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if expression has already been stored
                if (myDb.dataExists(enterWord.getText().toString())) {
                    // Show error as toast
                    Toast toast = Toast.makeText(context, "Your expression has already been stored.", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    // Store data and show feedback as toast
                    myDb.storeUserData(enterWord.getText().toString(), translatedText.getText().toString());
                    Toast toast = Toast.makeText(context, "Your expression has been stored.", Toast.LENGTH_SHORT);
                    toast.show();
                }
                clearInput();
            }
        });

        // The following code is modified from: Coding in Flow (2017)
        // 'Text to Speech - Android Studio Tutorial'
        // https://www.youtube.com/watch?v=DoYnz0GYN1w

        // Create Text to Speech for translated language
        translatedTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = translatedTTS.setLanguage(getLanguages().get(myDb.getID()-1).getLocale());

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        translatedSpeech.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        // Create English Text to Speech
        englishTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = englishTTS.setLanguage(Locale.ENGLISH);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        englishSpeech.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        // Text to Speech audio for user input on click
        englishSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(englishTTS, enterWord.getText().toString());
            }
        });

        // Text to Speech audio for translation on click
        translatedSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(translatedTTS, translatedText.getText().toString());
            }
        });
    }

    // Set speak rate to slower since users are learning and create method to start audio
    private void speak(TextToSpeech TTS, String text) {
        TTS.setSpeechRate((float) 0.75);
        TTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    // Stop and shut down Text to Speech
    @Override
    protected void onDestroy() {
        if (translatedTTS != null) {
            translatedTTS.stop();
            translatedTTS.shutdown();
        }
        if (englishTTS != null) {
            englishTTS.stop();
            englishTTS.shutdown();
        }
        super.onDestroy();
    }
    // Modified code stops here

    // Return to menu
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity_Self_Learn.class);
        startActivity(intent);
    }

    // Change store button transparency and whether enabled or not based on boolean
    public void enableStore(boolean enabled) {
        if (enabled) {
            store.setEnabled(true);
            store.setAlpha((float) 1);
        } else {
            store.setEnabled(false);
            store.setAlpha((float) 0.1);
        }
    }

    // Clear text
    public void clearInput() {
        enterWord.setText("");
        translatedText.setText("");
        enableStore(false);
        translatedSpeech.setVisibility(View.INVISIBLE);
    }

    // The following code is modified from: Oxford Dictionaries API Documentation
    // https://developer.oxforddictionaries.com/documentation#!/Entries/get_entries_source_lang_word_id
    // and Yandex Translate API Domcumentation
    // https://tech.yandex.com/translate/doc/dg/reference/translate-docpage/

    // Translate AsyncTask which calls API and returns result
    public class TranslateRequest extends AsyncTask<String, Integer, String> {

        public TranslateRequest() {
        }

        @Override
        protected String doInBackground(String... params) {
            String text = params[0];
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

                // Format and return translation result
                Log.d("Translation Result:", resultString);
                String resultFormatted = resultString.replace("'", "''");
                return resultFormatted;

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
}