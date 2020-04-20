package com.example.groupassignment.ui.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.groupassignment.DatabaseHelper;
import com.example.groupassignment.MainActivity_Learn;
import com.example.groupassignment.R;
import com.example.groupassignment.SQLiteHelper;

import java.util.ArrayList;

public class QuizSummary extends AppCompatActivity {
    TextView grade;
    TextView score;
    TextView coinsIncrease;
    TextView levelUp;
    Button viewResults;
    Button menu;

    DatabaseHelper myDb = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_summary);

        grade = findViewById(R.id.grade);
        score = findViewById(R.id.englishTF);
        coinsIncrease = findViewById(R.id.coinsIncrease);
        levelUp = findViewById(R.id.levelUp);
        viewResults = findViewById(R.id.viewResults);
        menu = findViewById(R.id.menu);

        final SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        final DatabaseHelper myDb = new DatabaseHelper(this);

        Intent intent1 = getIntent();
        final int category = intent1.getIntExtra("category", 0);

        if (category == 0) {
            this.setTitle("Self-Learn Quiz Results");
        } else {
            this.setTitle(LearnCategories.getCategories().get(category - 1).getCategoryName() + " Quiz Results");
        }

        final Bundle args = intent1.getBundleExtra("bundle");
        final ArrayList<QuizAnswers> quizAnswers = (ArrayList<QuizAnswers>) args.getSerializable("arraylist");

        int total = 0;
        int coinsEarned = 0;
        int coinsCurrrent = Integer.parseInt(sqLiteHelper.getData(SQLiteHelper.COL_4, 1));

        for (int i = 0; i < quizAnswers.size(); i++) {
            if (quizAnswers.get(i).getScore() > 0) {
                total++;
                if (quizAnswers.get(i).getScore() == 2) {
                    coinsEarned += 20;
                } else {
                    coinsEarned += 5;
                }
            }
        }

        sqLiteHelper.update(1, "Coins", "Coins", coinsCurrrent + coinsEarned);
        coinsIncrease.setText("+"+coinsEarned);
        score.setText("You scored "+total+" out of "+quizAnswers.size());

        double percentage = Double.valueOf(total)/Double.valueOf(quizAnswers.size());

        if (percentage < 0.5) {
            grade.setText("Failed!");
            grade.setTextColor(Color.RED);
        } else {
            grade.setText("Passed!");
            grade.setTextColor(Color.GREEN);
        }

        if (category != 0) {
            if (percentage == 1) {
                if (myDb.completed(category)) {
                    levelUp.setText("Congratulations on acing the test again!");
                } else {
                    int currentLevel = (int) sqLiteHelper.getPetTime("LVL");
                    String newLevel = String.valueOf(currentLevel + 1);
                    sqLiteHelper.updatePetData("LVL", newLevel, 1);
                    levelUp.setText("Level Up!" + " Level is now " + newLevel);
                    myDb.setCompleted(category);
                }
            }
        }

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizSummary.this, MainActivity_Learn.class);
                startActivity(intent);
            }
        });

        viewResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(QuizSummary.this, QuizResults.class);;
                args.putSerializable("arraylist", quizAnswers);
                intent2.putExtra("bundle", args);
                intent2.putExtra("category", category);
                startActivity(intent2);
            }
        });
    }
}
