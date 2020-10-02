package com.example.androidbasicproject.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(UserEntity userEntity);

    @Update
    void update(UserEntity userEntity);

    @Query("SELECT * FROM user_table ORDER BY LOWER(name) ASC")
    LiveData<List<UserEntity>> getAllUser();

    @Delete
    void delete(UserEntity userEntity);

    @Query("SELECT EXISTS(SELECT * FROM user_table WHERE login = :login)")
    LiveData<Boolean> check(String login);

    @Query("DELETE FROM user_table")
    void deleteAll();
}
