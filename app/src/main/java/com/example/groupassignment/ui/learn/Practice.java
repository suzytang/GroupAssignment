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
    // DatabaseHelper for queries
    DatabaseHelper myDb = new DatabaseHelper(this);
    // Random to retrieve random number
    Random random = new Random();

    // Declare variables
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

        //  Link with XML
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

        // Credit yandex API
        yandex = findViewById(R.id.yandexCredit2);
        yandex.setMovementMethod(LinkMovementMethod.getInstance());

        amount = 0;

        // Get category
        Intent intent = getIntent();
        category = intent.getIntExtra("category",0);

        // Get number of words to be tested
        if (category != 0) {
            amount = 10;
            this.setTitle(LearnCategories.getCategories().get(category - 1).getCategoryName()
                    + " True or False Practice");
        } else {
            amount = myDb.countUserData();
            this.setTitle("Self-Learn True or False Practice");
        }

        // Clear UI
        resetUI();

        // UpdateUI on Create
        updateUI();

        // Update UI after refresh button is clicked
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUI();
            }
        });
    }

    private void updateUI() {
        // Get random english expression
        randomEnglish = random.nextInt(amount-1) + 1;
        // Get random translated expression
        randomTranslate = random.nextInt(myDb.countData()-1) + 1;

        // Get random number between 0 and 1
        double y = Math.random();
        // Set the correct answer;
        // this is done to ensure there's an even probability of true or false
        if (y > 0.5) {
            correctAnswer = true;
        } else {
            correctAnswer = false;
        }
        refresh.setEnabled(false);
        refresh.setAlpha((float) 0.1);

        // Set random english text
        englishText = myDb.pullData("Expression",category, randomEnglish);
        englishTF.setText(englishText);

        // Check whether text contains ellipses or question marks;
        // this is as punctuation makes it too easy to cheat
        boolean questionMark;
        boolean ellipses;
        questionMark = englishText.contains("?");
        ellipses = englishText.contains("...");

        // If the answer is true, set translated text to be the translation
        if (correctAnswer) {
            translatedText = myDb.pullData("Translation", category, randomEnglish);
            translatedTF.setText(translatedText);
        } else {
            // If the answer is false, set translated text to be a different translation
            translatedText = myDb.pullRandom("Translation", randomTranslate);
            if (category != 0) {
                // If this is from learn,
                // loop until it returns a translation with the same punctuation
                // but is a different expression
                while (translatedText.contains("?") != questionMark
                        || translatedText.contains("...") != ellipses
                        || randomEnglish == randomTranslate) {
                    randomTranslate = random.nextInt(myDb.countData()-1) + 1;
                    translatedText = myDb.pullRandom("Translation", randomTranslate);
                }
            } else {
                // If this is from self-learn,
                // due to the variety of ways an expression is stored,
                // the loop only ensures it is not the same as the english text
                while (randomEnglish == randomTranslate) {
                    randomTranslate = random.nextInt(myDb.countData()-1) + 1;
                    translatedText = myDb.pullRandom("Translation", randomTranslate);
                }
            }
            // Set random translated text
            translatedTF.setText(myDb.pullRandom("Translation", randomTranslate));
        }

        // Clear text views which show results
        clearResultText();
        // Changes button appearance to enable true and false but disable refresh
        answerUI();

        // On click listener for true button
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswer = true;
                // Method checks whether user is correct and updates UI to show result
                // If user answers correctly, number of correct increases
                if (showResult(userAnswer, correctAnswer)) {
                    correct++;
                }
            }
        });

        // On click listener for false button
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Method checks whether user is correct and updates UI to show result
                // If user answers correctly, number of correct increases
                userAnswer = false;
                if (showResult(userAnswer, correctAnswer)) {
                    correct++;
                }
            }
        });

    }

    // Check answer and show result by updating respective textviews
    private boolean showResult(boolean userAnswer, boolean correctAnswer) {
        selection.setText("You selected "+userAnswer+".");
        // Changes button appearance to enable refresh but disable true and false
        resultUI();

        // Show feedback
        correctTF.setText("The translation of \""+
                myDb.pullData("Expression",category, randomEnglish)+"\" is \""
                +myDb.pullData( "Translation", category, randomEnglish)+"\".");
        // Show correct or not
        if (userAnswer == correctAnswer) {
            resultTF.setText("Correct!");
            resultTF.setTextColor(Color.GREEN);
            return true;
        } else {
            // If incorrect, user gets a strike
            resultTF.setText("Incorrect!");
            resultTF.setTextColor(Color.RED);
            strikes++;
            updateStrikes(strikes);
            return false;
        }
    }

    // Show number of strikes
    private void updateStrikes(int strikes) {
        switch (strikes) {
            case 1:
                strike1.setVisibility(View.VISIBLE);
                break;
            case 2:
                strike2.setVisibility(View.VISIBLE);
                break;
            // On third strike, starts dialog
            case 3:
                strike3.setVisibility(View.VISIBLE);
                practiceOver();
                break;
        }
    }

    // Clear result text
    private void clearResultText() {
        resultTF.setText("");
        correctTF.setText("");
        selection.setText("");
    }

    // Changes button appearance to enable true and false but disable refresh
    private void answerUI() {
        trueButton.setEnabled(true);
        trueButton.setAlpha((float) 1);
        falseButton.setEnabled(true);
        falseButton.setAlpha((float) 1);
        refresh.setEnabled(false);
        refresh.setAlpha((float) 0.1);
    }

    // Changes button appearance to enable refresh but disable true and false
    private void resultUI() {
        trueButton.setEnabled(false);
        trueButton.setAlpha((float) 0.1);
        falseButton.setEnabled(false);
        falseButton.setAlpha((float) 0.1);
        refresh.setEnabled(true);
        refresh.setAlpha((float) 1);
    }

    // On third strike, this method is invoked
    private void practiceOver() {
        // Create dialog
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.practice_game_over);

        // Link to XMl
        TextView grade = dialog.findViewById(R.id.grade1);
        TextView score = dialog.findViewById(R.id.score1);
        TextView coinsIncrease = dialog.findViewById(R.id.coinsIncrease1);
        ImageView reaction = dialog.findViewById(R.id.reaction1);
        Button tryQuiz = dialog.findViewById(R.id.tryQuiz);
        Button menu = dialog.findViewById(R.id.menu1);
        Button retry = dialog.findViewById(R.id.retry1);

        // SQLiteHelper & DatabaseHelper
        final SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        myDb = new DatabaseHelper(this);

        int coinsEarned = 0;
        // Calculate coinsPerQuestion;
        // for every 5 questions the user attempts correct, the number of coins earned
        // per a question increases;
        // creates incentive to continually improve and get higher scores
        int coinsPerQuestion = (int) (Math.floor(correct/5)+1);
        coinsEarned = correct * coinsPerQuestion;
        // Get current coins
        int coinsCurrrent = Integer.parseInt(sqLiteHelper.getData(SQLiteHelper.COL_4, 1));
        // Update coins
        sqLiteHelper.update(
                1, "Coins", "Coins", coinsCurrrent + coinsEarned);

        // Set text for different scenarios,
        // showing number of correct answers and how many coins earned
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

        // Button to go back to menu
        menu.setOnClickListener(new View.OnClickListener() {
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

        // Button to continue to quiz
        tryQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to quiz and pass on category
                Intent intent2 = new Intent(getApplicationContext(), QuizTest.class);
                intent2.putExtra("category", category);
                startActivity(intent2);
            }
        });

        // Button to retry
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset UI
                resetUI();
                // Update UI with the first question
                updateUI();
                // Exit dialog
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
    }

    // Reset UI
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

        exitDialog.show();

        // Prevent users to cancel by touching outside or pressing back again
        exitDialog.setCanceledOnTouchOutside(false);
        exitDialog.setCancelable(false);
    }
}