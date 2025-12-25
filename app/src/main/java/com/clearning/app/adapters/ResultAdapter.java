package com.clearning.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.clearning.app.R;
import com.clearning.app.models.Quiz;
import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {
    private Context context;
    private List<Quiz> quizzes;

    public ResultAdapter(Context context, List<Quiz> quizzes) {
        this.context = context;
        this.quizzes = quizzes;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_result, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        Quiz quiz = quizzes.get(position);
        
        holder.tvQuestionNumber.setText(String.format("Q%d", position + 1));
        holder.tvQuestion.setText(quiz.getQuestion());
        
        // Show user's answer
        if (quiz.getUserAnswer() != -1 && quiz.getUserAnswer() < quiz.getOptions().size()) {
            holder.tvUserAnswer.setText("Your Answer: " + quiz.getOptions().get(quiz.getUserAnswer()));
            
            if (quiz.isCorrect()) {
                holder.tvUserAnswer.setTextColor(context.getResources().getColor(R.color.success));
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.success_light));
                holder.tvCorrectAnswer.setVisibility(View.GONE);
                holder.tvExplanation.setVisibility(View.GONE);
            } else {
                holder.tvUserAnswer.setTextColor(context.getResources().getColor(R.color.error));
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.error_light));
                
                // Show correct answer and explanation
                holder.tvCorrectAnswer.setText("Correct Answer: " + 
                    quiz.getOptions().get(quiz.getCorrectAnswer()));
                holder.tvCorrectAnswer.setVisibility(View.VISIBLE);
                
                holder.tvExplanation.setText("💡 " + quiz.getExplanation());
                holder.tvExplanation.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return quizzes.size();
    }

    static class ResultViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvQuestionNumber, tvQuestion, tvUserAnswer, tvCorrectAnswer, tvExplanation;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            tvQuestionNumber = itemView.findViewById(R.id.tvQuestionNumber);
            tvQuestion = itemView.findViewById(R.id.tvQuestion);
            tvUserAnswer = itemView.findViewById(R.id.tvUserAnswer);
            tvCorrectAnswer = itemView.findViewById(R.id.tvCorrectAnswer);
            tvExplanation = itemView.findViewById(R.id.tvExplanation);
        }
    }
}
