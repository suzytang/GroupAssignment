package com.example.groupassignment.ui.selflearn;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment.DatabaseHelper;
import com.example.groupassignment.R;
import com.example.groupassignment.ui.learn.LearnFlashcards;
import com.example.groupassignment.ui.learn.QuizTest;

import java.util.ArrayList;

public class SelfLearnFragment extends Fragment {

    private SelfLearnViewModel selfLearnViewModel;

    DatabaseHelper myDb;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        selfLearnViewModel =
                ViewModelProviders.of(this).get(SelfLearnViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_selflearn, container, false);
        /*final TextView textView = root.findViewById(R.id.text_learn);
        learnViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        myDb = new DatabaseHelper(getActivity());

        Button translator = root.findViewById(R.id.translatorButton);
        Button learn = root.findViewById(R.id.learn);
        final Button quiz = root.findViewById(R.id.quiz);

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
                if (myDb.countUserData()==0) {
                    Toast.makeText(getActivity(), "Error: need to store at least one expression using the translator first", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(getActivity(), LearnFlashcards.class);
                    intent.putExtra("category", 0);
                    startActivity(intent);
                }
            }
        });

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myDb.countUserData()==0) {
                    Toast.makeText(getActivity(), "Error: need to store at least one expression using the translator first", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(getActivity(), QuizTest.class);
                    intent.putExtra("category", 0);
                    startActivity(intent);
                }
            }
        });
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

//    public boolean empty(DatabaseHelper dB) {
//        try {
//            int i  = dB.rowsUserData();
//            if (i > 0) {
//                return false;
//            } else {
//                return true;
//            }
//        }
//        catch(Exception e) {
//            return true;
//        }
//    }

}
