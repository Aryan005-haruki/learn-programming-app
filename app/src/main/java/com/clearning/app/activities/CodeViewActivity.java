package com.clearning.app.activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.clearning.app.R;
import com.clearning.app.models.Lesson;
import com.clearning.app.models.User;
import com.clearning.app.utils.DataProvider;
import java.util.List;

public class CodeViewActivity extends AppCompatActivity {
    private TextView tvTopic, tvExplanation, tvSyntax, tvCode, tvOutput, tvNotes, tvPractice;
    private Button btnMarkComplete, btnNext, btnCopyCode;
    private DataProvider dataProvider;
    private Lesson currentLesson;
    private String lessonId, chapterId;
    private List<Lesson> allLessons;
    private int currentLessonIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_view);

        lessonId = getIntent().getStringExtra("lesson_id");
        chapterId = getIntent().getStringExtra("chapter_id");

        setupToolbar();
        initViews();
        loadLesson();
        setupClickListeners();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initViews() {
        tvTopic = findViewById(R.id.tvTopic);
        tvExplanation = findViewById(R.id.tvExplanation);
        tvSyntax = findViewById(R.id.tvSyntax);
        tvCode = findViewById(R.id.tvCode);
        tvOutput = findViewById(R.id.tvOutput);
        tvNotes = findViewById(R.id.tvNotes);
        tvPractice = findViewById(R.id.tvPractice);
        btnMarkComplete = findViewById(R.id.btnMarkComplete);
        btnNext = findViewById(R.id.btnNext);
        btnCopyCode = findViewById(R.id.btnCopyCode);
        
        dataProvider = DataProvider.getInstance(this);
    }

    private void loadLesson() {
        allLessons = dataProvider.getLessonsForChapter(chapterId);
        
        // Find current lesson
        for (int i = 0; i < allLessons.size(); i++) {
            if (allLessons.get(i).getLessonId().equals(lessonId)) {
                currentLesson = allLessons.get(i);
                currentLessonIndex = i;
                break;
            }
        }

        if (currentLesson != null) {
            displayLesson();
        }
    }

    private void displayLesson() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(currentLesson.getTitle());
        }

        tvTopic.setText(currentLesson.getTitle());
        
        // Check language preference and display appropriate explanation
        String currentLanguage = SettingsActivity.getCurrentLanguage(this);
        if (currentLanguage.equals(SettingsActivity.LANG_HINGLISH)) {
            // Show Hinglish explanation if available, otherwise fallback to English
            String hinglishExplanation = currentLesson.getExplanationHinglish();
            if (hinglishExplanation != null && !hinglishExplanation.isEmpty()) {
                tvExplanation.setText(hinglishExplanation);
            } else {
                tvExplanation.setText(currentLesson.getExplanation());
            }
        } else {
            // Show English explanation
            tvExplanation.setText(currentLesson.getExplanation());
        }
        
        // Show/hide syntax section
        View syntaxCard = findViewById(R.id.syntaxCard);
        if (currentLesson.getSyntax() != null && !currentLesson.getSyntax().isEmpty()) {
            tvSyntax.setText(currentLesson.getSyntax());
            syntaxCard.setVisibility(View.VISIBLE);
        } else {
            syntaxCard.setVisibility(View.GONE);
        }
        
        tvCode.setText(currentLesson.getExampleCode());
        tvOutput.setText(currentLesson.getOutput());
        tvNotes.setText(currentLesson.getNotes());
        tvPractice.setText(currentLesson.getPracticeQuestion());

        // Update button states
        if (currentLesson.isCompleted()) {
            btnMarkComplete.setText("✓ Completed");
            btnMarkComplete.setEnabled(false);
        }

        // Show/hide next button
        if (currentLessonIndex < allLessons.size() - 1) {
            btnNext.setVisibility(View.VISIBLE);
        } else {
            btnNext.setVisibility(View.GONE);
        }
    }

    private void setupClickListeners() {
        btnMarkComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                markLessonComplete();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadNextLesson();
            }
        });

        btnCopyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyCodeToClipboard();
            }
        });
    }

    private void markLessonComplete() {
        User user = dataProvider.getCurrentUser();
        if (user != null) {
            user.markLessonComplete(lessonId);
            dataProvider.saveUser(user);
            currentLesson.setCompleted(true);
            
            btnMarkComplete.setText("✓ Completed");
            btnMarkComplete.setEnabled(false);
            Toast.makeText(this, "Lesson marked as complete!", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadNextLesson() {
        if (currentLessonIndex < allLessons.size() - 1) {
            currentLessonIndex++;
            currentLesson = allLessons.get(currentLessonIndex);
            lessonId = currentLesson.getLessonId();
            displayLesson();
            
            // Scroll to top
            findViewById(R.id.scrollView).scrollTo(0, 0);
        }
    }

    private void copyCodeToClipboard() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("C Code", currentLesson.getExampleCode());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Code copied to clipboard!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
