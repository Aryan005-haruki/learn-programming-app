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
import com.clearning.app.models.Lesson;
import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder> {
    private Context context;
    private List<Lesson> lessons;
    private OnLessonClickListener listener;

    public interface OnLessonClickListener {
        void onLessonClick(Lesson lesson);
    }

    public LessonAdapter(Context context, List<Lesson> lessons, OnLessonClickListener listener) {
        this.context = context;
        this.lessons = lessons;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lesson, parent, false);
        return new LessonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonViewHolder holder, int position) {
        Lesson lesson = lessons.get(position);
        
        holder.tvLessonNumber.setText(String.format("%d", lesson.getOrder()));
        holder.tvLessonTitle.setText(lesson.getTitle());
        
        if (lesson.isCompleted()) {
            holder.tvStatus.setText("✓");
            holder.tvStatus.setTextColor(context.getResources().getColor(R.color.success));
            holder.tvStatus.setVisibility(View.VISIBLE);
        } else {
            holder.tvStatus.setVisibility(View.GONE);
        }
        
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onLessonClick(lesson);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    static class LessonViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvLessonNumber, tvLessonTitle, tvStatus;

        public LessonViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            tvLessonNumber = itemView.findViewById(R.id.tvLessonNumber);
            tvLessonTitle = itemView.findViewById(R.id.tvLessonTitle);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }
}
