package com.example.groupassignment.ui.learn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.groupassignment.R;
import com.wajahatkarim3.easyflipview.EasyFlipView;

public class LearnFlashcards extends AppCompatActivity {

    //EasyFlipView easyFlipView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_flashcards);

        Intent intent = getIntent();
        final String level = intent.getStringExtra("placeholder");

        final EasyFlipView easyFlipView = (EasyFlipView) findViewById(R.id.easyFlipView);
        easyFlipView.setFlipDuration(400);
        easyFlipView.setFlipEnabled(true);

        findViewById(R.id.frontCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LearnFlashcards.this, "Front Card", Toast.LENGTH_SHORT).show();
                easyFlipView.flipTheView();
            }
        });

        findViewById(R.id.backCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LearnFlashcards.this, "Back Card", Toast.LENGTH_SHORT).show();
                easyFlipView.flipTheView();
            }
        });

        easyFlipView.setOnFlipListener(new EasyFlipView.OnFlipAnimationListener() {
            @Override
            public void onViewFlipCompleted(EasyFlipView easyFlipView, EasyFlipView.FlipState newCurrentSide) {
                Toast.makeText(LearnFlashcards.this,
                        "Flip Completed! New Side is: " + newCurrentSide, Toast.LENGTH_LONG).show();
            }
        });



        final EasyFlipView easyFlipView2 = (EasyFlipView) findViewById(R.id.easyFlipView);
        easyFlipView2.setFlipDuration(400);
        easyFlipView2.setToHorizontalType();
        easyFlipView2.setFlipTypeFromLeft();

    }
}
