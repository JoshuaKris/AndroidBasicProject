package com.example.androidbasicproject.model.GithubModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Items implements Parcelable {
    @SerializedName("score")
    private int score;
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

    protected Items(Parcel in) {
        score = in.readInt();
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

    public static final Creator<Items> CREATOR = new Creator<Items>() {
        @Override
        public Items createFromParcel(Parcel in) {
            return new Items(in);
        }

        @Override
        public Items[] newArray(int size) {
            return new Items[size];
        }
    };

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
        dest.writeInt(score);
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
