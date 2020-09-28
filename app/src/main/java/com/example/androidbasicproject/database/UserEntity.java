package com.example.androidbasicproject.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class UserEntity {

    @PrimaryKey()
    private int id;
    private String name;
    private String avatar_url;
    private String location;
    private int public_repos;
    private int followers;
    private int following;

    public UserEntity(int id, String name, String avatar_url, String location, int public_repos, int followers, int following) {
        this.id = id;
        this.name = name;
        this.avatar_url = avatar_url;
        this.location = location;
        this.public_repos = public_repos;
        this.followers = followers;
        this.following = following;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getLocation() {
        return location;
    }

    public int getPublic_repos() {
        return public_repos;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }
}
