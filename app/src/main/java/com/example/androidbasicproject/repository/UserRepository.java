package com.example.androidbasicproject.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidbasicproject.database.UserDao;
import com.example.androidbasicproject.database.UserDatabase;
import com.example.androidbasicproject.database.UserEntity;
import com.example.androidbasicproject.model.GithubDetail.UserDetail;
import com.example.androidbasicproject.model.GithubList.FollowList;
import com.example.androidbasicproject.model.GithubModel.Items;
import com.example.androidbasicproject.model.GithubModel.Users;
import com.example.androidbasicproject.service.InternetService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private UserDao userDao;
    private LiveData<List<UserEntity>> allUsers;
    private LiveData<Boolean> thisUser;

    private static UserRepository instance;
    private final Application application;

    private final InternetService internetService;
    private Call<String> apiCall;

    private MutableLiveData<Users> userList = new MutableLiveData<>();
    private MutableLiveData<UserDetail> userDetail = new MutableLiveData<>();
    private MutableLiveData<FollowList> userFollower = new MutableLiveData<>();
    private MutableLiveData<FollowList> userFollowing = new MutableLiveData<>();

    public static UserRepository getInstance(Application application) {
        if (instance == null) {
            instance = new UserRepository(application);
        }
        return instance;
    }

    public UserRepository(Application application) {
        this.application = application;
        internetService = new InternetService(application);
        UserDatabase database = UserDatabase.getInstance(application);
        userDao = database.userDao();
        allUsers = userDao.getAllUser();
    }

    public LiveData<Users> getUserList() {
        return userList;
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

    public void fetchUserList(String name) {
        apiCall = InternetService.getServicesApi().getUsers(name,50);
        apiCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    Users result = gson.fromJson(response.body(), Users.class);
                    userList.setValue(result);
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Toast.makeText(application, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void fetchUserDetail(String name) {
        apiCall = InternetService.getServicesApi().getUserDetail(name);
        apiCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    UserDetail result = gson.fromJson(response.body(), UserDetail.class);
                    userDetail.setValue(result);
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Toast.makeText(application, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void fetchUserFollower(String name) {
        apiCall = InternetService.getServicesApi().getUserFollower(name);
        apiCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<Items>>() {
                    }.getType();
                    List<Items> result = gson.fromJson(response.body(), listType);
                    FollowList list = new FollowList(result);
                    userFollower.setValue(list);
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Toast.makeText(application, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void fetchUserFollowing(String name) {
        apiCall = InternetService.getServicesApi().getUserFollowing(name);
        apiCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<Items>>() {
                    }.getType();
                    List<Items> result = gson.fromJson(response.body(), listType);
                    FollowList list = new FollowList(result);
                    userFollowing.setValue(list);
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Toast.makeText(application, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void insert(UserEntity userEntity) {
        new InsertUserAsyncTask(userDao).execute(userEntity);
    }

    public void update(UserEntity userEntity) {
        new UpdateUserAsyncTask(userDao).execute(userEntity);
    }

    public void delete(UserEntity userEntity) {
        new DeleteUserAsyncTask(userDao).execute(userEntity);
    }

    public void deleteAllUser() {
        new DeleteAllUserAsyncTask(userDao).execute();
    }

    public LiveData<List<UserEntity>> getAllUsers() {
        return allUsers;
    }

    public void checkThisUser(String login) {
        thisUser = userDao.check(login);
    }

    public LiveData<Boolean> getThisUser() {
        return thisUser;
    }

    private static class InsertUserAsyncTask extends AsyncTask<UserEntity, Void, Void> {
        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(UserEntity... userEntities) {
            userDao.insert(userEntities[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<UserEntity, Void, Void> {
        private UserDao userDao;

        private UpdateUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(UserEntity... userEntities) {
            userDao.update(userEntities[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<UserEntity, Void, Void> {
        private UserDao userDao;

        private DeleteUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(UserEntity... userEntities) {
            userDao.delete(userEntities[0]);
            return null;
        }
    }

    private static class DeleteAllUserAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;

        private DeleteAllUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAll();
            return null;
        }
    }
}
