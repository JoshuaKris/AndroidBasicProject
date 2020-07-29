package com.example.androidbasicproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.androidbasicproject.model.GithubDetail.UserDetail;
import com.example.androidbasicproject.model.GithubList.FollowList;
import com.example.androidbasicproject.repository.UserRepository;

public class DetailViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private LiveData<UserDetail> userDetail;
    private LiveData<FollowList> userFollower;
    private LiveData<FollowList> userFollowing;

    public DetailViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance(application);
        userDetail = userRepository.getUserDetail();
        userFollower = userRepository.getUserFollower();
        userFollowing = userRepository.getUserFollowing();
    }

    public LiveData<UserDetail> getUserDetail() {
        return userDetail;
    }

    public LiveData<FollowList> getUserFollower() {
        return userFollower;
    }

    public LiveData<FollowList> getUserFollowing() {
        return userFollowing;
    }

    public void fetchUserDetail(String name) {
        userRepository.fetchUserDetail(name);
    }

    public void fetchUserFollower(String name) {
        userRepository.fetchUserFollower(name);
    }

    public void fetchUserFollowing(String name) {
        userRepository.fetchUserFollowing(name);
    }
}
