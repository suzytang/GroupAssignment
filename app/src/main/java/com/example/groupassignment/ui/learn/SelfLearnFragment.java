package com.example.groupassignment.ui.learn;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.groupassignment.DatabaseHelper;
import com.example.groupassignment.R;

public class SelfLearnFragment extends Fragment {

    // Declare variables
    private DatabaseHelper myDb;
    private ImageView lock2, lock3, lock4;
    private ImageButton translator, learn, practice, quiz;
    private TextView storedWords, text2, text3, text4, lockText2, lockText3, lockText4;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_selflearn, container, false);

        // Initialise database
        myDb = new DatabaseHelper(getActivity());

        // Link to XML
        translator = root.findViewById(R.id.function1);
        learn = root.findViewById(R.id.function2);
        practice = root.findViewById(R.id.function3);
        quiz = root.findViewById(R.id.function4);
        storedWords = root.findViewById(R.id.storedWords);
        lockText2 = root.findViewById(R.id.lockText2);
        lockText3 = root.findViewById(R.id.lockText3);
        lockText4 = root.findViewById(R.id.lockText4);
        lock2 = root.findViewById(R.id.lock2);
        lock3 = root.findViewById(R.id.lock3);
        lock4 = root.findViewById(R.id.lock4);
        text2 = root.findViewById(R.id.text2);
        text3 = root.findViewById(R.id.text3);
        text4 = root.findViewById(R.id.text4);

        // Lock learn, practice and quiz function
        lock();

        // Unlock learn, practice and quiz function based on number of stored words
        unlock();

        // Show number of stored words
        final int words = myDb.countUserData();
        storedWords.setText("Stored words: "+words);

        // Button to go to translator
        translator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Translator.class);
                startActivity(intent);
            }
        });

        // Button to go to learn
        learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LearnFlashcards.class);
                    intent.putExtra("category", 0);
                    startActivity(intent);

            }
        });

        // Button to go to practice
        practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Practice.class);
                    intent.putExtra("category", 0);
                    startActivity(intent);

            }
        });

        // Button to go to quiz
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), QuizTest.class);
                    intent.putExtra("category", 0);
                    startActivity(intent);

            }
        });

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Lock learn, practice and quiz
    // Show lock image and text and disable image button for all functionalities
    private void lock() {
        learn.setAlpha((float) 0.1);
        learn.setEnabled(false);
        text2.setAlpha((float) 0.1);
        practice.setAlpha((float) 0.1);
        practice.setEnabled(false);
        text3.setAlpha((float) 0.1);
        quiz.setAlpha((float) 0.1);
        quiz.setEnabled(false);
        text4.setAlpha((float) 0.1);
    }

    // Unlock learn, practice or quiz based on number of stored words
    // Remove lock image and text and enable image button for respective functionality
    private void unlock() {
        // Unlock learn
        if (myDb.countUserData() >= 3) {
            learn.setAlpha((float) 1);
            learn.setEnabled(true);
            text2.setAlpha((float) 1);
            lockText2.setVisibility(View.GONE);
            lock2.setVisibility(View.GONE);
        }
        // Unlock practice
        if (myDb.countUserData() >= 10) {
            practice.setAlpha((float) 1);
            practice.setEnabled(true);
            text3.setAlpha((float) 1);
            lockText3.setVisibility(View.GONE);
            lock3.setVisibility(View.GONE);
        }
        // Unlock quiz
        if (myDb.countUserData() >= 5) {
            quiz.setAlpha((float) 1);
            quiz.setEnabled(true);
            text4.setAlpha((float) 1);
            lockText4.setVisibility(View.GONE);
            lock4.setVisibility(View.GONE);
        }
    }
}