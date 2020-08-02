package com.example.androidbasicproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.androidbasicproject.model.GithubDetail.UserDetail;
import com.example.androidbasicproject.model.GithubList.FollowList;
import com.example.androidbasicproject.repository.UserRepository;

public class DetailViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private MutableLiveData<UserDetail> userDetail;
    private MutableLiveData<FollowList> userFollower;
    private MutableLiveData<FollowList> userFollowing;
    private MutableLiveData<Boolean> isViewDestroyed = new MutableLiveData<>();

    public DetailViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance(application);
        userDetail = (MutableLiveData<UserDetail>) userRepository.getUserDetail();
        userFollower = (MutableLiveData<FollowList>) userRepository.getUserFollower();
        userFollowing = (MutableLiveData<FollowList>) userRepository.getUserFollowing();
    }

    public MutableLiveData<UserDetail> getUserDetail() {
        return userDetail;
    }

    public MutableLiveData<FollowList> getUserFollower() {
        return userFollower;
    }

    public MutableLiveData<FollowList> getUserFollowing() {
        return userFollowing;
    }

    public void setIsViewDestroyed(boolean isViewDestroyed) {
        this.isViewDestroyed.setValue(isViewDestroyed);
        if (isViewDestroyed) {
            this.userDetail.setValue(null);
            this.userFollower.setValue(null);
            this.userFollowing.setValue(null);
        }
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
