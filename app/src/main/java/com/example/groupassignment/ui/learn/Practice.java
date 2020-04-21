package com.example.groupassignment.ui.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.groupassignment.DatabaseHelper;
import com.example.groupassignment.MainActivity_Learn;
import com.example.groupassignment.MainActivity_Self_Learn;
import com.example.groupassignment.R;
import com.example.groupassignment.SQLiteHelper;

import java.util.Random;

public class Practice extends AppCompatActivity {

    DatabaseHelper myDb = new DatabaseHelper(this);
    Random random = new Random();
    private int strikes, randomEnglish, randomTranslate, amount, category;
    private boolean correctAnswer, userAnswer;
    private String englishText, translatedText;
    int correct;
    private ImageView strike1, strike2, strike3;
    private ImageButton refresh;
    private TextView englishTF, translatedTF, resultTF, correctTF, selection, yandex;
    private ImageButton trueButton, falseButton;
    private Dialog dialog;
    private Dialog exitDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        strike1 = findViewById(R.id.strike1);
        strike2 = findViewById(R.id.strike2);
        strike3 = findViewById(R.id.strike3);

        refresh = findViewById(R.id.refresh);
        trueButton = findViewById(R.id.trueButton);
        falseButton = findViewById(R.id.falseButton);

        englishTF = findViewById(R.id.englishTF);
        translatedTF = findViewById(R.id.translatedTF);

        resultTF = findViewById(R.id.resultTF);
        correctTF = findViewById(R.id.correctTF);
        selection = findViewById(R.id.selection);

        yandex = findViewById(R.id.yandexCredit2);
        yandex.setMovementMethod(LinkMovementMethod.getInstance());

//        menu = findViewById(R.id.menu);
//        quiz = findViewById(R.id.storeButton);
//        learn = findViewById(R.id.learn);

        Intent intent = getIntent();
        category = intent.getIntExtra("category",0);
        amount = 0;
        if (category != 0) {
            amount = 10;
            this.setTitle(LearnCategories.getCategories().get(category - 1).getCategoryName() + " True or False Practice");
        } else {
            amount = myDb.countUserData();
            this.setTitle("Self-Learn True or False Practice");
        }

        resetUI();
        updateUI();

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUI();
            }
        });

//        quiz.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Practice.this, QuizTest.class);
//                intent.putExtra("category", category);
//                startActivity(intent);
//            }
//        });
//
//        learn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Practice.this, LearnFlashcards.class);
//                intent.putExtra("category",category);
//                startActivity(intent);
//            }
//        });
//
//        menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (category != 0) {
//                    Intent intent1 = new Intent(getApplicationContext(), MainActivity_Learn.class);
//                    startActivity(intent1);
//                } else {
//                    Intent intent1 = new Intent(getApplicationContext(), MainActivity_Self_Learn.class);
//                    startActivity(intent1);
//                }
//            }
//        });
    }

    private void updateUI() {
        randomEnglish = random.nextInt(amount-1) + 1;
        randomTranslate = random.nextInt(myDb.countData()-1) + 1;

        double y = Math.random();
        if (y > 0.5) {
            correctAnswer = true;
        } else {
            correctAnswer = false;
        }
        refresh.setEnabled(false);
        refresh.setAlpha((float) 0.1);

        englishText = myDb.pullData("Expression",category, randomEnglish);
        englishTF.setText(englishText);

        boolean questionMark;
        boolean ellipses;

        questionMark = englishText.contains("?");
        ellipses = englishText.contains("...");

        if (correctAnswer) {
            translatedText = myDb.pullData("Translation", category, randomEnglish);
            translatedTF.setText(translatedText);
        } else {
            translatedText = myDb.pullRandom("Translation", randomTranslate);
            if (category != 0) {
                while (translatedText.contains("?") != questionMark || translatedText.contains("...") != ellipses
                        || randomEnglish == randomTranslate) {
                    randomTranslate = random.nextInt(myDb.countData()-1) + 1;
                    translatedText = myDb.pullRandom("Translation", randomTranslate);
                }
            } else {
                while (randomEnglish == randomTranslate) {
                    randomTranslate = random.nextInt(myDb.countData()-1) + 1;
                    translatedText = myDb.pullRandom("Translation", randomTranslate);
                }
            }
            translatedTF.setText(myDb.pullRandom("Translation", randomTranslate));
        }

        resultTF.setText("");
        correctTF.setText("");
        selection.setText("");

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswer = true;
                if (showResult(userAnswer, correctAnswer)) {
                    correct++;
                }
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswer = false;
                if (showResult(userAnswer, correctAnswer)) {
                    correct++;
                }
            }
        });

        refresh.setEnabled(false);
        refresh.setAlpha((float) 0.1);
        trueButton.setEnabled(true);
        falseButton.setEnabled(true);
        trueButton.setAlpha((float) 1);
        falseButton.setAlpha((float) 1);
    }

    private void updateStrikes(int strikes) {
        switch (strikes) {
            case 1:
                strike1.setVisibility(View.VISIBLE);
                break;
            case 2:
                strike2.setVisibility(View.VISIBLE);
                break;
            case 3:
                strike3.setVisibility(View.VISIBLE);
                practiceOver();
                break;
        }
    }

    private boolean showResult(boolean userAnswer, boolean correctAnswer) {
        selection.setText("You selected "+userAnswer+".");
        trueButton.setEnabled(false);
        trueButton.setAlpha((float) 0.1);
        falseButton.setEnabled(false);
        falseButton.setAlpha((float) 0.1);
        refresh.setEnabled(true);
        refresh.setAlpha((float) 1);
        correctTF.setText("The translation of \""+myDb.pullData("Expression",category, randomEnglish)+"\" is \""+myDb.pullData( "Translation", category, randomEnglish)+"\".");
        if (userAnswer == correctAnswer) {
            resultTF.setText("Correct!");
            resultTF.setTextColor(Color.GREEN);
            return true;
        } else {
            resultTF.setText("Incorrect!");
            resultTF.setTextColor(Color.RED);
            strikes++;
            updateStrikes(strikes);
            return false;
        }
    }

    private void resetUI() {
        correct = 0;
        strikes = 0;
        strike1.setVisibility(View.INVISIBLE);
        strike2.setVisibility(View.INVISIBLE);
        strike3.setVisibility(View.INVISIBLE);
        resultTF.setText("");
        correctTF.setText("");
        selection.setText("");
    }

    private void practiceOver() {
        dialog = new Dialog(this);

        dialog.setContentView(R.layout.practice_game_over);
        TextView grade = dialog.findViewById(R.id.grade1);
        TextView score = dialog.findViewById(R.id.score1);
        TextView coinsIncrease = dialog.findViewById(R.id.coinsIncrease1);
        ImageView reaction = dialog.findViewById(R.id.reaction1);
        Button tryQuiz = dialog.findViewById(R.id.tryQuiz);
        Button menu = dialog.findViewById(R.id.menu1);
        Button retry = dialog.findViewById(R.id.retry1);

        final SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        myDb = new DatabaseHelper(this);

        int coinsEarned = 0;
        int coinsPerQuestion = (int) (Math.floor(correct/5)+1);
        coinsEarned = correct * coinsPerQuestion;
        int coinsCurrrent = Integer.parseInt(sqLiteHelper.getData(SQLiteHelper.COL_4, 1));

        sqLiteHelper.update(1, "Coins", "Coins", coinsCurrrent + coinsEarned);

        if (correct == 0) {
            grade.setText("Unlucky!");
            reaction.setImageResource(R.drawable.sad);
            coinsIncrease.setText("No coins earned.");
            score.setText("You didn't get any correct.");
        } else {
            reaction.setImageResource(R.drawable.happy1);
            score.setText("You got " + correct + " correct.");
            if (correct == 1) {
                grade.setText("Good Try!");
                coinsIncrease.setText("+" + coinsEarned + " coin");
            } else {
                grade.setText("Congratulations!");
                coinsIncrease.setText("+" + coinsEarned + " coins");
            }
        }

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (category != 0) {
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity_Learn.class);
                    startActivity(intent1);
                } else {
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity_Self_Learn.class);
                    startActivity(intent1);
                }
            }
        });

        tryQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), QuizTest.class);
                intent2.putExtra("category", category);
                startActivity(intent2);
            }
        });

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetUI();
                updateUI();
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
    }

    public void onBackPressed() {
        exitDialog = new Dialog(this);

        exitDialog.setContentView(R.layout.exit_dialog);
        Button yes = exitDialog.findViewById(R.id.yes);
        Button no = exitDialog.findViewById(R.id.no);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (category != 0) {
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity_Learn.class);
                    startActivity(intent1);
                } else {
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity_Self_Learn.class);
                    startActivity(intent1);
                }
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitDialog.dismiss();
            }
        });

        exitDialog.show();

        exitDialog.setCanceledOnTouchOutside(false);
        exitDialog.setCancelable(false);
    }

//    @Override
//    protected void onDestroy() {
//        dialog.dismiss();
//        exitDialog.dismiss();
//        super.onDestroy();
//    }
}