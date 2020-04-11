package com.example.groupassignment.ui.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.groupassignment.MainActivity_Learn;
import com.example.groupassignment.R;

import java.util.ArrayList;

public class QuizSummary extends AppCompatActivity {
    TextView grade;
    TextView score;
    Button viewResults;
    Button menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_summary);

        grade = findViewById(R.id.grade);
        score = findViewById(R.id.englishTF);
        viewResults = findViewById(R.id.viewResults);
        menu = findViewById(R.id.menu);

        Intent intent1 = getIntent();
        final int level = intent1.getIntExtra("level", 0);

        if (level == 0) {
            this.setTitle("Self-Learn Quiz Results");
        } else {
            this.setTitle(LearnCategories.getCategories().get(level - 1).getCategoryName() + " Quiz Results");
        }

        final Bundle args = intent1.getBundleExtra("bundle");
        final ArrayList<QuizAnswers> quizAnswers = (ArrayList<QuizAnswers>) args.getSerializable("arraylist");

        int total = 0;
        for (int i = 0; i < quizAnswers.size(); i++) {
            total += quizAnswers.get(i).getScore();
        }

        score.setText("You scored "+total+" out of "+quizAnswers.size());

        double percentage = total/quizAnswers.size();
        if (percentage < 0.5) {
            grade.setText("Failed!");
        } else {
            grade.setText("Passed!");
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
                intent2.putExtra("level", level);
                startActivity(intent2);
            }
        });
    }
}
