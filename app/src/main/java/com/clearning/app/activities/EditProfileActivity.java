package com.clearning.app.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.clearning.app.R;
import com.clearning.app.models.User;
import com.clearning.app.utils.DataProvider;

public class EditProfileActivity extends AppCompatActivity {
    private EditText etName, etEmail, etPhone;
    private Button btnSave, btnCancel;
    private DataProvider dataProvider;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        setupToolbar();
        initViews();
        loadCurrentData();
        setupClickListeners();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Edit Profile");
        }
    }

    private void initViews() {
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        dataProvider = DataProvider.getInstance(this);
    }

    private void loadCurrentData() {
        currentUser = dataProvider.getCurrentUser();

        if (currentUser != null) {
            etName.setText(currentUser.getName());
            etEmail.setText(currentUser.getEmail());
            if (currentUser.getPhone() != null) {
                etPhone.setText(currentUser.getPhone());
            }
        }
    }

    private void setupClickListeners() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfile();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void saveProfile() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();

        // Validation
        if (TextUtils.isEmpty(name)) {
            etName.setError("Name is required");
            etName.requestFocus();
            return;
        }

        if (name.length() < 2) {
            etName.setError("Name must be at least 2 characters");
            etName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please enter a valid email");
            etEmail.requestFocus();
            return;
        }

        // Phone validation (optional field)
        if (!TextUtils.isEmpty(phone)) {
            if (phone.length() != 10 || !phone.matches("\\d+")) {
                etPhone.setError("Phone must be 10 digits");
                etPhone.requestFocus();
                return;
            }
        }

        // Save to DataProvider
        boolean success = dataProvider.updateUserProfile(name, email, phone);

        if (success) {
            Toast.makeText(this, "Profile updated successfully!", Toast.LENGTH_SHORT).show();
            finish(); // Return to ProfileActivity
        } else {
            Toast.makeText(this, "Failed to update profile", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
