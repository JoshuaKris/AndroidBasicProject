package com.example.androidbasicproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidbasicproject.model.GithubModel.Users;
import com.example.androidbasicproject.repository.UserRepository;

public class MainViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private MutableLiveData<String> userSearch = new MutableLiveData<>();
    private LiveData<Users> userList;

    public MainViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance(application);
        userList = userRepository.getUserList();
    }

    public LiveData<String> getSearch() {
        return userSearch;
    }

    public LiveData<Users> getUserList() {
        return userList;
    }

    public void searchUser(String name) {
        userSearch.setValue(name);
        userRepository.fetchUserList(name);
    }

    public void checkThisUser(String login) {
        userRepository.checkThisUser(login);
    }
}
