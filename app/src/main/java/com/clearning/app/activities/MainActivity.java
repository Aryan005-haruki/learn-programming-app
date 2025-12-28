package com.clearning.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import com.clearning.app.R;
import com.clearning.app.models.User;
import com.clearning.app.utils.DataProvider;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private TextView tvWelcome;
    private TextView tvUserAvatar; // Added tvUserAvatar
    private CardView cardCProgramming;
    private CardView cardJavaProgramming;
    private DataProvider dataProvider;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataProvider = DataProvider.getInstance(this);
        currentUser = dataProvider.getCurrentUser();
        
        // Create dummy user if no user exists (for testing without login)
        if (currentUser == null) {
            currentUser = new User("test_user", "Test User", "test@example.com");
            dataProvider.saveUser(currentUser);
        }
        
        setupToolbar();
        initViews(); // Moved initViews after setupToolbar
        loadUserData();
        setupClickListeners();
        setupAvatarMenu(); // Added setupAvatarMenu
    }

    private void initViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        tvWelcome = findViewById(R.id.tvWelcome);
        tvUserAvatar = findViewById(R.id.tvUserAvatar); // Initialized tvUserAvatar
        cardCProgramming = findViewById(R.id.cardCProgramming);
        cardJavaProgramming = findViewById(R.id.cardJavaProgramming);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false); // Hide default title
        }
    }

    private void loadUserData() {
        // Reload user data from DataProvider to get latest updates
        currentUser = dataProvider.getCurrentUser();
        
        if (currentUser != null) {
            tvWelcome.setText(String.format("Welcome, %s!", currentUser.getName()));
            // Set first letter of user's name in avatar
            String firstLetter = currentUser.getName().substring(0, 1).toUpperCase();
            tvUserAvatar.setText(firstLetter);
        } else {
            tvWelcome.setText("Welcome!");
            tvUserAvatar.setText("U"); // Default avatar for no user
        }
    }

    private void setupClickListeners() {
        cardCProgramming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CProgrammingActivity.class);
                startActivity(intent);
            }
        });
        
        cardJavaProgramming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JavaProgrammingActivity.class);
                startActivity(intent);
            }
        });
    }
    
    // New method for avatar menu
    private void setupAvatarMenu() {
        tvUserAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProfileMenu(v);
            }
        });
    }
    
    // New method to show profile popup menu
    private void showProfileMenu(View anchor) {
        PopupMenu popup = new PopupMenu(this, anchor);
        popup.getMenuInflater().inflate(R.menu.menu_profile, popup.getMenu());
        
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                
                if (id == R.id.action_profile) {
                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                    return true;
                } else if (id == R.id.action_settings) {
                    startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                    return true;
                } else if (id == R.id.action_about) {
                    startActivity(new Intent(MainActivity.this, AboutActivity.class));
                    return true;
                } else if (id == R.id.action_upcoming_features) {
                    startActivity(new Intent(MainActivity.this, UpcomingFeaturesActivity.class));
                    return true;
                } else if (id == R.id.action_logout) {
                    logout();
                    return true;
                }
                
                return false;
            }
        });
        
        popup.show();
    }

    private void logout() {
        // Sign out from Firebase
        FirebaseAuth.getInstance().signOut();
        
        // Clear local data
        dataProvider.logout();
        
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
        
        // Navigate to login
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUserData(); // Refresh data when returning to this activity
    }
}
