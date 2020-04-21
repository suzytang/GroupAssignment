package com.example.groupassignment.ui.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.groupassignment.DatabaseHelper;
import com.example.groupassignment.MainActivity_Learn;
import com.example.groupassignment.MainActivity_Self_Learn;
import com.example.groupassignment.R;
import com.example.groupassignment.SQLiteHelper;

import java.util.ArrayList;
import java.util.Collections;

public class QuizTest extends AppCompatActivity {

    private Button submit;
    private TextView progress, question, yandex;
    private EditText input;
    int category, i, score, amount;
    DatabaseHelper myDb = new DatabaseHelper(this);
    ArrayList<QuizAnswers> quizAnswers;
    private Dialog dialog;
    private Dialog exitDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_test);
        Intent intent = getIntent();
        category = intent.getIntExtra("category", 0);
        if (category != 0) {
            this.setTitle(LearnCategories.getCategories().get(category - 1).getCategoryName() + " Quiz");
        } else {
            this.setTitle("Self-Learn Quiz");
        }


        submit = findViewById(R.id.submit);
        progress = findViewById(R.id.progress);
        question = findViewById(R.id.question);
        input = findViewById(R.id.input);
        yandex = findViewById(R.id.yandexCredit4);
        yandex.setMovementMethod(LinkMovementMethod.getInstance());

        startQuiz();
    }

    private void startQuiz() {
        quizAnswers = new ArrayList<QuizAnswers>();
        amount = 0;
        i = 1;
        score = 0;
        submit.setText("Submit");
        if (category != 0) {
            amount = 10;
        } else {
            amount = myDb.countUserData();
        }
        final ArrayList<Integer> shuffle = new ArrayList<Integer>();
        for (int j = 1; j < amount+1; j++) {
            shuffle.add(j);
        }
        Collections.shuffle(shuffle);

        progress.setText(i + "/"+amount);
        question.setText(myDb.pullData("Expression",category,shuffle.get(i-1)));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i < amount+1) {
                    String expression = myDb.pullData("Expression",category,shuffle.get(i-1));
                    String translation = myDb.pullData("Translation",category,shuffle.get(i-1));
                    String userText = input.getText().toString().toLowerCase().replace("’", "'");
                    String matchTranslation = translation.toLowerCase().replace("’", "'");

                    if (userText.equals(matchTranslation)) {
                        if (myDb.answered(category,shuffle.get(i-1))) {
                            score = 1;
                        } else {
                            score = 2;
                            myDb.setAnswered(category,shuffle.get(i-1));
                        }
                    } else {
                        score = 0;
                    }

                    QuizAnswers answer = new QuizAnswers(i, expression, score, input.getText().toString(), translation);
                    quizAnswers.add(answer);

                    i++;
                    if (i == amount) {
                        submit.setText("Complete");
                    }
                    if (i < amount+1) {
                        question.setText(myDb.pullData("Expression",category,shuffle.get(i-1)));
                        progress.setText((i) + "/"+ amount);
                        input.getText().clear();
                    } else {
                        quizOver();
                        input.getText().clear();
                    }
                }
            }
        });
    }
    private void quizOver() {
        dialog = new Dialog(this);

        dialog.setContentView(R.layout.quiz_game_over);
        TextView grade = dialog.findViewById(R.id.grade);
        TextView score = dialog.findViewById(R.id.score);
        TextView coinsIncrease = dialog.findViewById(R.id.coinsIncrease);
        TextView levelUp = dialog.findViewById(R.id.levelUp);
        ImageView reaction = dialog.findViewById(R.id.reaction);
        Button viewResults = dialog.findViewById(R.id.viewResults);
        Button menu = dialog.findViewById(R.id.menu);
        Button retry = dialog.findViewById(R.id.retry);

        final SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        myDb = new DatabaseHelper(this);

        int total = 0;
        int coinsEarned = 0;
        int coinsCurrrent = Integer.parseInt(sqLiteHelper.getData(SQLiteHelper.COL_4, 1));

        for (int i = 0; i < quizAnswers.size(); i++) {
            if (quizAnswers.get(i).getScore() > 0) {
                total++;
                if (quizAnswers.get(i).getScore() == 2) {
                    coinsEarned += 20;
                } else {
                    coinsEarned += 5;
                }
            }
        }

        sqLiteHelper.update(1, "Coins", "Coins", coinsCurrrent + coinsEarned);
        if (coinsEarned == 0) {
            coinsIncrease.setText("No coins earned.");
        } else {
            coinsIncrease.setText("+" + coinsEarned + " coins");
        }
        score.setText("You scored "+total+" out of "+quizAnswers.size()+".");

        double percentage = Double.valueOf(total)/Double.valueOf(quizAnswers.size());

        if (percentage < 0.5) {
            grade.setText("Failed!");
            grade.setTextColor(Color.RED);
            reaction.setImageResource(R.drawable.sad);
        } else if (percentage == 1) {
            if (category != 0) {
                if (myDb.completed(category)) {
                    grade.setText("Perfect Again!");
                    reaction.setImageResource(R.drawable.happy);
                } else {
                    grade.setText("Level Up!");
                    int currentLevel = (int) sqLiteHelper.getPetTime("LVL");
                    String newLevel = String.valueOf(currentLevel + 1);
                    sqLiteHelper.updatePetData("LVL", newLevel, 1);
                    levelUp.setText(newLevel);
                    myDb.setCompleted(category);
                    reaction.setImageResource(R.drawable.trophy);
                }
            } else {
                grade.setText("Perfect!");
                reaction.setImageResource(R.drawable.happy);
            }
        } else {
            grade.setText("Passed!");
            grade.setTextColor(Color.GREEN);
            reaction.setImageResource(R.drawable.happy);
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

        viewResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), QuizResults.class);
                Bundle args = new Bundle();
                args.putSerializable("arraylist", quizAnswers);
                intent2.putExtra("bundle", args);
                intent2.putExtra("category", category);
                startActivity(intent2);
            }
        });

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
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

}