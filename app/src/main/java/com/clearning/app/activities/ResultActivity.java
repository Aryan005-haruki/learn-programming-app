package com.clearning.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.clearning.app.R;
import com.clearning.app.adapters.ResultAdapter;
import com.clearning.app.models.Quiz;
import com.clearning.app.models.User;
import com.clearning.app.utils.DataProvider;
import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {
    private TextView tvScore, tvPercentage, tvMessage;
    private RecyclerView recyclerView;
    private Button btnRetry, btnBackToLessons;
    private DataProvider dataProvider;
    private List<Quiz> quizzes;
    private String chapterId, chapterTitle;
    private int score, totalQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        chapterId = getIntent().getStringExtra("chapter_id");
        chapterTitle = getIntent().getStringExtra("chapter_title");
        quizzes = (ArrayList<Quiz>) getIntent().getSerializableExtra("quizzes");

        setupToolbar();
        initViews();
        calculateScore();
        displayResults();
        setupClickListeners();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Quiz Results");
        }
    }

    private void initViews() {
        tvScore = findViewById(R.id.tvScore);
        tvPercentage = findViewById(R.id.tvPercentage);
        tvMessage = findViewById(R.id.tvMessage);
        recyclerView = findViewById(R.id.recyclerView);
        btnRetry = findViewById(R.id.btnRetry);
        btnBackToLessons = findViewById(R.id.btnBackToLessons);
        
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataProvider = DataProvider.getInstance(this);
    }

    private void calculateScore() {
        score = 0;
        totalQuestions = quizzes.size();
        
        for (Quiz quiz : quizzes) {
            if (quiz.isCorrect()) {
                score++;
            }
        }
        
        // Save score to user profile
        User user = dataProvider.getCurrentUser();
        if (user != null) {
            int percentage = (score * 100) / totalQuestions;
            user.updateChapterScore(chapterId, percentage);
            dataProvider.saveUser(user);
        }
    }

    private void displayResults() {
        int percentage = (score * 100) / totalQuestions;
        
        tvScore.setText(String.format("%d/%d", score, totalQuestions));
        tvPercentage.setText(String.format("%d%%", percentage));
        
        // Set message based on performance
        String message;
        if (percentage >= 90) {
            message = "🎉 " + getString(R.string.excellent);
        } else if (percentage >= 70) {
            message = "👍 " + getString(R.string.good_job);
        } else if (percentage >= 50) {
            message = "💪 " + getString(R.string.keep_practicing);
        } else {
            message = "📚 " + getString(R.string.need_improvement);
        }
        tvMessage.setText(message);
        
        // Setup RecyclerView with results
        ResultAdapter adapter = new ResultAdapter(this, quizzes);
        recyclerView.setAdapter(adapter);
    }

    private void setupClickListeners() {
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
                intent.putExtra("chapter_id", chapterId);
                intent.putExtra("chapter_title", chapterTitle);
                startActivity(intent);
                finish();
            }
        });

        btnBackToLessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
