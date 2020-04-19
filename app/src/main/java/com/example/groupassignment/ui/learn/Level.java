package com.example.groupassignment.ui.learn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.groupassignment.R;
import com.google.android.material.tabs.TabLayout;

public class Level extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_learn_level);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        Intent intent = getIntent();
        int level = intent.getIntExtra("level",0);

        Bundle bundleLearn = new Bundle();
        LearnFrag learnFrag = new LearnFrag();
        bundleLearn.putInt("level", level);
        learnFrag.setArguments(bundleLearn);

        Bundle bundlePractise = new Bundle();
        PractiseFrag practiseFrag = new PractiseFrag();
        bundlePractise.putInt("level", level);
        practiseFrag.setArguments(bundleLearn);

        Bundle bundleQuiz = new Bundle();
        QuizFrag quizFrag = new QuizFrag();
        bundleQuiz.putInt("level", level);
        quizFrag.setArguments(bundleLearn);
    }
}
