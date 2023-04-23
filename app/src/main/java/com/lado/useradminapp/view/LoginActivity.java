package com.lado.useradminapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.lado.useradminapp.model.User;
import com.lado.useradminapp.R;
import com.lado.useradminapp.viewModel.UserViewModel;

import java.util.List;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TextView registerTextView;
    private UserViewModel userViewModel;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupAttr();

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (email.equals("") && password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fill the email and password", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        User user = userViewModel.getUser(email, password);
                        progressBar.setVisibility(View.GONE);
                        if (user != null) {
                            Intent main;
                            if (Objects.equals(user.getRole(), "User")) {
                                main = new Intent(LoginActivity.this, UserActivity.class);
                            } else {
                                main = new Intent(LoginActivity.this, AdminActivity.class);
                            }
                            startActivity(main);
                        } else {
                            Toast.makeText(v.getContext(), "User Not Registered", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        progressBar.setVisibility(View.GONE);
                        Log.e("ERROR LOGIN", e.getLocalizedMessage());
                        Toast.makeText(v.getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        registerTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        resetEditText();

    }

    public void setupAttr(){
        emailEditText = findViewById(R.id.editTextTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextTextPassword);
        loginButton = findViewById(R.id.button);
        registerTextView = findViewById(R.id.button_text);
        progressBar = findViewById(R.id.progressBar);

        userViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(UserViewModel.class);
    }

    public void resetEditText() {
        emailEditText.setText("");
        passwordEditText.setText("");
    }
}
