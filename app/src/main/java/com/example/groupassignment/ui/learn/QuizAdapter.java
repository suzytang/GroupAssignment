package com.example.groupassignment.ui.learn;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment.R;

import java.util.ArrayList;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {
    private Context mContext;
    private ArrayList<QuizAnswers> mQuiz;

    //Creates listener which links to respective restaurant Id
//    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Quiz restaurant = (Quiz) v.getTag();
//
//            Context context = v.getContext();
//            Intent intent = new Intent(context, DetailActivity.class);
//            intent.putExtra(DetailFragment.ARG_ITEM_ID, restaurant.getId());
//            context.startActivity(intent);
//
//        }
//    };

    //    Constructor
    public QuizAdapter(Context context, ArrayList<QuizAnswers> quizAnswers) {
        mContext = context;
        mQuiz = quizAnswers;
    }

    //Create Viewholder class for restaurant_list_row
    public static class QuizViewHolder extends RecyclerView.ViewHolder  {
        public TextView english, question, userAnswer,correctAnswer;
        public CardView card;

        public QuizViewHolder(View v) {
            super(v);
            card = v.findViewById(R.id.cardView);
            english = v.findViewById(R.id.englishTF);
            userAnswer = v.findViewById(R.id.userAnswer);
            correctAnswer = v.findViewById(R.id.correctAnswer);
            question = v.findViewById(R.id.question);
        }
    }

    //Inflate restaurant_list_row and return view
    @Override
    public QuizAdapter.QuizViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_results, parent, false);
        return new QuizViewHolder(v);
    }

    //Replace contents of a view with respective data
    @Override
    public void onBindViewHolder(QuizViewHolder holder, int position) {
        final QuizAnswers result = mQuiz.get(position);
//
//        //Sets listener
//        holder.itemView.setOnClickListener(mOnClickListener);
        holder.question.setText("QUESTION "+result.getQuestion());
        holder.english.setText(result.getEnglish());
        holder.userAnswer.setText("Your translation: "+result.getAnswer());
        holder.correctAnswer.setText("Correct translation: "+result.getTranslation());

        if (result.getScore() == 1) {
            holder.card.setCardBackgroundColor(ColorStateList.valueOf(0xffdeffe6));
        } else {
            holder.card.setCardBackgroundColor(ColorStateList.valueOf(0xffffdede));
        }
        holder.itemView.setTag(result);

    }

    //Return the size of dataset
    @Override
    public int getItemCount() {
        return mQuiz.size();
    }

}
