package com.example.androidbasicproject.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Users implements Parcelable {
    @SerializedName("following")
    private int following;
    @SerializedName("follower")
    private int follower;
    @SerializedName("repository")
    private int repository;
    @SerializedName("location")
    private String location;
    @SerializedName("company")
    private String company;
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("name")
    private String name;
    @SerializedName("username")
    private String username;

    private Users(Parcel in) {
        following = in.readInt();
        follower = in.readInt();
        repository = in.readInt();
        location = in.readString();
        company = in.readString();
        avatar = in.readString();
        name = in.readString();
        username = in.readString();
    }

    public static final Creator<Users> CREATOR = new Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel in) {
            return new Users(in);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };

    public int getFollowing() {
        return following;
    }

    public int getFollower() {
        return follower;
    }

    public int getRepository() {
        return repository;
    }

    public String getLocation() {
        return location;
    }

    public String getCompany() {
        return company;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(following);
        dest.writeInt(follower);
        dest.writeInt(repository);
        dest.writeString(location);
        dest.writeString(company);
        dest.writeString(avatar);
        dest.writeString(name);
        dest.writeString(username);
    }
}
