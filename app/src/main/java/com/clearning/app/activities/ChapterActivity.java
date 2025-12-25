package com.clearning.app.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.clearning.app.R;
import com.clearning.app.adapters.ChapterAdapter;
import com.clearning.app.models.Chapter;
import com.clearning.app.utils.DataProvider;
import java.util.List;

public class ChapterActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ChapterAdapter adapter;
    private DataProvider dataProvider;
    private List<Chapter> chapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        setupToolbar();
        initViews();
        loadChapters();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Chapters");
        }
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataProvider = DataProvider.getInstance(this);
    }

    private void loadChapters() {
        chapters = dataProvider.getChapters();
        
        adapter = new ChapterAdapter(this, chapters, new ChapterAdapter.OnChapterClickListener() {
            @Override
            public void onChapterClick(Chapter chapter) {
                Intent intent = new Intent(ChapterActivity.this, LessonActivity.class);
                intent.putExtra("chapter_id", chapter.getChapterId());
                intent.putExtra("chapter_title", chapter.getTitle());
                startActivity(intent);
            }

            @Override
            public void onQuizClick(Chapter chapter) {
                Intent intent = new Intent(ChapterActivity.this, QuizActivity.class);
                intent.putExtra("chapter_id", chapter.getChapterId());
                intent.putExtra("chapter_title", chapter.getTitle());
                startActivity(intent);
            }
        });
        
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
