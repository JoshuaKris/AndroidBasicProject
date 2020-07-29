package com.example.androidbasicproject.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class CommonUtils {

    public CommonUtils() {
    }

    public static final String BASE_URL = "https://api.github.com/";
    public static final String API_TOKEN = "381bfa69f8be6b049f05239d449ce65f1d91173c";

    public String loadJSONFromAsset(Context context, String fileName) {
        String json;
        try {
            InputStream is = context.getAssets().open(String.format("%1$s",fileName));
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
