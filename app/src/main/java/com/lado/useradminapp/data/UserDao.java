package com.lado.useradminapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.lado.useradminapp.model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    User getUser(String email, String password);

    @Query("SELECT * FROM user")
    List<User> getAllUser();

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    LiveData<User> getUserLive(String email, String password);

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAllUserLive();
}

