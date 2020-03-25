package com.example.groupassignment.ui.learn;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.groupassignment.R;

public class LearnQuiz extends AppCompatActivity {

    private TextView levelText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learnquiz);

        Intent intent = getIntent();
        final String level = intent.getStringExtra("level");

        TextView levelText = (TextView) findViewById(R.id.levelText);
        levelText.setText(level);

    }

}
