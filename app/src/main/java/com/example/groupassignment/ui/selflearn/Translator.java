package com.example.groupassignment.ui.selflearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.groupassignment.DatabaseHelper;
import com.example.groupassignment.R;
import com.example.groupassignment.TranslateRequest;

import java.util.Locale;
import java.util.concurrent.ExecutionException;

import static com.example.groupassignment.ui.learn.LearnFlashcards.lang;

public class Translator extends AppCompatActivity {
    DatabaseHelper myDb = new DatabaseHelper(this);

    private TextToSpeech translatedTTS;
    private TextToSpeech englishTTS;
    private ImageButton translatedSpeech;
    private ImageButton englishSpeech;
    private Button translate, store;

    private TextView translatedText, storeResponse;
    private EditText enterWord;
    private Context context;
    private boolean translated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translator);

        context = this;

        translatedText = findViewById(R.id.translatedText);

        enterWord = findViewById(R.id.enterWord);

        translatedSpeech = findViewById(R.id.translatedSpeech);
        translatedSpeech.setVisibility(View.INVISIBLE);

        englishSpeech = findViewById(R.id.englishSpeech);

        translate = findViewById(R.id.translate);
        storeResponse = findViewById(R.id.storeResponse);

        store = findViewById(R.id.storeButton);
        store.setVisibility(View.INVISIBLE);

        translated = false;

        translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (translated) {
                    enterWord.getText().clear();
                    translatedText.setText("");
                    storeResponse.setText("");
                    translate.setText("Translate");
                    translatedSpeech.setVisibility(View.INVISIBLE);
                    store.setVisibility(View.INVISIBLE);
                    translated = false;
                } else {
                    TranslateRequest tR = new TranslateRequest();
                    if (enterWord.getText().toString().equals("")) {
                        translatedText.setText("Error: please enter an expression first");
                    } else {
                        String result = null;
                        try {
                            result = tR.execute(enterWord.getText().toString()).get();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (result.equals(enterWord.getText().toString())) {
                            translatedText.setText("Error: no translation available");
                            translated = true;
                            translate.setText("Re-try");
                        } else {
                            translated = true;
                            translatedText.setText(result);
                            translate.setText("Refresh");
                            translatedSpeech.setVisibility(View.VISIBLE);
                            store.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });

        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myDb.dataExists(enterWord.getText().toString())) {
                    storeResponse.setText("You expression has already been stored.");
                } else {
                    myDb.storeUserData(enterWord.getText().toString(),translatedText.getText().toString());
                    storeResponse.setText("Your expression has been stored.");
                }
                store.setVisibility(View.INVISIBLE);
            }
        });

        translatedTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = translatedTTS.setLanguage(lang);

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

        englishSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(englishTTS,enterWord.getText().toString());
            }
        });

        translatedSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(translatedTTS,translatedText.getText().toString());
            }
        });
    }

    private void speak(TextToSpeech TTS, String text) {
        TTS.setSpeechRate((float) 0.75);
        TTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

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
}
