package com.example.groupassignment.ui.learn;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.groupassignment.R;

public class LearnFlashcards extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_flashcards);

        Intent intent = getIntent();
        final String level = intent.getStringExtra("placeholder");
    }
}
