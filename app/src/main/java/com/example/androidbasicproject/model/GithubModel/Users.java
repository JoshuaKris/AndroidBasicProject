package com.example.androidbasicproject.model.GithubModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Users implements Parcelable {

    @SerializedName("items")
    private List<Items> items;
    @SerializedName("incomplete_results")
    private boolean incompleteResults;
    @SerializedName("total_count")
    private int totalCount;

    protected Users(Parcel in) {
        items = in.createTypedArrayList(Items.CREATOR);
        incompleteResults = in.readByte() != 0;
        totalCount = in.readInt();
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

    public List<Items> getItems() {
        return items;
    }

    public boolean getIncompleteResults() {
        return incompleteResults;
    }

    public int getTotalCount() {
        return totalCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(items);
        dest.writeByte((byte) (incompleteResults ? 1 : 0));
        dest.writeInt(totalCount);
    }
}
