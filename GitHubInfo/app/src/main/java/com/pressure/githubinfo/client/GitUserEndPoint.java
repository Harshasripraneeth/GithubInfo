package com.pressure.githubinfo.client;

import com.pressure.githubinfo.model.GitUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitUserEndPoint {

    @GET("users/{user}")
    Call<GitUser> getUser(@Path("user") String user);
}
