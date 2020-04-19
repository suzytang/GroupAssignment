package com.example.groupassignment.ui.learn;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.groupassignment.DatabaseHelper;
import com.example.groupassignment.MainActivity_Learn;
import com.example.groupassignment.R;

import java.util.Random;

public class PractiseFrag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_practice, container, false);

        final DatabaseHelper myDb = new DatabaseHelper(getActivity());

        final Button refresh = root.findViewById(R.id.refresh);
        final ImageButton trueButton = root.findViewById(R.id.trueButton);
        final ImageButton falseButton = root.findViewById(R.id.falseButton);

        final TextView englishTF = root.findViewById(R.id.englishTF);
        final TextView translatedTF = root.findViewById(R.id.translatedTF);

        final TextView resultTF = root.findViewById(R.id.resultTF);
        final TextView correctTF = root.findViewById(R.id.correctTF);
        final TextView selection = root.findViewById(R.id.selection);

        final Button menu = root.findViewById(R.id.menu);
        final Button quiz = root.findViewById(R.id.storeButton);
        final Button learn = root.findViewById(R.id.learn);

        //Intent intent = getIntent();
        //final int level = intent.getIntExtra("level",0);

        //final int level = getArguments().getInt("level");
        final int level = 2;
        final String table = "learn_table";

        //final String table = intent.getStringExtra("table");
        getActivity().setTitle(LearnCategories.getCategories().get(level-1).getCategoryName()+" True / False Practice");

        final Random random = new Random();

        final int[] randomEnglish = new int[1];
        final int[] randomTranslate = new int[1];
        final int[] tf = new int[1];
        randomEnglish[0] = random.nextInt(9) + 1;
        randomTranslate[0] = random.nextInt(89) + 1;
        double x = Math.random();
        if (x > 0.5) {
            tf[0] = 1;
        } else {
            tf[0] = 0;
        }
        refresh.setEnabled(false);

        englishTF.setText(myDb.pullData(table, "Expression",level, randomEnglish[0]));
        if (tf[0] == 1) {
            translatedTF.setText(myDb.pullData(table, "Translation", level, randomEnglish[0]));
        } else {
            while (randomEnglish[0] == randomTranslate[0]) {
                randomTranslate[0] = random.nextInt(9) + 1;
            }
            translatedTF.setText(myDb.pullAllData(table, "Translation", randomTranslate[0]));
        }

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selection.setText("You selected true.");
                if (tf[0] == 1) {
                    resultTF.setText("Correct!");
                    resultTF.setTextColor(Color.GREEN);
                } else {
                    resultTF.setText("Incorrect!");
                    resultTF.setTextColor(Color.RED);
                }
                trueButton.setEnabled(false);
                falseButton.setEnabled(false);
                refresh.setEnabled(true);
                correctTF.setText("The translation of \""+myDb.pullData(table, "Expression",level, randomEnglish[0])+"\" is \""+myDb.pullData(table, "Translation", level, randomEnglish[0])+"\".");
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selection.setText("You selected false.");
                if (tf[0] == 0) {
                    resultTF.setText("Correct!");
                    resultTF.setTextColor(Color.GREEN);
                } else {
                    resultTF.setText("Incorrect!");
                    resultTF.setTextColor(Color.RED);
                }
                trueButton.setEnabled(false);
                falseButton.setEnabled(false);
                refresh.setEnabled(true);
                correctTF.setText("The translation of \""+myDb.pullData(table, "Expression",level, randomEnglish[0])+"\" is \""+myDb.pullData(table, "Translation", level, randomEnglish[0])+"\".");
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomEnglish[0] = random.nextInt(9) + 1;
                randomTranslate[0] = random.nextInt(89) + 1;
                double y = Math.random();
                if (y > 0.5) {
                    tf[0] = 1;
                } else {
                    tf[0] = 0;
                }
                refresh.setEnabled(false);

                englishTF.setText(myDb.pullData(table, "Expression",level, randomEnglish[0]));
                if (tf[0] == 1) {
                    translatedTF.setText(myDb.pullData(table, "Translation", level, randomEnglish[0]));
                } else {
                    while (randomEnglish[0] == randomTranslate[0]) {
                        randomTranslate[0] = random.nextInt(9) + 1;
                    }
                    translatedTF.setText(myDb.pullAllData(table, "Translation", randomTranslate[0]));
                }
                resultTF.setText("");
                correctTF.setText("");
                selection.setText("");

                trueButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selection.setText("You selected true.");
                        if (tf[0] == 1) {
                            resultTF.setText("Correct!");
                            resultTF.setTextColor(Color.GREEN);
                        } else {
                            resultTF.setText("Incorrect!");
                            resultTF.setTextColor(Color.RED);
                        }
                        trueButton.setEnabled(false);
                        falseButton.setEnabled(false);
                        refresh.setEnabled(true);
                        correctTF.setText("The translation of \""+myDb.pullData(table, "Expression",level, randomEnglish[0])+"\" is \""+myDb.pullData(table, "Translation", level, randomEnglish[0])+"\".");
                    }
                });

                falseButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selection.setText("You selected false.");
                        if (tf[0] == 0) {
                            resultTF.setText("Correct!");
                            resultTF.setTextColor(Color.GREEN);
                        } else {
                            resultTF.setText("Incorrect!");
                            resultTF.setTextColor(Color.RED);
                        }
                        trueButton.setEnabled(false);
                        falseButton.setEnabled(false);
                        refresh.setEnabled(true);
                        correctTF.setText("The translation of \""+myDb.pullData(table, "Expression",level, randomEnglish[0])+"\" is \""+myDb.pullData(table, "Translation", level, randomEnglish[0])+"\".");
                    }
                });

                refresh.setEnabled(false);
                trueButton.setEnabled(true);
                falseButton.setEnabled(true);
            }
        });

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuizTest.class);
                intent.putExtra("level", level);
                intent.putExtra("table", table);
                startActivity(intent);
            }
        });

        learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LearnFlashcards.class);
                intent.putExtra("level",level);
                intent.putExtra("table", table);
                startActivity(intent);
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity_Learn.class);
                startActivity(intent);
            }
        });



        return root;
    }
}

