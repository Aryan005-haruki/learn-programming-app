package com.clearning.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.clearning.app.R;
import com.clearning.app.models.Chapter;
import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> {
    private Context context;
    private List<Chapter> chapters;
    private OnChapterClickListener listener;

    public interface OnChapterClickListener {
        void onChapterClick(Chapter chapter);
        void onQuizClick(Chapter chapter);
    }

    public ChapterAdapter(Context context, List<Chapter> chapters, OnChapterClickListener listener) {
        this.context = context;
        this.chapters = chapters;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chapter, parent, false);
        return new ChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {
        Chapter chapter = chapters.get(position);
        
        holder.tvChapterTitle.setText(chapter.getTitle());
        holder.tvLessonCount.setText(String.format("%d Lessons", chapter.getTotalLessons()));
        
        int progress = chapter.getProgressPercentage();
        holder.progressBar.setProgress(progress);
        holder.tvProgress.setText(String.format("%d%%", progress));
        
        if (chapter.isCompleted()) {
            holder.tvStatus.setText("✓ Completed");
            holder.tvStatus.setBackgroundResource(R.drawable.badge_completed);
            holder.tvStatus.setTextColor(context.getResources().getColor(R.color.text_white));
        } else if (chapter.getCompletedLessons() > 0) {
            holder.tvStatus.setText("In Progress");
            holder.tvStatus.setBackgroundResource(R.drawable.badge_in_progress);
            holder.tvStatus.setTextColor(context.getResources().getColor(R.color.text_white));
        } else {
            holder.tvStatus.setText("Not Started");
            holder.tvStatus.setBackgroundResource(R.drawable.badge_not_started);
            holder.tvStatus.setTextColor(context.getResources().getColor(R.color.text_white));
        }
        
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onChapterClick(chapter);
            }
        });
        
        holder.tvQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onQuizClick(chapter);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }

    static class ChapterViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvChapterTitle, tvLessonCount, tvStatus, tvProgress, tvQuiz;
        ProgressBar progressBar;

        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            tvChapterTitle = itemView.findViewById(R.id.tvChapterTitle);
            tvLessonCount = itemView.findViewById(R.id.tvLessonCount);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvProgress = itemView.findViewById(R.id.tvProgress);
            tvQuiz = itemView.findViewById(R.id.tvQuiz);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}
