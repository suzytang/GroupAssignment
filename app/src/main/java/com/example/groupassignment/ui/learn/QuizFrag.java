package com.example.groupassignment.ui.learn;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.groupassignment.DatabaseHelper;
import com.example.groupassignment.R;

import java.util.ArrayList;
import java.util.Collections;

public class QuizFrag extends Fragment {

    Button submit;
    TextView progress;
    TextView question;
    EditText input;

    DatabaseHelper myDb = new DatabaseHelper(getActivity());

    int i = 1;
    int score = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.activity_quiz_test, container, false);
        final DatabaseHelper myDb = new DatabaseHelper(getActivity());

        //Intent intent = getIntent();
        //final int level = intent.getIntExtra("level", 0);

        //final int level = getArguments().getInt("level");
        final int level = 2;
        final String table = "learn_table";

        //final String table = intent.getStringExtra("table");
        if (table.equals("learn_table")) {
            getActivity().setTitle(LearnCategories.getCategories().get(level - 1).getCategoryName() + " Quiz");
        } else {
            getActivity().setTitle("Self-Learn Quiz");
        }


        submit = root.findViewById(R.id.submit);
        progress = root.findViewById(R.id.progress);
        question = root.findViewById(R.id.question);
        input = root.findViewById(R.id.input);

        final ArrayList<QuizAnswers> quizAnswers = new ArrayList<QuizAnswers>();
        int amount = 0;
        if (table.equals("learn_table")) {
            amount = 10;
        } else {
            amount = myDb.rowsUserData();
        }
        final ArrayList<Integer> shuffle = new ArrayList<Integer>();
        for (int j = 1; j < amount+1; j++) {
            shuffle.add(j);
        }
        Collections.shuffle(shuffle);

        progress.setText(i + "/"+amount);
        question.setText(myDb.pullData(table, "Expression",level,shuffle.get(i-1)));

        final int finalAmount = amount;
        final int finalAmount1 = amount;
        final int finalAmount2 = amount;

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i < finalAmount + 1) {
                    String translation = myDb.pullData(table, "Translation", level, shuffle.get(i - 1));

                    if (input.getText().toString().toLowerCase().equals(translation.toLowerCase())) {
                        score = 1;
                    } else {
                        score = 0;
                    }
                    QuizAnswers answer = new QuizAnswers(i, myDb.pullData(table, "Expression", level, shuffle.get(i - 1)), score, input.getText().toString(), translation);
                    quizAnswers.add(answer);
                    i++;
                    if (i == finalAmount1 - 1) {
                        submit.setText("Complete");
                    }
                    if (i < finalAmount1 + 1) {
                        question.setText(myDb.pullData(table, "Expression", level, shuffle.get(i - 1)));
                        progress.setText((i) + "/" + finalAmount2);
                        input.getText().clear();
                    } else {
                        Intent intent = new Intent(getActivity(), QuizSummary.class);
                        Bundle args = new Bundle();
                        args.putSerializable("arraylist", quizAnswers);
                        intent.putExtra("bundle", args);
                        intent.putExtra("level", level);
                        startActivity(intent);
                    }
                }



            }
        });
        return root;
    }
}
