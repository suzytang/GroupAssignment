package com.example.groupassignment.ui.learn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.groupassignment.R;

import java.util.ArrayList;

public class QuizResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        Intent intent = getIntent();
        String level = intent.getStringExtra("level");
        this.setTitle(level+" Quiz Results");
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<QuizAnswers> quizAnswers = (ArrayList<QuizAnswers>) args.getSerializable("ARRAYLIST");

        RecyclerView mRecyclerView = findViewById(R.id.rvList);
        mRecyclerView.setHasFixedSize(true);

        //Initialise layoutManager for recyclerView
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Create adapter object
        RecyclerView.Adapter mAdapter = new QuizAdapter(this, quizAnswers);

        //Attach adapter to recyclerView
        mRecyclerView.setAdapter(mAdapter);
    }
}
