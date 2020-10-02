package com.example.androidbasicproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidbasicproject.database.UserEntity;
import com.example.androidbasicproject.model.GithubDetail.UserDetail;
import com.example.androidbasicproject.model.GithubList.FollowList;
import com.example.androidbasicproject.repository.UserRepository;

public class DetailViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private MutableLiveData<UserDetail> userDetail;
    private MutableLiveData<FollowList> userFollower;
    private MutableLiveData<FollowList> userFollowing;
    private LiveData<Boolean> user;
    private MutableLiveData<Boolean> isViewDestroyed = new MutableLiveData<>();

    public DetailViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance(application);
        userDetail = userRepository.getUserDetail();
        userFollower = userRepository.getUserFollower();
        userFollowing = userRepository.getUserFollowing();
        user = userRepository.getThisUser();
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

    public LiveData<Boolean> getUser() {
        if (user == null) {
            user = new MutableLiveData<>();
        }
        return user;
    }

    public void setIsViewDestroyed(boolean isViewDestroyed) {
        this.isViewDestroyed.setValue(isViewDestroyed);
        if (isViewDestroyed) {
            this.userDetail.setValue(null);
            this.userFollower.setValue(null);
            this.userFollowing.setValue(null);
            this.user = new MutableLiveData<>();
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

    public void insertToDB(UserEntity userEntity) {
        userRepository.insert(userEntity);
    }

    public void deleteFromDB(UserEntity userEntity) {
        userRepository.delete(userEntity);
    }

}
