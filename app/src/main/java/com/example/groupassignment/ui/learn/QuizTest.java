package com.example.groupassignment.ui.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.groupassignment.DatabaseHelper;
import com.example.groupassignment.R;

import java.util.ArrayList;
import java.util.Collections;

public class QuizTest extends AppCompatActivity {

    Button submit;
    TextView progress;
    TextView question;
    EditText input;

    DatabaseHelper myDb = new DatabaseHelper(this);

    int i = 1;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_test);

        Intent intent = getIntent();
        final int level = intent.getIntExtra("level", 0);
        final String table = intent.getStringExtra("table");
        this.setTitle(LearnCategories.getCategories().get(level-1).getCategoryName()+" Quiz");

        submit = findViewById(R.id.submit);
        progress = findViewById(R.id.progress);
        question = findViewById(R.id.question);
        input = findViewById(R.id.input);

        final ArrayList<QuizAnswers> quizAnswers = new ArrayList<QuizAnswers>();

        final ArrayList<Integer> shuffle = new ArrayList<Integer>();
        for (int j = 1; j < 11; j++) {
            shuffle.add(j);
        }
        Collections.shuffle(shuffle);

        int amount = 0;
        if (table.equals("learn_table")) {
            amount = 10;
        }

        progress.setText(i + "/"+amount);
        question.setText(myDb.pullData(table, "Expression",level,shuffle.get(i-1)));

        final int finalAmount = amount;
        final int finalAmount1 = amount;
        final int finalAmount2 = amount;

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (i < finalAmount +1) {
                String translation = myDb.pullData(table, "Translation",level,shuffle.get(i-1));

                if (input.getText().toString().toLowerCase().equals(translation.toLowerCase())) {
                    score = 1;
                } else {
                    score = 0;
                }
                QuizAnswers answer = new QuizAnswers(i, myDb.pullData(table, "Expression",level,shuffle.get(i-1)), score, input.getText().toString(), translation);
                quizAnswers.add(answer);
                i++;
                if (i == finalAmount1 -1) {
                    submit.setText("Complete");
                }
                if (i < finalAmount1 +1) {
                    question.setText(myDb.pullData(table, "Expression",level,shuffle.get(i-1)));
                    progress.setText((i) + "/"+ finalAmount2);
                    input.getText().clear();
                } else {
                    Intent intent = new Intent(QuizTest.this, QuizSummary.class);
                    Bundle args = new Bundle();
                    args.putSerializable("arraylist", quizAnswers);
                    intent.putExtra("bundle", args);
                    intent.putExtra("level", level);
                    startActivity(intent);
                }
            }
//            String s = "";
//            for (int i = 0; i < quizAnswers.size(); i++) {
//                s += quizAnswers.get(i).getQuestion() + " ,";
//                s += quizAnswers.get(i).getEnglish() + " ,";
//                s += quizAnswers.get(i).getScore() + " ,";
//                s += quizAnswers.get(i).getAnswer() + " ,";
//                s += quizAnswers.get(i).getTranslation() + " ,";
//                s += "\n";
//            }
//            check.setText(s);
            }
        });
    }
}