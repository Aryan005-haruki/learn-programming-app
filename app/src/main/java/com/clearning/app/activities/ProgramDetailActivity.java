package com.clearning.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.clearning.app.R;

public class ProgramDetailActivity extends AppCompatActivity {
    private TextView tvProgramTitle, tvDescription, tvCode, tvOutput;
    private Button btnTryCompiler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_detail);

        setupToolbar();
        initViews();
        loadProgramData();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle("Program Details");
            }
        }
    }

    private void initViews() {
        tvProgramTitle = findViewById(R.id.tvProgramTitle);
        tvDescription = findViewById(R.id.tvDescription);
        tvCode = findViewById(R.id.tvCode);
        tvOutput = findViewById(R.id.tvOutput);
        btnTryCompiler = findViewById(R.id.btnTryCompiler);
    }

    private void loadProgramData() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        String code = intent.getStringExtra("code");
        String output = intent.getStringExtra("output");
        final boolean isJava = intent.getBooleanExtra("is_java", false);


        // Replace escaped characters based on language
        if (code != null && isJava) {
            // Only process Java code - Java needs all escapes converted
            code = code.replace("\\n", "\n");      // Newlines
            code = code.replace("\\\"", "\"");     // Quotes
            code = code.replace("\\t", "\t");      // Tabs
        }
        // C code is left as-is - it already has correct escape sequences
        
        if (output != null) {
            output = output.replace("\\n", "\n");
        }

        if (tvProgramTitle != null) tvProgramTitle.setText(title);
        if (tvDescription != null) tvDescription.setText(description);
        if (tvCode != null) tvCode.setText(code);
        if (tvOutput != null) tvOutput.setText(output);

        final String finalCode = code;
        btnTryCompiler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (isJava) {
                    // Navigate to Java Programming Activity and switch to Compiler tab
                    intent = new Intent(ProgramDetailActivity.this, JavaProgrammingActivity.class);
                } else {
                    // Navigate to C Programming Activity and switch to Compiler tab
                    intent = new Intent(ProgramDetailActivity.this, CProgrammingActivity.class);
                }
                intent.putExtra("code", finalCode);
                intent.putExtra("openCompiler", true);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
