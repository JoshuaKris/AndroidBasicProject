package com.example.androidbasicproject.model.GithubList;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.androidbasicproject.model.GithubModel.Items;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FollowList implements Parcelable {

    @SerializedName("follow")
    private List<Items> followList;

    public FollowList(List<Items> followList) {
        this.followList = followList;
    }

    protected FollowList(Parcel in) {
        followList = in.createTypedArrayList(Items.CREATOR);
    }

    public static Creator<FollowList> getCREATOR() {
        return CREATOR;
    }

    public List<Items> getFollowList() {
        return followList;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(followList);
    }
}
