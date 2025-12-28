package com.clearning.app.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.clearning.app.R;
import com.clearning.app.models.User;
import com.clearning.app.utils.DataProvider;

public class ProfileActivity extends AppCompatActivity {
    private TextView tvName, tvEmail, tvPhone, tvLessonsCompleted, tvTotalScore;
    private Button btnLogout, btnEditProfile;
    private DataProvider dataProvider;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setupToolbar();
        initViews();
        loadUserData();
        setupClickListeners();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Profile");
        }
    }

    private void initViews() {
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        tvLessonsCompleted = findViewById(R.id.tvLessonsCompleted);
        tvTotalScore = findViewById(R.id.tvTotalScore);
        btnLogout = findViewById(R.id.btnLogout);
        btnEditProfile = findViewById(R.id.btnEditProfile);
        
        dataProvider = DataProvider.getInstance(this);
    }

    private void loadUserData() {
        currentUser = dataProvider.getCurrentUser();
        
        if (currentUser != null) {
            tvName.setText(currentUser.getName());
            tvEmail.setText(currentUser.getEmail());
            
            // Display phone if available
            if (currentUser.getPhone() != null && !currentUser.getPhone().isEmpty()) {
                tvPhone.setText("📞 " + currentUser.getPhone());
            } else {
                tvPhone.setText("📞 Not provided");
            }
            
            tvLessonsCompleted.setText(String.valueOf(currentUser.getLessonsCompleted()));
            tvTotalScore.setText(String.valueOf(currentUser.getTotalScore()));
        }
    }

    private void setupClickListeners() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutDialog();
            }
        });

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showLogoutDialog() {
        new android.app.AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert)
            .setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    logout();
                }
            })
            .setNegativeButton("No", null)
            .setCancelable(true)
            .show();
    }

    private void logout() {
        try {
            // Sign out from Firebase
            com.google.firebase.auth.FirebaseAuth.getInstance().signOut();
            
            // Clear local data
            dataProvider.logout();
            
            // Show toast
            android.widget.Toast.makeText(this, "Logged out successfully", android.widget.Toast.LENGTH_SHORT).show();
            
            // Navigate to login with proper flags
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            android.widget.Toast.makeText(this, "Error: " + e.getMessage(), android.widget.Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh user data when returning from EditProfileActivity
        loadUserData();
    }
}
