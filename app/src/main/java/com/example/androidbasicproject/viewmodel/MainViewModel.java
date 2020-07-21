package com.example.androidbasicproject.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidbasicproject.model.GithubUsers;
import com.example.androidbasicproject.utils.CommonUtils;
import com.google.gson.Gson;

public class MainViewModel extends ViewModel {

    private MutableLiveData<GithubUsers> itemList = new MutableLiveData<>();

    private final Context context;
    private CommonUtils commonUtils = new CommonUtils();

    public MainViewModel(Context context) {
        this.context = context;
    }

    public MutableLiveData<GithubUsers> getItemList() {
        return itemList;
    }

    @SuppressLint("DefaultLocale")
    public void fetchItemList() {
        String r = commonUtils.loadJSONFromAsset(context, "githubuser.json");
        if (!r.isEmpty()) {
            try {
                Gson gson = new Gson();
                GithubUsers result = gson.fromJson(r,GithubUsers.class);
                itemList.setValue(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
