package com.example.groupassignment.ui.learn;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.groupassignment.DatabaseHelper;
import com.example.groupassignment.MainActivity_Learn;
import com.example.groupassignment.R;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.Locale;

import static java.util.Locale.FRENCH;

public class LearnFlashcards extends AppCompatActivity {
    // Locale for translated Text to Speech
    final public static Locale lang = FRENCH;

    // DatabaseHelper for queries
    DatabaseHelper myDb = new DatabaseHelper(this);

    // Declare variables
    int i = 1;
    int amount, category;
    private TextToSpeech translatedTTS, englishTTS;
    private ImageButton translatedSpeech, englishSpeech, next, prev;
    private TextView question, frontText, backText, yandex;
    private EasyFlipView easyFlipView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_flashcards);

        // Link with XML
        next = findViewById(R.id.next);
        prev = findViewById(R.id.prev);
        question = findViewById(R.id.progress);
        frontText = findViewById(R.id.frontText);
        backText = findViewById(R.id.backText);
        translatedSpeech = findViewById(R.id.translatedSpeech);
        englishSpeech = findViewById(R.id.englishSpeech);
        // Credit yandex API
        yandex = findViewById(R.id.yandexCredit3);
        yandex.setMovementMethod(LinkMovementMethod.getInstance());

        amount = 0;

        // Get category
        Intent intent = getIntent();
        category = intent.getIntExtra("category",0);

        // Get number of expressions for flashcards
        if (category != 0) {
            amount = 10;
            this.setTitle(LearnCategories.getCategories().get(category - 1).getCategoryName() + " Flashcards");
        } else {
            amount = myDb.countUserData();
            this.setTitle("Self-Learn Flashcards");
        }


        // @Suzy pls do source code
        easyFlipView = findViewById(R.id.easyFlipView);
        easyFlipView.setFlipDuration(500);
        easyFlipView.setFlipEnabled(true);
        easyFlipView.setAutoFlipBack(false);

        // Flip view on click
        findViewById(R.id.frontCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                easyFlipView.flipTheView();
            }
        });

        findViewById(R.id.backCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                easyFlipView.flipTheView();
            }
        });


        // Set first flashcard
        question.setText((i)+"/"+amount);
        frontText.setText(myDb.pullData("Expression",category,i));
        backText.setText(myDb.pullData("Translation",category,i));

        // Move to next card
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            i++;
            // Enable previous since moving to next
            prev.setEnabled(true);
            prev.setAlpha((float) 1);
            if (i <= amount) {
                next.setEnabled(true);
                next.setAlpha((float) 1);
                updateFlashCards();
                // Disable next button if last flashcard
                if (i == amount) {
                    next.setEnabled(false);
                    next.setAlpha((float) 0.1);
                }
            }
            }
        });

        // Move to previous card
        // Starts at disabled because onCreate, it is the first flashcard
        prev.setEnabled(false);
        prev.setAlpha((float) 0.1);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            i--;
            // Enable next since moving to previous
            next.setEnabled(true);
            next.setAlpha((float) 1);
            if (i <= amount) {
                prev.setEnabled(true);
                prev.setAlpha((float) 1);
                updateFlashCards();
                // Disable prev button if first flashcard
                if (i == 1) {
                    prev.setEnabled(false);
                    prev.setAlpha((float) 0.1);
                }
            }
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

        // Text to Speech audio for translation on click
        translatedSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            speak(translatedTTS,myDb.pullData("Translation",category,i));
            }
        });

        // Text to Speech audio for english expression on click
        englishSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            speak(englishTTS,myDb.pullData("Expression",category,i));
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

    // Update flashcards by setting the question, front text and back text
    private void updateFlashCards() {
        frontText.setText(myDb.pullData("Expression",category,i));
        backText.setText(myDb.pullData("Translation",category,i));
        question.setText((i)+"/"+ amount);
    }
}



