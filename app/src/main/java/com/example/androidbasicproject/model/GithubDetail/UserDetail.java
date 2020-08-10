package com.example.androidbasicproject.model.GithubDetail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UserDetail implements Parcelable {

    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("following")
    private int following;
    @SerializedName("followers")
    private int followers;
    @SerializedName("public_gists")
    private int publicGists;
    @SerializedName("public_repos")
    private int publicRepos;
    @SerializedName("twitter_username")
    private String twitterUsername;
    @SerializedName("bio")
    private String bio;
    @SerializedName("hireable")
    private String hireable;
    @SerializedName("email")
    private String email;
    @SerializedName("location")
    private String location;
    @SerializedName("blog")
    private String blog;
    @SerializedName("company")
    private String company;
    @SerializedName("name")
    private String name;
    @SerializedName("site_admin")
    private boolean siteAdmin;
    @SerializedName("type")
    private String type;
    @SerializedName("received_events_url")
    private String receivedEventsUrl;
    @SerializedName("events_url")
    private String eventsUrl;
    @SerializedName("repos_url")
    private String reposUrl;
    @SerializedName("organizations_url")
    private String organizationsUrl;
    @SerializedName("subscriptions_url")
    private String subscriptionsUrl;
    @SerializedName("starred_url")
    private String starredUrl;
    @SerializedName("gists_url")
    private String gistsUrl;
    @SerializedName("following_url")
    private String followingUrl;
    @SerializedName("followers_url")
    private String followersUrl;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("url")
    private String url;
    @SerializedName("gravatar_id")
    private String gravatarId;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("node_id")
    private String nodeId;
    @SerializedName("id")
    private int id;
    @SerializedName("login")
    private String login;

    protected UserDetail(Parcel in) {
        updatedAt = in.readString();
        createdAt = in.readString();
        following = in.readInt();
        followers = in.readInt();
        publicGists = in.readInt();
        publicRepos = in.readInt();
        twitterUsername = in.readString();
        bio = in.readString();
        hireable = in.readString();
        email = in.readString();
        location = in.readString();
        blog = in.readString();
        company = in.readString();
        name = in.readString();
        siteAdmin = in.readByte() != 0;
        type = in.readString();
        receivedEventsUrl = in.readString();
        eventsUrl = in.readString();
        reposUrl = in.readString();
        organizationsUrl = in.readString();
        subscriptionsUrl = in.readString();
        starredUrl = in.readString();
        gistsUrl = in.readString();
        followingUrl = in.readString();
        followersUrl = in.readString();
        htmlUrl = in.readString();
        url = in.readString();
        gravatarId = in.readString();
        avatarUrl = in.readString();
        nodeId = in.readString();
        id = in.readInt();
        login = in.readString();
    }

    public static final Creator<UserDetail> CREATOR = new Creator<UserDetail>() {
        @Override
        public UserDetail createFromParcel(Parcel in) {
            return new UserDetail(in);
        }

        @Override
        public UserDetail[] newArray(int size) {
            return new UserDetail[size];
        }
    };

    public int getFollowing() {
        return following;
    }

    public int getFollowers() {
        return followers;
    }

    public int getPublicRepos() {
        return publicRepos;
    }

    public String getLocation() {
        return location;
    }

    public String getCompany() {
        return company;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(updatedAt);
        dest.writeString(createdAt);
        dest.writeInt(following);
        dest.writeInt(followers);
        dest.writeInt(publicGists);
        dest.writeInt(publicRepos);
        dest.writeString(twitterUsername);
        dest.writeString(bio);
        dest.writeString(hireable);
        dest.writeString(email);
        dest.writeString(location);
        dest.writeString(blog);
        dest.writeString(company);
        dest.writeString(name);
        dest.writeByte((byte) (siteAdmin ? 1 : 0));
        dest.writeString(type);
        dest.writeString(receivedEventsUrl);
        dest.writeString(eventsUrl);
        dest.writeString(reposUrl);
        dest.writeString(organizationsUrl);
        dest.writeString(subscriptionsUrl);
        dest.writeString(starredUrl);
        dest.writeString(gistsUrl);
        dest.writeString(followingUrl);
        dest.writeString(followersUrl);
        dest.writeString(htmlUrl);
        dest.writeString(url);
        dest.writeString(gravatarId);
        dest.writeString(avatarUrl);
        dest.writeString(nodeId);
        dest.writeInt(id);
        dest.writeString(login);
    }
}
