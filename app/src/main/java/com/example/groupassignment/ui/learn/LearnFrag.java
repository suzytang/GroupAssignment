package com.example.groupassignment.ui.learn;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.groupassignment.DatabaseHelper;
import com.example.groupassignment.MainActivity_Learn;
import com.example.groupassignment.R;
import com.google.android.material.tabs.TabLayout;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.Locale;

import static java.util.Locale.FRENCH;

public class LearnFrag extends Fragment {


    final public static Locale lang = FRENCH;
    DatabaseHelper myDb = new DatabaseHelper(getActivity());

    //EasyFlipView easyFlipView;
    TextView levelText;
    int i = 1;
    private TextToSpeech translatedTTS;
    private TextToSpeech englishTTS;
    private ImageButton translatedSpeech;
    private ImageButton englishSpeech;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.study_flashcards, container, false);

        final DatabaseHelper myDb = new DatabaseHelper(getActivity());

        final ImageButton next = root.findViewById(R.id.next);
        final ImageButton prev = root.findViewById(R.id.prev);
        final TextView question = root.findViewById(R.id.progress);
        final TextView frontText = root.findViewById(R.id.frontText);
        final TextView backText = root.findViewById(R.id.backText);
        final Button menu = root.findViewById(R.id.menu);
        final Button quiz = root.findViewById(R.id.storeButton);

        translatedSpeech = root.findViewById(R.id.translatedSpeech);
        englishSpeech = root.findViewById(R.id.englishSpeech);

        int amount = 0;
        //Intent intent = getIntent();
        //final int level = intent.getIntExtra("level",0);

        //final int level = getArguments().getInt("level");
        final int level = 2;
        final String table = "learn_table";
        //final String table = intent.getStringExtra("table");
        if (table.equals("learn_table")) {
            amount = 10;
            getActivity().setTitle(LearnCategories.getCategories().get(level - 1).getCategoryName() + " Flashcards");
        } else {
            amount = myDb.rowsUserData();
            getActivity().setTitle("Self-Learn Flashcards");
        }

        question.setText((i)+"/"+amount);

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuizTest.class);
                intent.putExtra("level", level);
                intent.putExtra("table", table);
                startActivity(intent);
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity_Learn.class);
                intent.putExtra("level", level);
                intent.putExtra("table", table);
                startActivity(intent);
            }
        });

        final EasyFlipView easyFlipView = (EasyFlipView) root.findViewById(R.id.easyFlipView);
        easyFlipView.setFlipDuration(500);
        easyFlipView.setFlipEnabled(true);
        easyFlipView.setAutoFlipBack(false);

        frontText.setText(myDb.pullData(table,"Expression",level,i));
        backText.setText(myDb.pullData(table,"Translation",level,i));

        root.findViewById(R.id.frontCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(LearnFlashcards.this, "Front Card", Toast.LENGTH_SHORT).show();
                easyFlipView.flipTheView();
            }
        });

        root.findViewById(R.id.backCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(LearnFlashcards.this, "Back Card", Toast.LENGTH_SHORT).show();
                easyFlipView.flipTheView();
            }
        });

        final int finalAmount = amount;
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                prev.setEnabled(true);
                if (i <= finalAmount) {
                    next.setEnabled(true);
                    frontText.setText(myDb.pullData(table,"Expression",level,i));
                    backText.setText(myDb.pullData(table,"Translation",level,i));
                    question.setText((i)+"/"+ finalAmount);
                    if (i == finalAmount) {
                        next.setEnabled(false);
                    }
                }
            }
        });

        prev.setEnabled(false);
        final int finalAmount1 = amount;
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i--;
                next.setEnabled(true);
                if (i <= finalAmount1) {
                    prev.setEnabled(true);
                    frontText.setText(myDb.pullData(table,"Expression",level,i));
                    backText.setText(myDb.pullData(table,"Translation",level,i));
                    question.setText((i)+"/"+ finalAmount1);
                    if (i == 1) {
                        prev.setEnabled(false);
                    }
                }
            }
        });

        final EasyFlipView easyFlipView2 = root.findViewById(R.id.easyFlipView);
        easyFlipView2.setFlipDuration(500);
        easyFlipView2.setToHorizontalType();
        easyFlipView2.setFlipTypeFromLeft();

        translatedTTS = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
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

        englishTTS = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
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
                speak(translatedTTS,myDb.pullData(table,"Translation",level,i));
            }
        });

        englishSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(englishTTS,myDb.pullData(table,"Expression",level,i));
            }
        });

        return root;
    }
    private void speak(TextToSpeech TTS, String text) {
        TTS.setSpeechRate((float) 0.75);
        TTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onDestroy() {
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
