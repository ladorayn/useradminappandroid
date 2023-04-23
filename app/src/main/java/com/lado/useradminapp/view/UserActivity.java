package com.lado.useradminapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.lado.useradminapp.R;
import com.lado.useradminapp.adapter.PhotoAdapter;
import com.lado.useradminapp.adapter.UserAdapter;
import com.lado.useradminapp.databinding.ActivityAdminBinding;
import com.lado.useradminapp.databinding.ActivityUserBinding;
import com.lado.useradminapp.model.Photo;
import com.lado.useradminapp.model.User;
import com.lado.useradminapp.viewModel.PhotosViewModel;
import com.lado.useradminapp.viewModel.UserViewModel;

import java.util.List;
import java.util.Objects;

public class UserActivity extends AppCompatActivity {

    private ActivityUserBinding binding;
    private PhotosViewModel photosViewModel;
    private PhotoAdapter photoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        getSupportActionBar().setTitle("User View");

        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.photosRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.progressBar4.setVisibility(View.VISIBLE);
        photosViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(PhotosViewModel.class);


        photosViewModel.getPhotoAPI().observe(UserActivity.this, new Observer<List<Photo>>() {
            @Override
            public void onChanged(List<Photo> photos) {
                if (photos != null) {
                    binding.progressBar4.setVisibility(View.GONE);
                    photoAdapter = new PhotoAdapter(UserActivity.this, photos);
                    binding.photosRecyclerView.setAdapter(photoAdapter);
                }
            }
        });
    }
}