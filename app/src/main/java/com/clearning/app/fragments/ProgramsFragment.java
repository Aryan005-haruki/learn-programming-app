package com.clearning.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.clearning.app.R;
import com.clearning.app.activities.CategoryProgramsActivity;
import com.clearning.app.utils.ProgramDataProvider;
import java.util.List;

public class ProgramsFragment extends Fragment {
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_programs, container, false);
        
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2)); // 2 columns
        
        setupCategories();
        
        return view;
    }

    private void setupCategories() {
        List<String> categories = ProgramDataProvider.getAllCategories();
        CategoryAdapter adapter = new CategoryAdapter(categories);
        recyclerView.setAdapter(adapter);
    }

    private class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
        private List<String> categories;

        public CategoryAdapter(List<String> categories) {
            this.categories = categories;
        }

        @NonNull
        @Override
        public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_chapter, parent, false);
            return new CategoryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
            String category = categories.get(position);
            holder.tvTitle.setText(category);
            
            // Set description based on category
            int programCount = ProgramDataProvider.getProgramsByCategory(category).size();
            holder.tvLessonCount.setText(programCount + " programs");
            
            holder.tvStatus.setVisibility(View.GONE);
            holder.progressBar.setVisibility(View.GONE);
            holder.tvProgress.setVisibility(View.GONE);
            holder.tvQuiz.setVisibility(View.GONE);
            
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), CategoryProgramsActivity.class);
                    intent.putExtra("category_name", category);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return categories.size();
        }

        class CategoryViewHolder extends RecyclerView.ViewHolder {
            TextView tvTitle, tvLessonCount, tvStatus, tvProgress, tvQuiz;
            View progressBar;

            public CategoryViewHolder(@NonNull View itemView) {
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
