package com.example.androidbasicproject.database;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class UserEntity {

    @PrimaryKey()
    private int id;
    private String login;
    private String name;
    private String avatarUrl;
    private String location;
    private int publicRepos;
    private int followers;
    private int following;

    public static final String AUTHORITY = "com.example.androidbasicproject";
    private static final String SCHEME = "content";
    public static String TABLE_NAME = "user_table";
    public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath(TABLE_NAME)
            .build();

    public UserEntity(int id, String login, String name, String avatarUrl, String location, int publicRepos, int followers, int following) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.location = location;
        this.publicRepos = publicRepos;
        this.followers = followers;
        this.following = following;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getLocation() {
        return location;
    }

    public int getPublicRepos() {
        return publicRepos;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    @NonNull
    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", location='" + location + '\'' +
                ", publicRepos=" + publicRepos +
                ", followers=" + followers +
                ", following=" + following +
                '}';
    }
}
