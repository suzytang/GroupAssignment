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

        submit = (Button) findViewById(R.id.submit);
        progress = (TextView) findViewById(R.id.progress);
        question = (TextView) findViewById(R.id.question);
        input = (EditText) findViewById(R.id.input);

        right = (TextView) findViewById(R.id.right);
        wrong = (TextView) findViewById(R.id.wrong);

        final ArrayList<Integer> shuffle = new ArrayList<Integer>();
        for (int j = 0; j < 10; j++) {
            shuffle.add(j);
        }
        Collections.shuffle(shuffle);

        progress .setText((count+1)+"/10");
        question.setText(LearnData.getLearnData().get(10 * (position - 1) + shuffle.get(0)).getText());

        final ArrayList<Integer> score = new ArrayList<Integer>();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count != 10) {
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
                        score.add(1);
                    } else {
                        score.add(0);
                    }

                    count++;
                    question.setText(LearnData.getLearnData().get(10 * (position - 1) + shuffle.get(count)).getText());
                    progress.setText((count + 1) + "/10");

                    if (count == 9) {
                        submit.setText("Complete");
                        count++;
                    }
                } else {
                    Intent intent = new Intent(QuizTest.this, QuizResults.class);
                    intent.putExtra("level", level);
                    //                    intent.putIntegerArrayListExtra("score",score);
                    //                    intent.putExtra("questions",shuffle);
                    startActivity(intent);
                }
            }
        });
    }
}
