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

public class PracticeFragment extends Fragment {
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
                intent.putExtra("chapter_id", "chapter_final");
                intent.putExtra("chapter_title", "Final C Quiz - 100 Questions");
                startActivity(intent);
            }
        });
    }

    private List<PracticeSet> getPracticeSets() {
        List<PracticeSet> sets = new ArrayList<>();
        sets.add(new PracticeSet("Set 1: Basics", "5 programs", "Hello World, Print name, Add numbers, Area of circle, Swap numbers"));
        sets.add(new PracticeSet("Set 2: Conditions", "5 programs", "Even/Odd, Largest of 3, Leap year, Positive/Negative, Grade"));
        sets.add(new PracticeSet("Set 3: Loops", "5 programs", "Print 1 to N, Sum of N, Factorial, Fibonacci, Prime check"));
        sets.add(new PracticeSet("Set 4: Arrays & Strings", "5 programs", "Array sum, Largest element, Reverse array, String length, Palindrome"));
        sets.add(new PracticeSet("Set 5: Functions & Pointers", "5 programs", "Add function, Call by value/reference, Swap with pointers, Pointer to array, Pointer basics"));
        sets.add(new PracticeSet("Set 6: Structures & Files", "5 programs", "Student structure, Employee salary, File write, File read, Append to file"));
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
