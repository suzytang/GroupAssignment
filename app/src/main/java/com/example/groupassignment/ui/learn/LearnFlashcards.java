package com.example.groupassignment.ui.learn;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.groupassignment.LearnData;
import com.example.groupassignment.MainActivity_Learn;
import com.example.groupassignment.R;
import com.example.groupassignment.TranslateRequest;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.Locale;
import java.util.concurrent.ExecutionException;

import static java.util.Locale.FRENCH;

public class LearnFlashcards extends AppCompatActivity {
    final Locale lang = FRENCH;
    //EasyFlipView easyFlipView;
    TextView levelText;
    int i = 0;
    private TextToSpeech translatedTTS;
    private TextToSpeech englishTTS;
    private ImageButton translatedSpeech;
    private ImageButton englishSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_flashcards);

        final ImageButton next = findViewById(R.id.next);
        final ImageButton prev = findViewById(R.id.prev);
        final TextView question = findViewById(R.id.progress);
        final TextView frontText = findViewById(R.id.frontText);
        final TextView backText = findViewById(R.id.backText);
        final Button menu = findViewById(R.id.menu);
        final Button quiz = findViewById(R.id.quiz);



        translatedSpeech = findViewById(R.id.translatedSpeech);
        englishSpeech = findViewById(R.id.englishSpeech);

        Intent intent = getIntent();
        final String level = intent.getStringExtra("level");
//        String category = LearnData.getLearnData().get(Integer.parseInt(level)*10 - 1).getCategory();

        question.setText((i+1)+"/10");
        final int position = Integer.parseInt(level);

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(LearnFlashcards.this, QuizTest.class);
                intent.putExtra("level", level);
            startActivity(intent);
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LearnFlashcards.this, MainActivity_Learn.class);
                startActivity(intent);
            }
        });

        final EasyFlipView easyFlipView = (EasyFlipView) findViewById(R.id.easyFlipView);
        easyFlipView.setFlipDuration(500);
        easyFlipView.setFlipEnabled(true);
        easyFlipView.setAutoFlipBack(false);

        frontText.setText(LearnData.getLearnData().get(10*(position-1)+i).getText());
        TranslateRequest tR = new TranslateRequest();
        String result = "";
        try {
            result = tR.execute(LearnData.getLearnData().get(10*(position-1)+i).getText()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        backText.setText(result);

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

        /*easyFlipView.setOnFlipListener(new EasyFlipView.OnFlipAnimationListener() {
            @Override
            public void onViewFlipCompleted(EasyFlipView easyFlipView, EasyFlipView.FlipState newCurrentSide) {
                Toast.makeText(LearnFlashcards.this,
                        "Flip Completed! New Side is: " + newCurrentSide, Toast.LENGTH_LONG).show();
            }
        });*/

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            i++;
            prev.setEnabled(true);
            if (i < 10) {
                next.setEnabled(true);
                frontText.setText(LearnData.getLearnData().get(10 * (position - 1) + i).getText());
                TranslateRequest tR = new TranslateRequest();
                String result = "";
                try {
                    result = tR.execute(LearnData.getLearnData().get(10 * (position - 1) + i).getText()).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                backText.setText(result);
                question.setText((i+1)+"/10");
                if (i == 9) {
                    next.setEnabled(false);
                }
            }
            }
        });

        prev.setEnabled(false);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            i--;
            next.setEnabled(true);
            if (i < 10) {
                prev.setEnabled(true);
                frontText.setText(LearnData.getLearnData().get(10 * (position - 1) + i).getText());
                TranslateRequest tR = new TranslateRequest();
                String result = "";
                try {
                    result = tR.execute(LearnData.getLearnData().get(10 * (position - 1) + i).getText()).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                backText.setText(result);
                question.setText((i+1)+"/10");
                if (i == 0) {
                    prev.setEnabled(false);
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
                TranslateRequest tR = new TranslateRequest();
                String result = "";
                try {
                    result = tR.execute(LearnData.getLearnData().get(10 * (position - 1) + i).getText()).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                speak(translatedTTS, result);
            }
        });

        englishSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(englishTTS, LearnData.getLearnData().get(10 * (position - 1) + i).getText());
            }
        });
    }

    private void speak(TextToSpeech TTS, String text) {
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

//        public void updateText(int number1,int number2) {
//            frontText.setText(LearnData.getLearnData().get(10 * (number1 - 1) + number2).getText());
//            TranslateRequest tR1 = new TranslateRequest();
//            String result1 = "";
//            try {
//                result1 = tR1.execute(LearnData.getLearnData().get(10 * (number1 - 1) + number2).getText()).get();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            backText.setText(result1);
//            question.setText("Question "+(number2+1));
//            if (i == 9) {
//                button.setText("Finish");
//            }
//        }
}



