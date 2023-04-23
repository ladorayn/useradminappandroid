package com.lado.useradminapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.lado.useradminapp.OnClickUserInterface;
import com.lado.useradminapp.R;
import com.lado.useradminapp.adapter.UserAdapter;
import com.lado.useradminapp.databinding.ActivityAdminBinding;
import com.lado.useradminapp.model.User;
import com.lado.useradminapp.viewModel.UserViewModel;

import java.util.List;
import java.util.Objects;

public class AdminActivity extends AppCompatActivity implements OnClickUserInterface {

    private ActivityAdminBinding binding;
    private UserViewModel userViewModel;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        getSupportActionBar().setTitle("Admin View");
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.userRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        userAdapter = new UserAdapter(this);
        binding.userRecyclerView.setAdapter(userAdapter);

        userViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(UserViewModel.class);


        userViewModel.getAllUserLive().observe(AdminActivity.this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (users != null) {
                    userAdapter.setUsers(users);
                }
            }
        });
    }

    @Override
    public void onClickUser(User user, boolean isEdit) {

        if (isEdit) {
            Intent intent = new Intent(AdminActivity.this, EditUser.class);
            intent.putExtra("user", user);
            startActivity(intent);
        } else {
            userViewModel.deleteUser(user);
        }
    }
}