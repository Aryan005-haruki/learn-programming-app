package com.clearning.app.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.clearning.app.R;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class JavaCompilerFragment extends Fragment {
    private EditText etCode;
    private Button btnRun;
    private TextView tvOutput;
    private ProgressBar progressBar;
    private String pendingCode = null;
    
    // Shortcut buttons
    private Button btnTab, btnBraceOpen, btnBraceClose;
    private Button btnParenOpen, btnParenClose, btnSemicolon, btnQuote;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compiler, container, false);
        
        etCode = view.findViewById(R.id.etCode);
        btnRun = view.findViewById(R.id.btnRun);
        tvOutput = view.findViewById(R.id.tvOutput);
        progressBar = view.findViewById(R.id.progressBar);
        
        // Initialize shortcut buttons
        btnTab = view.findViewById(R.id.btnTab);
        btnBraceOpen = view.findViewById(R.id.btnBraceOpen);
        btnBraceClose = view.findViewById(R.id.btnBraceClose);
        btnParenOpen = view.findViewById(R.id.btnParenOpen);
        btnParenClose = view.findViewById(R.id.btnParenClose);
        btnSemicolon = view.findViewById(R.id.btnSemicolon);
        btnQuote = view.findViewById(R.id.btnQuote);
        
        // Set default Java code or pending code
        if (pendingCode != null && !pendingCode.isEmpty()) {
            etCode.setText(pendingCode);
            pendingCode = null; // Clear after use
        } else {
            etCode.setText("public class Main {\n    public static void main(String[] args) {\n        System.out.println(\"Hello, World!\");\n    }\n}");
        }
        
        // Setup shortcut button listeners
        setupShortcutButtons();
        
        btnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runCode();
            }
        });
        
        return view;
    }
    
    private void setupShortcutButtons() {
        btnTab.setOnClickListener(v -> insertText("    ")); // 4 spaces
        btnBraceOpen.setOnClickListener(v -> insertText("{"));
        btnBraceClose.setOnClickListener(v -> insertText("}"));
        btnParenOpen.setOnClickListener(v -> insertText("("));
        btnParenClose.setOnClickListener(v -> insertText(")"));
        btnSemicolon.setOnClickListener(v -> insertText(";"));
        btnQuote.setOnClickListener(v -> insertText("\""));
    }
    
    private void insertText(String text) {
        int start = etCode.getSelectionStart();
        int end = etCode.getSelectionEnd();
        etCode.getText().replace(Math.min(start, end), Math.max(start, end), text);
    }
    
    public void setCode(String code) {
        if (etCode != null) {
            etCode.setText(code);
        } else {
            pendingCode = code;
        }
    }

    private void runCode() {
        String code = etCode.getText().toString().trim();
        
        if (code.isEmpty()) {
            Toast.makeText(getContext(), "Please write some code first!", Toast.LENGTH_SHORT).show();
            return;
        }
        
        tvOutput.setText("Compiling and running...");
        btnRun.setEnabled(false);
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
        
        // Run code using JDoodle API
        new CompileTask().execute(code);
    }

    private class CompileTask extends AsyncTask<String, Void, String> {
        private boolean isError = false;
        
        @Override
        protected String doInBackground(String... params) {
            String code = params[0];
            
            try {
                URL url = new URL("https://api.jdoodle.com/v1/execute");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);
                conn.setConnectTimeout(10000); // 10 seconds timeout
                conn.setReadTimeout(15000); // 15 seconds timeout
                
                // Create JSON request for Java
                JSONObject jsonRequest = new JSONObject();
                jsonRequest.put("clientId", "e93b317e36a70cda57bfb88e9c8a83f6");
                jsonRequest.put("clientSecret", "bf81fd9ac657ad450ed7a00142dc68979200ea29f8f058b6c609d8894b75ee52");
                jsonRequest.put("script", code);
                jsonRequest.put("language", "java");
                jsonRequest.put("versionIndex", "4"); // Java 17
                
                // Send request
                OutputStream os = conn.getOutputStream();
                os.write(jsonRequest.toString().getBytes());
                os.flush();
                os.close();
                
                // Check response code
                int responseCode = conn.getResponseCode();
                if (responseCode != 200) {
                    isError = true;
                    return "Server Error: HTTP " + responseCode + "\nPlease check your internet connection.";
                }
                
                // Read response
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                br.close();
                
                // Parse response
                JSONObject jsonResponse = new JSONObject(response.toString());
                
                if (jsonResponse.has("output")) {
                    String output = jsonResponse.getString("output");
                    if (output == null || output.trim().isEmpty()) {
                        return "Program compiled successfully!\n(No output)";
                    }
                    return output;
                } else if (jsonResponse.has("error")) {
                    isError = true;
                    return "Compilation Error:\n" + jsonResponse.getString("error");
                } else if (jsonResponse.has("memory") || jsonResponse.has("cpuTime")) {
                    // Success but no output
                    return "Program compiled and executed successfully!\n(No output)";
                } else {
                    isError = true;
                    return "Unknown response from server";
                }
                
            } catch (java.net.SocketTimeoutException e) {
                isError = true;
                return "Error: Connection timeout\nPlease check your internet connection and try again.";
            } catch (java.net.UnknownHostException e) {
                isError = true;
                return "Error: Cannot reach server\nPlease check your internet connection.";
            } catch (Exception e) {
                isError = true;
                return "Error: " + e.getMessage() + "\n\nNote: Make sure you have internet connection.\nThe compiler uses JDoodle API.";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            tvOutput.setText(result);
            btnRun.setEnabled(true);
            if (progressBar != null) {
                progressBar.setVisibility(View.GONE);
            }
            
            if (!isError) {
                Toast.makeText(getContext(), "Execution complete!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
