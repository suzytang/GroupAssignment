package com.example.groupassignment.ui.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.groupassignment.DatabaseHelper;
import com.example.groupassignment.MainActivity_Learn;
import com.example.groupassignment.R;

import java.util.Random;

public class Practice extends AppCompatActivity {

    DatabaseHelper myDb = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        final Button refresh = findViewById(R.id.refresh);
        final ImageButton trueButton = findViewById(R.id.trueButton);
        final ImageButton falseButton = findViewById(R.id.falseButton);

        final TextView englishTF = findViewById(R.id.englishTF);
        final TextView translatedTF = findViewById(R.id.translatedTF);

        final TextView resultTF = findViewById(R.id.resultTF);
        final TextView correctTF = findViewById(R.id.correctTF);
        final TextView selection = findViewById(R.id.selection);

        final Button menu = findViewById(R.id.menu);
        final Button quiz = findViewById(R.id.storeButton);
        final Button learn = findViewById(R.id.learn);

        Intent intent = getIntent();
        final int category = intent.getIntExtra("category",0);
        this.setTitle(LearnCategories.getCategories().get(category-1).getCategoryName()+" True / False Practice");

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

        englishTF.setText(myDb.pullData( "Expression",category, randomEnglish[0]));
        if (tf[0] == 1) {
            translatedTF.setText(myDb.pullData("Translation", category, randomEnglish[0]));
        } else {
            while (randomEnglish[0] == randomTranslate[0]) {
                randomTranslate[0] = random.nextInt(9) + 1;
            }
            translatedTF.setText(myDb.pullRandom("Translation", randomTranslate[0]));
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
                correctTF.setText("The translation of \""+myDb.pullData( "Expression",category, randomEnglish[0])+"\" is \""+myDb.pullData( "Translation", category, randomEnglish[0])+"\".");
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
                correctTF.setText("The translation of \""+myDb.pullData("Expression",category, randomEnglish[0])+"\" is \""+myDb.pullData( "Translation", category, randomEnglish[0])+"\".");
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

                englishTF.setText(myDb.pullData("Expression",category, randomEnglish[0]));
                if (tf[0] == 1) {
                    translatedTF.setText(myDb.pullData( "Translation", category, randomEnglish[0]));
                } else {
                    while (randomEnglish[0] == randomTranslate[0]) {
                        randomTranslate[0] = random.nextInt(9) + 1;
                    }
                    translatedTF.setText(myDb.pullRandom( "Translation", randomTranslate[0]));
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
                        correctTF.setText("The translation of \""+myDb.pullData( "Expression",category, randomEnglish[0])+"\" is \""+myDb.pullData( "Translation", category, randomEnglish[0])+"\".");
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
                        correctTF.setText("The translation of \""+myDb.pullData( "Expression",category, randomEnglish[0])+"\" is \""+myDb.pullData( "Translation", category, randomEnglish[0])+"\".");
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
                Intent intent = new Intent(Practice.this, QuizTest.class);
                intent.putExtra("category", category);
                startActivity(intent);
            }
        });

        learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Practice.this, LearnFlashcards.class);
                intent.putExtra("category",category);
                startActivity(intent);
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Practice.this, MainActivity_Learn.class);
                startActivity(intent);
            }
        });

    }
}