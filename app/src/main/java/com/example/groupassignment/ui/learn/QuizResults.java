package com.example.groupassignment.ui.learn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.groupassignment.MainActivity_Learn;
import com.example.groupassignment.R;

import java.util.ArrayList;

public class QuizResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        Intent intent = getIntent();
        int category = intent.getIntExtra("category", 0);

        if (category == 0) {
            this.setTitle("Self-Learn Quiz Results");
        } else {
            this.setTitle(LearnCategories.getCategories().get(category - 1).getCategoryName() + " Quiz Results");
        }

        Bundle args = intent.getBundleExtra("bundle");
        ArrayList<QuizAnswers> quizAnswers = (ArrayList<QuizAnswers>) args.getSerializable("arraylist");

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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity_Learn.class);
        startActivity(intent);
    }
}
