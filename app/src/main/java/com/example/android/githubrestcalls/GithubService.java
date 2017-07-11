package com.example.android.githubrestcalls;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by singh on 7/10/17.
 */

public interface GithubService {

    @GET("users/{user}/repos")
    Call<List<Repositories>> callProfle(@Path("user") String user);

}
