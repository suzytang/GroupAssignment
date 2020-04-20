package com.example.groupassignment.ui.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.groupassignment.DatabaseHelper;
import com.example.groupassignment.MainActivity_Learn;
import com.example.groupassignment.R;

import java.util.Random;

public class Practice extends AppCompatActivity {

    DatabaseHelper myDb = new DatabaseHelper(this);
    Random random = new Random();
    int strikes, randomEnglish, randomTranslate, amount, category;
    boolean correctAnswer, userAnswer;
    int correct;
    private ImageView strike1, strike2, strike3;
    private Button refresh, menu, quiz, learn;
    private TextView englishTF, translatedTF, resultTF, correctTF, selection;
    private ImageButton trueButton, falseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        strike1 = findViewById(R.id.strike1);
        strike2 = findViewById(R.id.strike2);
        strike3 = findViewById(R.id.strike3);

        strike1.setVisibility(View.INVISIBLE);
        strike2.setVisibility(View.INVISIBLE);
        strike3.setVisibility(View.INVISIBLE);

        strikes = 0;

        refresh = findViewById(R.id.refresh);
        trueButton = findViewById(R.id.trueButton);
        falseButton = findViewById(R.id.falseButton);

        englishTF = findViewById(R.id.englishTF);
        translatedTF = findViewById(R.id.translatedTF);

        resultTF = findViewById(R.id.resultTF);
        correctTF = findViewById(R.id.correctTF);
        selection = findViewById(R.id.selection);

        menu = findViewById(R.id.menu);
        quiz = findViewById(R.id.storeButton);
        learn = findViewById(R.id.learn);

        Intent intent = getIntent();
        category = intent.getIntExtra("category",0);
        amount = 0;
        if (category != 0) {
            amount = 10;
            this.setTitle(LearnCategories.getCategories().get(category - 1).getCategoryName() + " True or False Practice");
        } else {
            amount = myDb.countUserData();
            this.setTitle("Self-Learn True or False Practice");
        }

        updateUI();

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUI();
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

    private void updateUI() {
        randomEnglish = random.nextInt(amount-1) + 1;
        randomTranslate = random.nextInt(myDb.countData()-1) + 1;

        double y = Math.random();
        if (y > 0.5) {
            correctAnswer = true;
        } else {
            correctAnswer = false;
        }
        refresh.setEnabled(false);

        englishTF.setText(myDb.pullData("Expression",category, randomEnglish));
        if (correctAnswer) {
            translatedTF.setText(myDb.pullData("Translation", category, randomEnglish));
        } else {
            while (randomEnglish == randomTranslate) {
                randomTranslate = random.nextInt(myDb.countData()-1) + 1;
            }
            translatedTF.setText(myDb.pullRandom("Translation", randomTranslate));
        }
        resultTF.setText(String.valueOf(correct));
        correctTF.setText("");
        selection.setText("");

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswer = true;
                if (showResult(userAnswer, correctAnswer)) {
                    correct++;
                }
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswer = false;
                if (showResult(userAnswer, correctAnswer)) {
                    correct++;
                }
            }
        });

        refresh.setEnabled(false);
        trueButton.setEnabled(true);
        falseButton.setEnabled(true);
    }

    private void updateStrikes(int strikes) {
        switch (strikes) {
            case 1:
                strike1.setVisibility(View.VISIBLE);
                break;
            case 2:
                strike2.setVisibility(View.VISIBLE);
                break;
            case 3:
                strike3.setVisibility(View.VISIBLE);
                break;
        }
    }

    private boolean showResult(boolean userAnswer, boolean correctAnswer) {
        selection.setText("You selected "+userAnswer+".");
        trueButton.setEnabled(false);
        falseButton.setEnabled(false);
        refresh.setEnabled(true);
        correctTF.setText("The translation of \""+myDb.pullData("Expression",category, randomEnglish)+"\" is \""+myDb.pullData( "Translation", category, randomEnglish)+"\".");
        if (userAnswer == correctAnswer) {
            resultTF.setText("Correct!");
            resultTF.setTextColor(Color.GREEN);
            return true;
        } else {
            resultTF.setText("Incorrect!");
            resultTF.setTextColor(Color.RED);
            strikes++;
            updateStrikes(strikes);
            return false;
        }
    }
}