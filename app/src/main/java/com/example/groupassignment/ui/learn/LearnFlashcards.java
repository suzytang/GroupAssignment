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
    final public static Locale lang = FRENCH;
    DatabaseHelper myDb = new DatabaseHelper(this);

    //EasyFlipView easyFlipView;
    int i = 1;
    int amount, category;
    private TextToSpeech translatedTTS, englishTTS;
    private ImageButton translatedSpeech, englishSpeech, next, prev;
    private TextView question, frontText, backText, yandex;
//    private Button menu, quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_flashcards);

        next = findViewById(R.id.next);
        prev = findViewById(R.id.prev);
        question = findViewById(R.id.progress);
        frontText = findViewById(R.id.frontText);
        backText = findViewById(R.id.backText);
//        menu = findViewById(R.id.menu);
//        quiz = findViewById(R.id.quiz);
        translatedSpeech = findViewById(R.id.translatedSpeech);
        englishSpeech = findViewById(R.id.englishSpeech);
        yandex = findViewById(R.id.yandexCredit3);
        yandex.setMovementMethod(LinkMovementMethod.getInstance());

        amount = 0;
        Intent intent = getIntent();
        category = intent.getIntExtra("category",0);
        if (category != 0) {
            amount = 10;
            this.setTitle(LearnCategories.getCategories().get(category - 1).getCategoryName() + " Flashcards");
        } else {
            amount = myDb.countUserData();
            this.setTitle("Self-Learn Flashcards");
        }

        question.setText((i)+"/"+amount);

//        quiz.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            Intent intent = new Intent(LearnFlashcards.this, QuizTest.class);
//            intent.putExtra("category", category);
//            startActivity(intent);
//            }
//        });
//
//        menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LearnFlashcards.this, MainActivity_Learn.class);
//                intent.putExtra("category", category);
//                startActivity(intent);
//            }
//        });

        final EasyFlipView easyFlipView = (EasyFlipView) findViewById(R.id.easyFlipView);
        easyFlipView.setFlipDuration(500);
        easyFlipView.setFlipEnabled(true);
        easyFlipView.setAutoFlipBack(false);

        frontText.setText(myDb.pullData("Expression",category,i));
        backText.setText(myDb.pullData("Translation",category,i));

        findViewById(R.id.frontCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(LearnFlashcards.this, "Front Card", Toast.LENGTH_SHORT).show();
                easyFlipView.flipTheView();
            }
        });

        findViewById(R.id.backCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(LearnFlashcards.this, "Back Card", Toast.LENGTH_SHORT).show();
                easyFlipView.flipTheView();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            i++;
            prev.setEnabled(true);
            prev.setAlpha((float) 1);
            if (i <= amount) {
                next.setEnabled(true);
                next.setAlpha((float) 1);
                updateFlashCards(i);
                if (i == amount) {
                    next.setEnabled(false);
                    next.setAlpha((float) 0.1);
                }
            }
            }
        });

        prev.setEnabled(false);
        prev.setAlpha((float) 0.1);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            i--;
            next.setEnabled(true);
            next.setAlpha((float) 1);
            if (i <= amount) {
                prev.setEnabled(true);
                prev.setAlpha((float) 1);
                updateFlashCards(i);
                if (i == 1) {
                    prev.setEnabled(false);
                    prev.setAlpha((float) 0.1);
                }
            }
            }
        });

        final EasyFlipView easyFlipView2 = findViewById(R.id.easyFlipView);
        easyFlipView2.setFlipDuration(500);
        easyFlipView2.setToHorizontalType();
        easyFlipView2.setFlipTypeFromLeft();

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

        translatedSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            speak(translatedTTS,myDb.pullData("Translation",category,i));
            }
        });

        englishSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            speak(englishTTS,myDb.pullData("Expression",category,i));
            }
        });
    }

    public void updateFlashCards(int j) {
        frontText.setText(myDb.pullData("Expression",category,j));
        backText.setText(myDb.pullData("Translation",category,j));
        question.setText((i)+"/"+ amount);
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



