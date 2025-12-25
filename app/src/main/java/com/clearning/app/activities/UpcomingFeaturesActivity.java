package com.clearning.app.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.clearning.app.R;
import com.clearning.app.adapters.UpcomingFeatureAdapter;
import com.clearning.app.models.UpcomingFeature;
import java.util.ArrayList;
import java.util.List;

public class UpcomingFeaturesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UpcomingFeatureAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_features);

        setupToolbar();
        initViews();
        loadFeatures();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Upcoming Features");
        }
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadFeatures() {
        List<UpcomingFeature> features = new ArrayList<>();

        // 1. Python Programming
        features.add(new UpcomingFeature(
            "Python Programming",
            "Complete Python course from basics to advanced with interactive examples",
            "Coming Soon",
            "🐍"
        ));

        // 2. DSA Questions - NEW!
        features.add(new UpcomingFeature(
            "DSA Questions",
            "Data Structures & Algorithms practice with C, Python, Java solutions. Arrays, Trees, Graphs, Sorting & more!",
            "Coming Soon",
            "🧮"
        ));

        // 3. JavaScript Course
        features.add(new UpcomingFeature(
            "JavaScript Course",
            "Web development with JavaScript - DOM manipulation, async programming, ES6+",
            "Planned",
            "🌐"
        ));

        // 4. Dark Mode
        features.add(new UpcomingFeature(
            "Dark Mode",
            "Eye-friendly dark theme for comfortable learning at night",
            "In Progress",
            "🌙"
        ));

        // 5. Offline Mode
        features.add(new UpcomingFeature(
            "Offline Mode",
            "Download lessons and learn without internet connection",
            "Planned",
            "📥"
        ));

        // 6. Code Playground
        features.add(new UpcomingFeature(
            "Code Playground",
            "Experiment with code snippets and test your ideas instantly",
            "Coming Soon",
            "🎮"
        ));

        // 7. Achievements & Badges
        features.add(new UpcomingFeature(
            "Achievements & Badges",
            "Earn rewards for completing milestones and challenges",
            "Planned",
            "🏆"
        ));

        // 8. Discussion Forum
        features.add(new UpcomingFeature(
            "Discussion Forum",
            "Ask doubts, share solutions, and discuss with the community. Like Stack Overflow for learners!",
            "Planned",
            "💬"
        ));

        // 9. Unlimited Compilation
        features.add(new UpcomingFeature(
            "Unlimited Compilation",
            "Run unlimited code compilations for C, Java, and Python without any restrictions!",
            "Coming Soon",
            "⚡"
        ));

        adapter = new UpcomingFeatureAdapter(this, features);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
