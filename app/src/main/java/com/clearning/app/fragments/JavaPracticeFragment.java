package com.clearning.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.clearning.app.R;
import com.clearning.app.activities.PracticeSetActivity;
import com.clearning.app.activities.QuizActivity;
import java.util.ArrayList;
import java.util.List;

public class JavaPracticeFragment extends Fragment {
    private RecyclerView recyclerView;
    private Button btnStartQuiz;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_practice, container, false);
        
        recyclerView = view.findViewById(R.id.recyclerView);
        btnStartQuiz = view.findViewById(R.id.btnStartQuiz);
        
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        
        setupPracticeSets();
        setupQuizButton();
        
        return view;
    }

    private void setupPracticeSets() {
        List<PracticeSet> practiceSets = getPracticeSets();
        PracticeSetAdapter adapter = new PracticeSetAdapter(practiceSets);
        recyclerView.setAdapter(adapter);
    }

    private void setupQuizButton() {
        btnStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), QuizActivity.class);
                intent.putExtra("chapter_id", "java_chapter_final");
                intent.putExtra("chapter_title", "Final Java Quiz - 100 Questions");
                startActivity(intent);
            }
        });
    }

    private List<PracticeSet> getPracticeSets() {
        List<PracticeSet> sets = new ArrayList<>();
        sets.add(new PracticeSet("Set 1: Basics", "5 programs", "Hello World, Variables, Data types, Operators, Input/Output"));
        sets.add(new PracticeSet("Set 2: Control Flow", "5 programs", "if-else, switch, loops (for, while, do-while)"));
        sets.add(new PracticeSet("Set 3: Arrays & Strings", "5 programs", "Array operations, String methods, 2D arrays"));
        sets.add(new PracticeSet("Set 4: OOP Basics", "5 programs", "Classes, Objects, Constructors, Methods"));
        sets.add(new PracticeSet("Set 5: Inheritance", "5 programs", "extends, super, method overriding, polymorphism"));
        sets.add(new PracticeSet("Set 6: Collections", "5 programs", "ArrayList, HashMap, HashSet, Iteration"));
        return sets;
    }

    private class PracticeSet {
        String title;
        String count;
        String description;

        PracticeSet(String title, String count, String description) {
            this.title = title;
            this.count = count;
            this.description = description;
        }
    }

    private class PracticeSetAdapter extends RecyclerView.Adapter<PracticeSetAdapter.PracticeSetViewHolder> {
        private List<PracticeSet> practiceSets;

        public PracticeSetAdapter(List<PracticeSet> practiceSets) {
            this.practiceSets = practiceSets;
        }

        @NonNull
        @Override
        public PracticeSetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_practice_set, parent, false);
            return new PracticeSetViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PracticeSetViewHolder holder, int position) {
            PracticeSet set = practiceSets.get(position);
            holder.tvTitle.setText(set.title);
            holder.tvCount.setText(set.count);
            holder.tvDescription.setText(set.description);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), PracticeSetActivity.class);
                    intent.putExtra("set_number", position + 1);
                    intent.putExtra("set_title", set.title);
                    intent.putExtra("language", "java");
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return practiceSets.size();
        }

        class PracticeSetViewHolder extends RecyclerView.ViewHolder {
            TextView tvTitle, tvCount, tvDescription;

            PracticeSetViewHolder(@NonNull View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.tvTitle);
                tvCount = itemView.findViewById(R.id.tvCount);
                tvDescription = itemView.findViewById(R.id.tvDescription);
            }
        }
    }
}
