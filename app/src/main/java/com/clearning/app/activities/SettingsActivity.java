package com.clearning.app.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.clearning.app.R;

public class SettingsActivity extends AppCompatActivity {
    private RadioGroup radioGroupLanguage;
    private RadioButton radioEnglish, radioHinglish;
    private SharedPreferences preferences;
    private static final String PREFS_NAME = "AppSettings";
    private static final String KEY_LANGUAGE = "language";
    public static final String LANG_ENGLISH = "english";
    public static final String LANG_HINGLISH = "hinglish";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setupToolbar();
        initViews();
        loadSettings();
        setupListeners();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Settings");
        }
    }

    private void initViews() {
        radioGroupLanguage = findViewById(R.id.radioGroupLanguage);
        radioEnglish = findViewById(R.id.radioEnglish);
        radioHinglish = findViewById(R.id.radioHinglish);
        
        preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
    }

    private void loadSettings() {
        String currentLanguage = preferences.getString(KEY_LANGUAGE, LANG_ENGLISH);
        
        if (currentLanguage.equals(LANG_HINGLISH)) {
            radioHinglish.setChecked(true);
        } else {
            radioEnglish.setChecked(true);
        }
    }

    private void setupListeners() {
        radioGroupLanguage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String selectedLanguage;
                String message;
                
                if (checkedId == R.id.radioHinglish) {
                    selectedLanguage = LANG_HINGLISH;
                    message = "Hinglish selected! भाषा बदल गई है।";
                } else {
                    selectedLanguage = LANG_ENGLISH;
                    message = "English selected!";
                }
                
                // Save language preference
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(KEY_LANGUAGE, selectedLanguage);
                editor.apply();
                
                Toast.makeText(SettingsActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    
    // Helper method to get current language (can be called from other activities)
    public static String getCurrentLanguage(android.content.Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return prefs.getString(KEY_LANGUAGE, LANG_ENGLISH);
    }
}
