package com.example.consumerapp.entity;

import android.net.Uri;
import android.provider.BaseColumns;

public class UserColumn implements BaseColumns {

    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_LOGIN = "login";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AVATARURL = "avatar_url";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_REPOS = "public_repos";
    public static final String COLUMN_FOLLOWERS = "followers";
    public static final String COLLUMN_FOLLOWING = "following";

    public static final String AUTHORITY = "com.example.androidbasicproject";
    private static final String SCHEME = "content";

    public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath(UserEntity.TABLE_NAME)
            .build();

}
