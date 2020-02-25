package com.pressure.githubinfo.client;

import com.pressure.githubinfo.model.GitRepo;
import com.pressure.githubinfo.model.GitUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitReposEndPoint {
    @GET("users/{user}/repos")
    Call<List<GitRepo>> getRepos(@Path("user") String user);
}
