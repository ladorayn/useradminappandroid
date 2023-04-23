package com.lado.useradminapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lado.useradminapp.model.User;
import com.lado.useradminapp.R;
import com.lado.useradminapp.viewModel.UserViewModel;

public class RegisterActivity extends AppCompatActivity {

    private TextView loginButton;
    private Button regisButton;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private EditText editTextTextUsername;
    private EditText editTextTextEmailAddress2;
    private EditText editTextTextPassword2;
    private ProgressBar progressBar;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupAttr();

        regisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String username = editTextTextUsername.getText().toString();
                String email = editTextTextEmailAddress2.getText().toString();
                String password = editTextTextPassword2.getText().toString();

                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (!username.equals("") && !email.equals("") && !password.equals("") &&  selectedId != -1) {
                    radioButton = (RadioButton) findViewById(selectedId);
                    String role = (String) radioButton.getText();
                    userViewModel.insertUser(new User(username, email, password, role));

                    userViewModel.getUserLive(email, password).observe(RegisterActivity.this, new Observer<User>() {
                        @Override
                        public void onChanged(User user) {
                            if (user != null) {
                                progressBar.setVisibility(View.GONE);
                                finish();
                            } else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(v.getContext(), "User Not Registered", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });



                } else {
                    Toast.makeText(getApplicationContext(), "Fill the required field", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupAttr() {
        loginButton = findViewById(R.id.button_text2);
        regisButton = findViewById(R.id.regisButton);
        radioGroup = findViewById(R.id.radio_group);
        editTextTextUsername = findViewById(R.id.editTextTextUsername);
        editTextTextEmailAddress2 = findViewById(R.id.editTextTextEmailAddress2);
        editTextTextPassword2 = findViewById(R.id.editTextTextPassword2);
        progressBar = findViewById(R.id.progressBar2);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);


        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setElevation(0);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        resetRegisInput();
    }

    private void resetRegisInput() {
        editTextTextUsername.setText("");
        editTextTextEmailAddress2.setText("");
        editTextTextPassword2.setText("");
        RadioButton radioButton1 = findViewById(R.id.radio1);
        RadioButton radioButton2 = findViewById(R.id.radio2);
        radioButton1.setChecked(false);
        radioButton2.setChecked(false);
    }
}