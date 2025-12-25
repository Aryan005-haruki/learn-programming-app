package com.clearning.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.clearning.app.R;
import com.clearning.app.activities.LessonActivity;
import com.clearning.app.activities.QuizActivity;
import com.clearning.app.adapters.ChapterAdapter;
import com.clearning.app.models.Chapter;
import com.clearning.app.utils.DataProvider;
import java.util.List;

public class JavaChaptersFragment extends Fragment {
    private RecyclerView recyclerView;
    private ChapterAdapter adapter;
    private DataProvider dataProvider;
    private List<Chapter> chapters;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chapters, container, false);
        
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        
        dataProvider = DataProvider.getInstance(getContext());
        loadChapters();
        
        return view;
    }

    private void loadChapters() {
        chapters = dataProvider.getJavaChapters();
        
        adapter = new ChapterAdapter(getContext(), chapters, new ChapterAdapter.OnChapterClickListener() {
            @Override
            public void onChapterClick(Chapter chapter) {
                Intent intent = new Intent(getContext(), LessonActivity.class);
                intent.putExtra("chapter_id", chapter.getChapterId());
                intent.putExtra("chapter_title", chapter.getTitle());
                startActivity(intent);
            }

            @Override
            public void onQuizClick(Chapter chapter) {
                Intent intent = new Intent(getContext(), QuizActivity.class);
                
                // If Quiz chapter (java_chapter_13), open the comprehensive 70-question final quiz
                if ("java_chapter_13".equals(chapter.getChapterId())) {
                    intent.putExtra("chapter_id", "java_chapter_final");
                    intent.putExtra("chapter_title", "Final Java Quiz - 70 Questions");
                } else {
                    // For regular chapters, open their specific quiz
                    intent.putExtra("chapter_id", chapter.getChapterId());
                    intent.putExtra("chapter_title", chapter.getTitle());
                }
                startActivity(intent);
            }
        });
        
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Reload chapters to show updated progress
        loadChapters();
    }
}
