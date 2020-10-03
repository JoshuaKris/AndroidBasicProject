package com.example.consumerapp.entity;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = UserEntity.TABLE_NAME)
public class UserEntity implements Parcelable {

    public static final String TABLE_NAME = "user_table";
    public static final Creator<UserEntity> CREATOR = new Creator<UserEntity>() {
        @Override
        public UserEntity createFromParcel(Parcel in) {
            return new UserEntity(in);
        }

        @Override
        public UserEntity[] newArray(int size) {
            return new UserEntity[size];
        }
    };
    @PrimaryKey()
    @ColumnInfo(index = true, name = UserColumn.COLUMN_ID)
    private int id;
    @ColumnInfo(index = true, name = UserColumn.COLUMN_LOGIN)
    private String login;
    @ColumnInfo(index = true, name = UserColumn.COLUMN_NAME)
    private String name;
    @ColumnInfo(index = true, name = UserColumn.COLUMN_AVATARURL)
    private String avatarUrl;
    @ColumnInfo(index = true, name = UserColumn.COLUMN_LOCATION)
    private String location;
    @ColumnInfo(index = true, name = UserColumn.COLUMN_REPOS)
    private int publicRepos;
    @ColumnInfo(index = true, name = UserColumn.COLUMN_FOLLOWERS)
    private int followers;
    @ColumnInfo(index = true, name = UserColumn.COLLUMN_FOLLOWING)
    private int following;

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

    public UserEntity() {
    }

    protected UserEntity(Parcel in) {
        id = in.readInt();
        login = in.readString();
        name = in.readString();
        avatarUrl = in.readString();
        location = in.readString();
        publicRepos = in.readInt();
        followers = in.readInt();
        following = in.readInt();
    }

    public static UserEntity fromContentValues(ContentValues values) {
        final UserEntity userEntity = new UserEntity();
        if (values.containsKey(UserColumn.COLUMN_ID)) {
            userEntity.id = values.getAsInteger(UserColumn.COLUMN_ID);
        }
        if (values.containsKey(UserColumn.COLUMN_LOGIN)) {
            userEntity.login = values.getAsString(UserColumn.COLUMN_LOGIN);
        }
        if (values.containsKey(UserColumn.COLUMN_NAME)) {
            userEntity.name = values.getAsString(UserColumn.COLUMN_NAME);
        }
        if (values.containsKey(UserColumn.COLUMN_AVATARURL)) {
            userEntity.avatarUrl = values.getAsString(UserColumn.COLUMN_AVATARURL);
        }
        if (values.containsKey(UserColumn.COLUMN_LOCATION)) {
            userEntity.location = values.getAsString(UserColumn.COLUMN_LOCATION);
        }
        if (values.containsKey(UserColumn.COLUMN_REPOS)) {
            userEntity.publicRepos = values.getAsInteger(UserColumn.COLUMN_REPOS);
        }
        if (values.containsKey(UserColumn.COLLUMN_FOLLOWING)) {
            userEntity.following = values.getAsInteger(UserColumn.COLLUMN_FOLLOWING);
        }
        if (values.containsKey(UserColumn.COLUMN_FOLLOWERS)) {
            userEntity.followers = values.getAsInteger(UserColumn.COLUMN_FOLLOWERS);
        }
        return userEntity;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(login);
        dest.writeString(name);
        dest.writeString(avatarUrl);
        dest.writeString(location);
        dest.writeInt(publicRepos);
        dest.writeInt(followers);
        dest.writeInt(following);
    }
}
