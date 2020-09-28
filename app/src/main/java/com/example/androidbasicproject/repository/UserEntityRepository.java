package com.example.androidbasicproject.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.androidbasicproject.database.UserDao;
import com.example.androidbasicproject.database.UserDatabase;
import com.example.androidbasicproject.database.UserEntity;

import java.util.List;

public class UserEntityRepository {
    private UserDao userDao;
    private LiveData<List<UserEntity>> allUsers;

    public UserEntityRepository(Application application) {
        UserDatabase database = UserDatabase.getInstance(application);
        userDao = database.userDao();
        allUsers = userDao.getAllUser();
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
