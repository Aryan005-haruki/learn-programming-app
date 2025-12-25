package com.clearning.app.activities;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.clearning.app.R;
import com.clearning.app.fragments.ChaptersFragment;
import com.clearning.app.fragments.CompilerFragment;
import com.clearning.app.fragments.PracticeFragment;
import com.clearning.app.fragments.ProgramsFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class CProgrammingActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_programming);

        setupToolbar();
        initViews();
        setupViewPager();
        
        // Check if we need to open compiler tab with code
        handleIntent();
    }
    
    private void handleIntent() {
        if (getIntent().hasExtra("openCompiler") && getIntent().getBooleanExtra("openCompiler", false)) {
            // Switch to compiler tab (index 2)
            viewPager.setCurrentItem(2, false);
            
            // Pass code to compiler fragment if available
            if (getIntent().hasExtra("code")) {
                String code = getIntent().getStringExtra("code");
                // Wait a bit for fragment to be created, then set code
                viewPager.post(new Runnable() {
                    @Override
                    public void run() {
                        Fragment fragment = getSupportFragmentManager().findFragmentByTag("f2"); // ViewPager2 uses "f" + position as tag
                        if (fragment instanceof CompilerFragment) {
                            ((CompilerFragment) fragment).setCode(code);
                        }
                    }
                });
            }
        }
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("C Programming");
        }
    }

    private void initViews() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Chapters");
                    break;
                case 1:
                    tab.setText("Programs");
                    break;
                case 2:
                    tab.setText("Compiler");
                    break;
                case 3:
                    tab.setText("Practice & Quiz");
                    break;
            }
        }).attach();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private class ViewPagerAdapter extends FragmentStateAdapter {
        public ViewPagerAdapter(@NonNull AppCompatActivity activity) {
            super(activity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new ChaptersFragment();
                case 1:
                    return new ProgramsFragment();
                case 2:
                    return new CompilerFragment();
                case 3:
                    return new PracticeFragment();
                default:
                    return new ChaptersFragment();
            }
        }

        @Override
        public int getItemCount() {
            return 4;
        }
    }
}
