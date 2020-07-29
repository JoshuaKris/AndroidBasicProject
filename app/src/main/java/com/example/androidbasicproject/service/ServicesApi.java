package com.example.androidbasicproject.service;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServicesApi {

    @GET("search/users")
    Call<String> getUsers(
            @Query("q") String name,
            @Query("per_page") int totalPerPage
    );

    @GET("users/{name}")
    Call<String> getUserDetail(
            @Path("name") String name
    );

    @GET("users/{name}/followers")
    Call<String> getUserFollower(
            @Path("name") String name
    );

    @GET("users/{name}/following")
    Call<String> getUserFollowing(
            @Path("name") String name
    );

}
