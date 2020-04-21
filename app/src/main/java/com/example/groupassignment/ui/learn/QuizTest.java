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

    // Declare variables
    private Button submit;
    private TextView progress, question, yandex;
    private EditText input;
    private int category, i, score, amount;
    private DatabaseHelper myDb = new DatabaseHelper(this);
    private ArrayList<QuizAnswers> quizAnswers;
    private ArrayList<Integer> shuffle;
    private Dialog dialog;
    private Dialog exitDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_test);

        // Get category
        Intent intent = getIntent();
        category = intent.getIntExtra("category", 0);

        // Set title
        if (category != 0) {
            this.setTitle(LearnCategories.getCategories().get(category - 1).getCategoryName() + " Quiz");
        } else {
            this.setTitle("Self-Learn Quiz");
        }

        // Link with XML
        submit = findViewById(R.id.submit);
        progress = findViewById(R.id.progress);
        question = findViewById(R.id.question);
        input = findViewById(R.id.input);
        // Credit yandex API
        yandex = findViewById(R.id.yandexCredit4);
        yandex.setMovementMethod(LinkMovementMethod.getInstance());

        // Start quiz
        startQuiz();
    }

    private void startQuiz() {
        // Create new ArrayList to store quiz answers
        quizAnswers = new ArrayList<QuizAnswers>();

        // Initialise variables
        amount = 0;
        i = 1;
        score = 0;

        // Get number of expressions for quiz
        if (category != 0) {
            amount = 10;
        } else {
            amount = myDb.countUserData();
        }

        // Shuffle positions of each expression
        shuffle = new ArrayList<Integer>();
        for (int j = 1; j < amount+1; j++) {
            shuffle.add(j);
        }
        Collections.shuffle(shuffle);

        // Set text with respective information
        submit.setText("Submit");
        progress.setText(i + "/"+amount);
        question.setText(myDb.pullData("Expression",category,shuffle.get(i-1)));

        // User submits answer
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i < amount+1) {
                    // Get expression
                    String expression =
                            myDb.pullData("Expression",category,shuffle.get(i-1));
                    // Get correct translation
                    String translation =
                            myDb.pullData("Translation",category,shuffle.get(i-1));
                    // Format to ensure no issues with single quotations or cases
                    String userText =
                            input.getText()
                                    .toString().toLowerCase().replace("’", "'");
                    String matchTranslation =
                            translation.toLowerCase().replace("’", "'");

                    // Check if user input matches correct translation;
                    // return 0 = incorrect, 1 = correct and has previously answered question,
                    // 2 = correct for the first time
                    if (userText.equals(matchTranslation)) {
                        // Check if this question has been answered correctly before
                        if (myDb.answered(category,shuffle.get(i-1))) {
                            score = 1;
                        } else {
                            score = 2;
                            myDb.setAnswered(category,shuffle.get(i-1));
                        }
                    } else {
                        score = 0;
                    }

                    // Add question, expression, result, input and correct translation
                    // to Array List
                    QuizAnswers answer = new QuizAnswers(
                            i, expression, score, input.getText().toString(), translation);
                    quizAnswers.add(answer);

                    // Increment
                    i++;

                    // If final question change submit to complete
                    if (i == amount) {
                        submit.setText("Complete");
                    }

                    // If there are still expressions to be tested, set new question,
                    // else start quiz over dialog and clear text
                    if (i <= amount) {
                        question.setText(
                                myDb.pullData("Expression",category,shuffle.get(i-1)));
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
        // Create dialog
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.quiz_game_over);

        // Link to XMl
        TextView grade = dialog.findViewById(R.id.grade);
        TextView score = dialog.findViewById(R.id.score);
        TextView coinsIncrease = dialog.findViewById(R.id.coinsIncrease);
        TextView levelUp = dialog.findViewById(R.id.levelUp);
        ImageView reaction = dialog.findViewById(R.id.reaction);
        Button viewResults = dialog.findViewById(R.id.viewResults);
        Button menu = dialog.findViewById(R.id.menu);
        Button retry = dialog.findViewById(R.id.retry);

        // SQLiteHelper & DatabaseHelper
        final SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        myDb = new DatabaseHelper(this);

        // Initalise variables
        int total = 0;
        int coinsEarned = 0;

        // Get current coins
        int coinsCurrrent = Integer.parseInt(sqLiteHelper.getData(SQLiteHelper.COL_4, 1));

        // Calculate total score and coins earned
        for (int i = 0; i < quizAnswers.size(); i++) {
            if (quizAnswers.get(i).getScore() > 0) {
                total++;
                // If correct for the first time, gain 20 coins
                if (quizAnswers.get(i).getScore() == 2) {
                    coinsEarned += 20;
                } else {
                    // If previously answered correctly, gain 5 coins
                    coinsEarned += 5;
                }
            }
        }

        // Update coins
        sqLiteHelper.update(1, "Coins", "Coins", coinsCurrrent + coinsEarned);

        // Display coins earned
        if (coinsEarned == 0) {
            coinsIncrease.setText("No coins earned.");
        } else {
            coinsIncrease.setText("+" + coinsEarned + " coins");
        }
        score.setText("You scored "+total+" out of "+quizAnswers.size()+".");

        // Calculate grade and set text
        double percentage = Double.valueOf(total)/Double.valueOf(quizAnswers.size());
        if (percentage < 0.5) {
            // Set text for fail
            grade.setText("Failed!");
            grade.setTextColor(Color.RED);
            reaction.setImageResource(R.drawable.sad);
        } else if (percentage == 1) {
            if (category != 0) {
                // Check if user achieved 100% before for this category
                if (myDb.completed(category)) {
                    grade.setText("Perfect Again!");
                    reaction.setImageResource(R.drawable.happy);
                } else {
                    // If first time achieving 100%, level up
                    grade.setText("Level Up!");
                    // Get current level
                    int currentLevel = (int) sqLiteHelper.getPetTime("LVL");
                    String newLevel = String.valueOf(currentLevel + 1);
                    // Update level
                    sqLiteHelper.updatePetData("LVL", newLevel, 1);
                    levelUp.setText(newLevel);
                    myDb.setCompleted(category);
                    reaction.setImageResource(R.drawable.trophy);
                }
            } else {
                // Set text for 100% in self learn
                grade.setText("Perfect!");
                reaction.setImageResource(R.drawable.happy);
            }
        } else {
            // Set text for pass
            grade.setText("Passed!");
            grade.setTextColor(Color.GREEN);
            reaction.setImageResource(R.drawable.happy);
        }

        // Button to go back to menu
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

        // Button to view results
        viewResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), QuizResults.class);
                Bundle args = new Bundle();
                // Pass arraylist with quiz answers and categories
                args.putSerializable("arraylist", quizAnswers);
                intent2.putExtra("bundle", args);
                intent2.putExtra("category", category);
                startActivity(intent2);
            }
        });

        // Button to retry
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

    // When back button is pressed, dialog shows
    public void onBackPressed() {

        exitDialog = new Dialog(this);
        exitDialog.setContentView(R.layout.exit_dialog);

        // Declare buttons and link XML
        Button yes = exitDialog.findViewById(R.id.yes);
        Button no = exitDialog.findViewById(R.id.no);

        // If yes, return to menu
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

        // If no, dismiss dialog and return to current page
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitDialog.dismiss();
            }
        });

        // Show dialog
        exitDialog.show();

        // Prevent users to cancel by touching outside or pressing back again
        exitDialog.setCanceledOnTouchOutside(false);
        exitDialog.setCancelable(false);
    }
}