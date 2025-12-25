package com.clearning.app.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.clearning.app.R;
import com.clearning.app.adapters.LessonAdapter;
import com.clearning.app.models.Lesson;
import com.clearning.app.utils.DataProvider;
import java.util.List;

public class LessonActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LessonAdapter adapter;
    private DataProvider dataProvider;
    private String chapterId;
    private String chapterTitle;
    private List<Lesson> lessons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        chapterId = getIntent().getStringExtra("chapter_id");
        chapterTitle = getIntent().getStringExtra("chapter_title");

        setupToolbar();
        initViews();
        loadLessons();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(chapterTitle);
        }
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataProvider = DataProvider.getInstance(this);
    }

    private void loadLessons() {
        lessons = dataProvider.getLessonsForChapter(chapterId);
        
        adapter = new LessonAdapter(this, lessons, new LessonAdapter.OnLessonClickListener() {
            public void onLessonClick(Lesson lesson) {
                // For Module 10 (C Quiz module), directly open quiz instead of lesson details
                if ("chapter_10".equals(chapterId)) {
                    Intent intent = new Intent(LessonActivity.this, QuizActivity.class);
                    
                    // Check if it's the final quiz (order 10) or module quiz (1-9)
                    if (lesson.getOrder() == 10) {
                        // Final comprehensive C quiz
                        intent.putExtra("chapter_id", "chapter_final");
                        intent.putExtra("chapter_title", "Final C Quiz - 100 Questions");
                    } else {
                        // Map Module 10 lessons to their corresponding module quizzes
                        // Lesson order 1-9 maps to chapter_1 through chapter_9
                        String quizChapterId = "chapter_" + lesson.getOrder();
                        String quizTitle = "Module " + lesson.getOrder() + " Quiz";
                        intent.putExtra("chapter_id", quizChapterId);
                        intent.putExtra("chapter_title", quizTitle);
                    }
                    startActivity(intent);
                } 
                // For Java Module 13 (Java Quiz module), directly open quiz
                else if ("java_chapter_13".equals(chapterId)) {
                    Intent intent = new Intent(LessonActivity.this, QuizActivity.class);
                    
                    // Check if it's the final quiz (order 13) or module quiz (1-12)
                    if (lesson.getOrder() == 13) {
                        // Final comprehensive quiz
                        intent.putExtra("chapter_id", "java_chapter_final");
                        intent.putExtra("chapter_title", "Final Java Quiz");
                    } else {
                        // Map Java Module 13 lessons to their corresponding module quizzes
                        // Lesson order 1-12 maps to java_chapter_1 through java_chapter_12
                        String quizChapterId = "java_chapter_" + lesson.getOrder();
                        String quizTitle = "Module " + lesson.getOrder() + " Quiz";
                        intent.putExtra("chapter_id", quizChapterId);
                        intent.putExtra("chapter_title", quizTitle);
                    }
                    startActivity(intent);
                } 
                else {
                    // For other modules, open lesson details
                    Intent intent = new Intent(LessonActivity.this, CodeViewActivity.class);
                    intent.putExtra("lesson_id", lesson.getLessonId());
                    intent.putExtra("chapter_id", chapterId);
                    startActivity(intent);
                }
            }
        });
        
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh lessons to show updated completion status
        if (adapter != null) {
            loadLessons();
        }
    }
}
