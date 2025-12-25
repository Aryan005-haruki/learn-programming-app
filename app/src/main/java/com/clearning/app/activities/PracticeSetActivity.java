package com.clearning.app.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.clearning.app.R;
import com.clearning.app.models.Program;
import com.clearning.app.utils.PracticeDataProvider;
import java.util.List;

public class PracticeSetActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private int setNumber;
    private String setTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_set);

        setNumber = getIntent().getIntExtra("set_number", 1);
        setTitle = getIntent().getStringExtra("set_title");

        setupToolbar();
        initViews();
        loadPrograms();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(setTitle);
        }
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadPrograms() {
        List<Program> programs = PracticeDataProvider.getProgramsBySet(setNumber);
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
            View view = LayoutInflater.from(PracticeSetActivity.this).inflate(R.layout.item_program, parent, false);
            return new ProgramViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProgramViewHolder holder, int position) {
            Program program = programs.get(position);
            holder.tvTitle.setText(program.getTitle());
            holder.tvDescription.setText(program.getDescription());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PracticeSetActivity.this, ProgramDetailActivity.class);
                    intent.putExtra("title", program.getTitle());
                    intent.putExtra("description", program.getDescription());
                    intent.putExtra("code", program.getCode());
                    intent.putExtra("output", program.getOutput());
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return programs.size();
        }

        class ProgramViewHolder extends RecyclerView.ViewHolder {
            TextView tvTitle, tvDescription;

            ProgramViewHolder(@NonNull View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.tvProgramTitle);
                tvDescription = itemView.findViewById(R.id.tvProgramDescription);
            }
        }
    }
}
