package com.example.androidbasicproject.model.GithubList;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FollowList implements Parcelable {

    @SerializedName("follow")
    private List<Follow> followList;

    public FollowList(List<Follow> followList) {
        this.followList = followList;
    }

    protected FollowList(Parcel in) {
        followList = in.createTypedArrayList(Follow.CREATOR);
    }

    public static final Creator<FollowList> CREATOR = new Creator<FollowList>() {
        @Override
        public FollowList createFromParcel(Parcel in) {
            return new FollowList(in);
        }

        @Override
        public FollowList[] newArray(int size) {
            return new FollowList[size];
        }
    };

    public List<Follow> getFollowList() {
        return followList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(followList);
    }
}
