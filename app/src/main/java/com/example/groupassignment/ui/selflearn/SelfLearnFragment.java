package com.example.groupassignment.ui.selflearn;

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
import androidx.lifecycle.ViewModelProviders;

import com.example.groupassignment.DatabaseHelper;
import com.example.groupassignment.R;
import com.example.groupassignment.ui.learn.LearnFlashcards;
import com.example.groupassignment.ui.learn.Practice;
import com.example.groupassignment.ui.learn.QuizTest;

public class SelfLearnFragment extends Fragment {

    private DatabaseHelper myDb;
    private ImageView lock2, lock3, lock4;
    private ImageButton translator, learn, practice, quiz;
    private TextView storedWords, text2, text3, text4, lockText2, lockText3, lockText4;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_selflearn, container, false);

        myDb = new DatabaseHelper(getActivity());

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

        lock();
        unlock();

        final int words = myDb.countUserData();
        storedWords.setText("Stored words: "+words);

        translator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Translator.class);
                startActivity(intent);
            }
        });

        learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LearnFlashcards.class);
                    intent.putExtra("category", 0);
                    startActivity(intent);

            }
        });

        practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Practice.class);
                    intent.putExtra("category", 0);
                    startActivity(intent);

            }
        });

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

    private void unlock() {
        if (myDb.countUserData() >= 3) {
            learn.setAlpha((float) 1);
            learn.setEnabled(true);
            text2.setAlpha((float) 1);
            lockText2.setVisibility(View.GONE);
            lock2.setVisibility(View.GONE);
        }
        if (myDb.countUserData() >= 10) {
            practice.setAlpha((float) 1);
            practice.setEnabled(true);
            text3.setAlpha((float) 1);
            lockText3.setVisibility(View.GONE);
            lock3.setVisibility(View.GONE);
        }
        if (myDb.countUserData() >= 5) {
            quiz.setAlpha((float) 1);
            quiz.setEnabled(true);
            text4.setAlpha((float) 1);
            lockText4.setVisibility(View.GONE);
            lock4.setVisibility(View.GONE);
        }
    }
}