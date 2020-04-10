package com.example.groupassignment.ui.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.groupassignment.LearnData;
import com.example.groupassignment.R;
import com.example.groupassignment.TranslateRequest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class QuizTest extends AppCompatActivity {

    Button submit;
    TextView progress;
    TextView question;
    EditText input;

    TextView right;
    TextView wrong;

    int count = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_test);

        Intent intent = getIntent();
        final String level = intent.getStringExtra("level");
        final int position = Integer.parseInt(level);

        submit = findViewById(R.id.submit);
        progress = findViewById(R.id.progress);
        question = findViewById(R.id.question);
        input = findViewById(R.id.input);

        final ArrayList<QuizAnswers> quizAnswers = new ArrayList<QuizAnswers>();

        final ArrayList<Integer> shuffle = new ArrayList<Integer>();
        for (int j = 0; j < 10; j++) {
            shuffle.add(j);
        }
        Collections.shuffle(shuffle);

        progress.setText((count + 1) + "/10");
        question.setText(LearnData.getLearnData().get(10 * (position - 1) + shuffle.get(0)).getText());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count < 10) {
                    TranslateRequest tR = new TranslateRequest();
                    String result = null;
                    try {
                        result = tR.execute(LearnData.getLearnData().get(10 * (position - 1) + shuffle.get(count)).getText().toLowerCase()).get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (input.getText().toString().toLowerCase().equals(result)) {
                        score = 1;
                    } else {
                        score = 0;
                    }
                    QuizAnswers answer = new QuizAnswers(count+1, LearnData.getLearnData().get(10 * (position - 1) + shuffle.get(count)).getText(), score, input.getText().toString(), result);
                    quizAnswers.add(answer);
                    count++;
                    if (count == 9) {
                        submit.setText("Complete");
                    }
                    if (count < 10) {
                        question.setText(LearnData.getLearnData().get(10 * (position - 1) + shuffle.get(count)).getText());
                        progress.setText((count + 1) + "/10");
                        input.getText().clear();
                    } else {
                        Intent intent = new Intent(QuizTest.this, QuizSummary.class);
                        Bundle args = new Bundle();
                        args.putSerializable("ARRAYLIST", quizAnswers);
                        intent.putExtra("BUNDLE", args);
                        intent.putExtra("level", level);
                        startActivity(intent);
                    }
                }
//                String s = "";
//                for (int i = 0; i < quizAnswers.size(); i++) {
//                    s += quizAnswers.get(i).getQuestion() + " ,";
//                    s += quizAnswers.get(i).getEnglish() + " ,";
//                    s += quizAnswers.get(i).getScore() + " ,";
//                    s += quizAnswers.get(i).getAnswer() + " ,";
//                    s += quizAnswers.get(i).getTranslation() + " ,";
//                    s += "\n";
//                }
//                check.setText(s);
            }
        });
    }
}


