package com.example.groupassignment.ui.learn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.groupassignment.MainActivity_Learn;
import com.example.groupassignment.MainActivity_Self_Learn;
import com.example.groupassignment.R;

import java.util.ArrayList;

public class QuizResults extends AppCompatActivity {
    private int category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        // Get category
        Intent intent = getIntent();
        category = intent.getIntExtra("category", 0);

        // Set title
        if (category == 0) {
            this.setTitle("Self-Learn Quiz Results");
        } else {
            this.setTitle(LearnCategories.getCategories().get(category - 1).getCategoryName() + " Quiz Results");
        }

        // Get quiz answers array list
        Bundle args = intent.getBundleExtra("bundle");
        ArrayList<QuizAnswers> quizAnswers = (ArrayList<QuizAnswers>) args.getSerializable("arraylist");

        // Initialise recyclerView
        RecyclerView mRecyclerView = findViewById(R.id.rvList);
        mRecyclerView.setHasFixedSize(true);

        // Initialise layoutManager for recyclerView
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Create adapter object
        RecyclerView.Adapter mAdapter = new QuizAdapter(this, quizAnswers);

        // Attach adapter to recyclerView
        mRecyclerView.setAdapter(mAdapter);
    }

    // On back pressed, return to menu
    @Override
    public void onBackPressed() {
        if (category != 0) {
            // Return to learn
            Intent intent1 = new Intent(getApplicationContext(), MainActivity_Learn.class);
            startActivity(intent1);
        } else {
            // Return to self-learn
            Intent intent1 =
                    new Intent(getApplicationContext(), MainActivity_Self_Learn.class);
            startActivity(intent1);
        }
    }
}
