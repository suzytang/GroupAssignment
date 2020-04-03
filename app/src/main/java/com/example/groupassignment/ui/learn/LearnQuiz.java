package com.example.groupassignment.ui.learn;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.groupassignment.R;

public class LearnQuiz extends AppCompatActivity {

    private TextView levelText;
    private Button learnButton;
    private Button quizButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learnquiz);

        Intent intent = getIntent();
        final String level = intent.getStringExtra("level");

        TextView levelText = (TextView) findViewById(R.id.levelText);
        Button learnButton = (Button) findViewById(R.id.learnButton);
        Button quizButton = (Button) findViewById(R.id.quizButton);
        levelText.setText(level);

        learnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLearnFlashCards();

            }
        });


    }
    public void openLearnFlashCards(){
        Intent intent = new Intent(this, LearnFlashcards.class);
        intent.putExtra("placeholder", "Level placeholder");
        startActivity(intent);

        startActivity(intent);

    }

}
