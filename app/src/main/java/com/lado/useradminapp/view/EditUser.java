package com.lado.useradminapp.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.lado.useradminapp.R;
import com.lado.useradminapp.databinding.ActivityEditUserBinding;
import com.lado.useradminapp.model.User;
import com.lado.useradminapp.viewModel.UserViewModel;

public class EditUser extends AppCompatActivity {

    private ActivityEditUserBinding binding;
    private String username, email, password, role;
    private int id;
    private UserViewModel userViewModel;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEditUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initPage();

        userViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(UserViewModel.class);

        if (getIntent().hasExtra("user")) {
            user = getIntent().getParcelableExtra("user");
            binding.editUsername.setText(user.getUsername());
            binding.editEmail.setText(user.getEmail());
            binding.editPassword.setText(String.valueOf(user.getPassword()));
            if (user.getRole().equals("User")) {
                binding.radio1.setChecked(true);
            } else {
                binding.radio2.setChecked(true);
            }
        }

        binding.editButton.setOnClickListener(view -> {
            binding.progressBar3.setVisibility(View.VISIBLE);
            int selectedId = binding.radioGroup.getCheckedRadioButtonId();
            username = binding.editUsername.getText().toString();
            email = binding.editEmail.getText().toString();
            password = binding.editPassword.getText().toString();

            if(username.equals("") || email.equals("") || password.equals("") || selectedId == -1) {
                Toast.makeText(this, "Fill the required field", Toast.LENGTH_SHORT).show();
                binding.progressBar3.setVisibility(View.GONE);
            } else {
                user.setUsername(username);
                user.setEmail(email);
                user.setPassword(password);
                RadioButton radioButton = findViewById(selectedId);
                String role = (String) radioButton.getText();
                user.setRole(role);
                userViewModel.updateUser(user);
                binding.progressBar3.setVisibility(View.GONE);
                Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }

    private void initPage() {
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        binding.editUsername.setText("");
        binding.editEmail.setText("");
        binding.editPassword.setText("");
        binding.radio1.setChecked(false);
        binding.radio2.setChecked(false);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();

    }
}