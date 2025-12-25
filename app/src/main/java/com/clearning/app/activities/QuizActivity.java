package com.clearning.app.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.clearning.app.R;
import com.clearning.app.models.Quiz;
import com.clearning.app.utils.DataProvider;
import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private TextView tvQuestionNumber, tvQuestion;
    private RadioGroup radioGroup;
    private RadioButton rbOption1, rbOption2, rbOption3, rbOption4;
    private Button btnSubmit;
    private DataProvider dataProvider;
    private List<Quiz> quizzes;
    private int currentQuestionIndex = 0;
    private String chapterId, chapterTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        chapterId = getIntent().getStringExtra("chapter_id");
        chapterTitle = getIntent().getStringExtra("chapter_title");

        setupToolbar();
        initViews();
        loadQuizzes();
        displayQuestion();
        setupClickListeners();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Quiz - " + chapterTitle);
        }
    }

    private void initViews() {
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvQuestion = findViewById(R.id.tvQuestion);
        radioGroup = findViewById(R.id.radioGroup);
        rbOption1 = findViewById(R.id.rbOption1);
        rbOption2 = findViewById(R.id.rbOption2);
        rbOption3 = findViewById(R.id.rbOption3);
        rbOption4 = findViewById(R.id.rbOption4);
        btnSubmit = findViewById(R.id.btnSubmit);
        
        dataProvider = DataProvider.getInstance(this);
    }

    private void loadQuizzes() {
        quizzes = dataProvider.getQuizzesForChapter(chapterId);
        if (quizzes == null || quizzes.isEmpty()) {
            Toast.makeText(this, "No quiz available for this chapter", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void displayQuestion() {
        if (quizzes != null && currentQuestionIndex < quizzes.size()) {
            Quiz quiz = quizzes.get(currentQuestionIndex);
            
            // Get selected language
            String language = getSelectedLanguage();
            
            tvQuestionNumber.setText(String.format("Question %d of %d", 
                currentQuestionIndex + 1, quizzes.size()));
            
            // Display question based on language
            if (language.equals("english")) {
                tvQuestion.setText(quiz.getQuestion());
                ArrayList<String> options = quiz.getOptions();
                if (options.size() >= 4) {
                    rbOption1.setText(options.get(0));
                    rbOption2.setText(options.get(1));
                    rbOption3.setText(options.get(2));
                    rbOption4.setText(options.get(3));
                }
            } else {
                // Hinglish mode
                String questionHinglish = quiz.getQuestionHinglish();
                tvQuestion.setText(questionHinglish != null ? questionHinglish : quiz.getQuestion());
                
                ArrayList<String> optionsHinglish = quiz.getOptionsHinglish();
                if (optionsHinglish != null && optionsHinglish.size() >= 4) {
                    rbOption1.setText(optionsHinglish.get(0));
                    rbOption2.setText(optionsHinglish.get(1));
                    rbOption3.setText(optionsHinglish.get(2));
                    rbOption4.setText(optionsHinglish.get(3));
                } else {
                    // Fallback to English if Hinglish not available
                    ArrayList<String> options = quiz.getOptions();
                    if (options.size() >= 4) {
                        rbOption1.setText(options.get(0));
                        rbOption2.setText(options.get(1));
                        rbOption3.setText(options.get(2));
                        rbOption4.setText(options.get(3));
                    }
                }
            }
            
            radioGroup.clearCheck();
            
            // Update button text
            if (currentQuestionIndex == quizzes.size() - 1) {
                btnSubmit.setText("Finish Quiz");
            } else {
                btnSubmit.setText("Next Question");
            }
        }
    }
    
    private String getSelectedLanguage() {
        SharedPreferences prefs = getSharedPreferences("AppSettings", MODE_PRIVATE);
        return prefs.getString("language", "hinglish");
    }

    private void setupClickListeners() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAnswer();
            }
        });
    }

    private void submitAnswer() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        
        if (selectedId == -1) {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
            return;
        }
        
        // Determine which option was selected
        int selectedAnswer = -1;
        if (selectedId == R.id.rbOption1) selectedAnswer = 0;
        else if (selectedId == R.id.rbOption2) selectedAnswer = 1;
        else if (selectedId == R.id.rbOption3) selectedAnswer = 2;
        else if (selectedId == R.id.rbOption4) selectedAnswer = 3;
        
        // Save user's answer
        quizzes.get(currentQuestionIndex).setUserAnswer(selectedAnswer);
        
        // Move to next question or show results
        currentQuestionIndex++;
        
        if (currentQuestionIndex < quizzes.size()) {
            displayQuestion();
        } else {
            showResults();
        }
    }

    private void showResults() {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("chapter_id", chapterId);
        intent.putExtra("chapter_title", chapterTitle);
        
        // Pass quiz results
        ArrayList<Quiz> quizArrayList = new ArrayList<>(quizzes);
        intent.putExtra("quizzes", quizArrayList);
        
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
