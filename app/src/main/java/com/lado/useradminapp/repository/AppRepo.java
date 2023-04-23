package com.lado.useradminapp.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.lado.useradminapp.data.UserDao;
import com.lado.useradminapp.data.UserDatabase;
import com.lado.useradminapp.model.Photo;
import com.lado.useradminapp.model.User;
import com.lado.useradminapp.network.RetrofitClient;
import com.lado.useradminapp.network.Endpoints;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppRepo {
    private UserDao userDao;
    private UserDatabase userDatabase;
    private Endpoints endpoints;
    private Executor executor = Executors.newSingleThreadExecutor();

    public AppRepo(Context context) {
        userDatabase = UserDatabase.getInstance(context);
        endpoints = RetrofitClient.getInstance().create(Endpoints.class);
        userDao = userDatabase.userDao();
    }

    public MutableLiveData<List<Photo>> getPhotoAPI() {

        Call<List<Photo>> call = endpoints.getAllPhotos();

        final MutableLiveData<List<Photo>> photosData = new MutableLiveData<>();

        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                photosData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                photosData.setValue(null);
            }
        });
        return photosData;
    }

    public void insertUser(User user) {
        executor.execute(() -> userDao.insert(user));
    }
    public void deleteUser(User user) {
        executor.execute(() -> userDao.delete(user));
    }
    public void updateUser(User user) {
        executor.execute(() -> userDao.update(user));
    }

    public User getUser(String email, String password) throws ExecutionException, InterruptedException {

        Callable<User> callable = () -> userDao.getUser(email, password);
        Future<User> future = Executors.newSingleThreadExecutor().submit(callable);
        return future.get();

    }

    public List<User> getAllUser() throws ExecutionException, InterruptedException {

        Callable<List<User>> callable = () -> userDao.getAllUser();
        Future<List<User>> future = Executors.newSingleThreadExecutor().submit(callable);
        return future.get();

    }

    public LiveData<User> getUserLive(String email, String password) {
        return userDao.getUserLive(email, password);
    }

    public LiveData<List<User>> getAllUserLive() {
        return userDao.getAllUserLive();
    }




}
