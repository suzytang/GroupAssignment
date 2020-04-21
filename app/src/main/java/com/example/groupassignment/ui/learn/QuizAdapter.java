package com.example.groupassignment.ui.learn;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment.R;

import java.util.ArrayList;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {
    private Context mContext;
    private ArrayList<QuizAnswers> mQuiz;

    // Constructor
    public QuizAdapter(Context context, ArrayList<QuizAnswers> quizAnswers) {
        mContext = context;
        mQuiz = quizAnswers;
    }

    // Create Viewholder for quiz_results_list
    public static class QuizViewHolder extends RecyclerView.ViewHolder  {
        public TextView english, question, userAnswer,correctAnswer;
        public ImageView correctImage;

        public QuizViewHolder(View v) {
            super(v);
            correctImage = v.findViewById(R.id.correctImage);
            english = v.findViewById(R.id.englishTF);
            userAnswer = v.findViewById(R.id.userAnswer);
            correctAnswer = v.findViewById(R.id.correctAnswer);
            question = v.findViewById(R.id.question);
        }
    }

    // Inflate quiz_results_list and return view
    @Override
    public QuizAdapter.QuizViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_results_list, parent, false);
        return new QuizViewHolder(v);
    }

    // Replace contents of the view with data
    @Override
    public void onBindViewHolder(QuizViewHolder holder, int position) {
        final QuizAnswers result = mQuiz.get(position);

        holder.question.setText("QUESTION "+result.getQuestion());
        holder.english.setText(result.getEnglish());
        holder.userAnswer.setText("Your translation: "+result.getAnswer());
        holder.correctAnswer.setText("Correct translation: "+result.getTranslation());

        if (result.getScore() > 0) {
            holder.correctImage.setImageResource(R.drawable.correct);
        } else {
            holder.correctImage.setImageResource(R.drawable.incorrect);
        }
        holder.itemView.setTag(result);

    }

    // Return size of dataset
    @Override
    public int getItemCount() {
        return mQuiz.size();
    }

}
