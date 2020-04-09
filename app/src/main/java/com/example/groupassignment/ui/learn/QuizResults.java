package com.example.groupassignment.ui.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.groupassignment.R;

import java.util.ArrayList;

public class QuizResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        Intent intent = getIntent();
        String level = intent.getStringExtra("level");
//        ArrayList<QuizAnswers> results = (ArrayList<QuizAnswers>) getIntent().getSerializableExtra("QuizAnswers");

    }
}
