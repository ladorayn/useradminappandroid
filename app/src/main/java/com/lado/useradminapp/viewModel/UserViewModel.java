package com.lado.useradminapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.lado.useradminapp.model.User;
import com.lado.useradminapp.repository.AppRepo;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class UserViewModel extends AndroidViewModel {

    private AppRepo appRepo;

    public UserViewModel(@NonNull Application application) {
        super(application);
        appRepo = new AppRepo(application);

    }

    public void insertUser(User user) {
        appRepo.insertUser(user);
    }

    public void deleteUser(User user) {
        appRepo.deleteUser(user);
    }

    public void updateUser(User user) {
        appRepo.updateUser(user);
    }

    public User getUser(String email, String password) throws ExecutionException, InterruptedException {
        return appRepo.getUser(email, password);
    }

    public List<User> getAllUser() throws ExecutionException, InterruptedException {
        return appRepo.getAllUser();
    }

    public LiveData<User> getUserLive(String email, String password) {
        return appRepo.getUserLive(email, password);
    }

    public LiveData<List<User>> getAllUserLive() {
        return appRepo.getAllUserLive();
    }

}

