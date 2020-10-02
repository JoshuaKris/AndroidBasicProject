package com.example.androidbasicproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.androidbasicproject.database.UserEntity;
import com.example.androidbasicproject.repository.UserRepository;

import java.util.List;

public class FavoritesViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private LiveData<List<UserEntity>> users;

    public FavoritesViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance(application);
        users = userRepository.getAllUsers();
    }

    public LiveData<List<UserEntity>> getUser() {
        return users;
    }

    public void checkThisUser(String login) {
        userRepository.checkThisUser(login);
    }

    public void deleteAllFavorites() {
        userRepository.deleteAllUser();
    }
}
