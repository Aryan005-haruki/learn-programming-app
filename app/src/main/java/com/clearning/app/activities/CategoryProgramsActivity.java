package com.clearning.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.clearning.app.R;
import com.clearning.app.models.Program;
import com.clearning.app.utils.ProgramDataProvider;
import java.util.List;

public class CategoryProgramsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private String categoryName;
    private boolean isJava = false; // Track if Java programs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_programs);

        categoryName = getIntent().getStringExtra("category_name");
        
        setupToolbar();
        initViews();
        loadPrograms();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(categoryName);
        }
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadPrograms() {
        isJava = getIntent().getBooleanExtra("is_java", false); // Set class variable
        List<Program> programs;
        
        if (isJava) {
            programs = com.clearning.app.utils.JavaProgramDataProvider.getProgramsByCategory(categoryName);
        } else {
            programs = ProgramDataProvider.getProgramsByCategory(categoryName);
        }
        
        ProgramAdapter adapter = new ProgramAdapter(programs);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ProgramViewHolder> {
        private List<Program> programs;

        public ProgramAdapter(List<Program> programs) {
            this.programs = programs;
        }

        @NonNull
        @Override
        public ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(CategoryProgramsActivity.this).inflate(R.layout.item_chapter, parent, false);
            return new ProgramViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProgramViewHolder holder, int position) {
            Program program = programs.get(position);
            holder.tvTitle.setText(program.getTitle());
            holder.tvLessonCount.setText(program.getDescription());
            holder.tvStatus.setVisibility(View.GONE);
            holder.progressBar.setVisibility(View.GONE);
            holder.tvProgress.setVisibility(View.GONE);
            holder.tvQuiz.setVisibility(View.GONE);
            
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CategoryProgramsActivity.this, ProgramDetailActivity.class);
                    intent.putExtra("title", program.getTitle());
                    intent.putExtra("description", program.getDescription());
                    intent.putExtra("code", program.getCode());
                    intent.putExtra("output", program.getOutput());
                    intent.putExtra("is_java", isJava); // Pass Java flag
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return programs.size();
        }

        class ProgramViewHolder extends RecyclerView.ViewHolder {
            TextView tvTitle, tvLessonCount, tvStatus, tvProgress, tvQuiz;
            View progressBar;

            public ProgramViewHolder(@NonNull View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.tvChapterTitle);
                tvLessonCount = itemView.findViewById(R.id.tvLessonCount);
                tvStatus = itemView.findViewById(R.id.tvStatus);
                tvProgress = itemView.findViewById(R.id.tvProgress);
                tvQuiz = itemView.findViewById(R.id.tvQuiz);
                progressBar = itemView.findViewById(R.id.progressBar);
            }
        }
    }
}
