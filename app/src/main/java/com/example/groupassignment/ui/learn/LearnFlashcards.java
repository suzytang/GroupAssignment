package com.example.groupassignment.ui.learn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.groupassignment.LearnData;
import com.example.groupassignment.MainActivity;
import com.example.groupassignment.R;
import com.example.groupassignment.TranslateRequest;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.concurrent.ExecutionException;

public class LearnFlashcards extends AppCompatActivity {

    //EasyFlipView easyFlipView;
    TextView levelText;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_flashcards);

        TextView levelText = (TextView) findViewById(R.id.category);
        final Button button = (Button) findViewById(R.id.button);
        final TextView question = (TextView) findViewById(R.id.question);
        final TextView frontText = (TextView) findViewById(R.id.frontText);
        final TextView backText = (TextView) findViewById(R.id.backText);


        Intent intent = getIntent();
        final String level = intent.getStringExtra("level");
        levelText.setText("Level "+level);
        question.setText("Question "+(i+1));
        final int position = Integer.parseInt(level);
        int record = 0;

        final EasyFlipView easyFlipView = (EasyFlipView) findViewById(R.id.easyFlipView);
        easyFlipView.setFlipDuration(500);
        easyFlipView.setFlipEnabled(true);
        easyFlipView.setAutoFlipBack(false);

        frontText.setText(LearnData.getLearnData().get(10*(position-1)+i).getText());
        TranslateRequest tR = new TranslateRequest();
        String result = "";
        try {
            result = tR.execute(LearnData.getLearnData().get(10*(position-1)+i).getText()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        backText.setText(result);

        findViewById(R.id.frontCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(LearnFlashcards.this, "Front Card", Toast.LENGTH_SHORT).show();
                easyFlipView.flipTheView();
            }
        });

        findViewById(R.id.backCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(LearnFlashcards.this, "Back Card", Toast.LENGTH_SHORT).show();
                easyFlipView.flipTheView();
            }
        });

        /*easyFlipView.setOnFlipListener(new EasyFlipView.OnFlipAnimationListener() {
            @Override
            public void onViewFlipCompleted(EasyFlipView easyFlipView, EasyFlipView.FlipState newCurrentSide) {
                Toast.makeText(LearnFlashcards.this,
                        "Flip Completed! New Side is: " + newCurrentSide, Toast.LENGTH_LONG).show();
            }
        });*/

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                if (i < 10) {
                    frontText.setText(LearnData.getLearnData().get(10 * (position - 1) + i).getText());
                    TranslateRequest tR = new TranslateRequest();
                    String result = "";
                    try {
                        result = tR.execute(LearnData.getLearnData().get(10 * (position - 1) + i).getText()).get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    backText.setText(result);
                    question.setText("Question "+(i+1));
                    if (i == 9) {
                        button.setText("Finish");
                    }
                } else if (i == 10) {
//                    Intent intent = new Intent(this, MainActivity.class);
//                    startActivity(intent);
                }
            }
        });


        final EasyFlipView easyFlipView2 = (EasyFlipView) findViewById(R.id.easyFlipView);
        easyFlipView2.setFlipDuration(500);
        easyFlipView2.setToHorizontalType();
        easyFlipView2.setFlipTypeFromLeft();

//        public void updateText(int number1,int number2) {
//            frontText.setText(LearnData.getLearnData().get(10 * (number1 - 1) + number2).getText());
//            TranslateRequest tR1 = new TranslateRequest();
//            String result1 = "";
//            try {
//                result1 = tR1.execute(LearnData.getLearnData().get(10 * (number1 - 1) + number2).getText()).get();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            backText.setText(result1);
//            question.setText("Question "+(number2+1));
//            if (i == 9) {
//                button.setText("Finish");
//            }
//        }
    }



}
