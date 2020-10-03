package com.example.consumerapp.utils;

import android.database.Cursor;

import com.example.consumerapp.entity.UserColumn;
import com.example.consumerapp.entity.UserEntity;

import java.util.ArrayList;

public class MappingHelper {
    public static ArrayList<UserEntity> mapCursorToArrayList(Cursor cursor) {
        ArrayList<UserEntity> userEntities = new ArrayList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(UserColumn.COLUMN_ID));
            String login = cursor.getString(cursor.getColumnIndexOrThrow(UserColumn.COLUMN_LOGIN));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(UserColumn.COLUMN_NAME));
            String avatarUrl = cursor.getString(cursor.getColumnIndexOrThrow(UserColumn.COLUMN_AVATARURL));
            String location = cursor.getString(cursor.getColumnIndexOrThrow(UserColumn.COLUMN_LOCATION));
            int publicRepos = cursor.getInt(cursor.getColumnIndexOrThrow(UserColumn.COLUMN_REPOS));
            int followers = cursor.getInt(cursor.getColumnIndexOrThrow(UserColumn.COLUMN_FOLLOWERS));
            int following = cursor.getInt(cursor.getColumnIndexOrThrow(UserColumn.COLLUMN_FOLLOWING));
            userEntities.add(new UserEntity(id, login, name, avatarUrl, location, publicRepos, followers, following));
        }

        return userEntities;
    }

    public static UserEntity mapCursorToObject(Cursor cursor) {
        cursor.moveToFirst();
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(UserColumn.COLUMN_ID));
        String login = cursor.getString(cursor.getColumnIndexOrThrow(UserColumn.COLUMN_LOGIN));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(UserColumn.COLUMN_NAME));
        String avatarUrl = cursor.getString(cursor.getColumnIndexOrThrow(UserColumn.COLUMN_AVATARURL));
        String location = cursor.getString(cursor.getColumnIndexOrThrow(UserColumn.COLUMN_LOCATION));
        int publicRepos = cursor.getInt(cursor.getColumnIndexOrThrow(UserColumn.COLUMN_REPOS));
        int followers = cursor.getInt(cursor.getColumnIndexOrThrow(UserColumn.COLUMN_FOLLOWERS));
        int following = cursor.getInt(cursor.getColumnIndexOrThrow(UserColumn.COLLUMN_FOLLOWING));

        return new UserEntity(id, login, name, avatarUrl, location, publicRepos, followers, following);
    }
}
