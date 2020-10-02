package com.example.androidbasicproject.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

//need to learn migration strategy
@Database(entities = {UserEntity.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase instance;
    //callback
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    public static synchronized UserDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    UserDatabase.class, "user_database")
                    .addCallback(roomCallback)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract UserDao userDao();

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;

        public PopulateDbAsyncTask(UserDatabase database) {
            this.userDao = database.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.insert(new UserEntity(
                    4090245,
                    "sidiqpermana",
                    "Sidiq Permana",
                    "https://avatars1.githubusercontent.com/u/4090245?v=4",
                    "Jakarta Indonesia",
                    67,
                    509,
                    10));
            return null;
        }
    }

}
