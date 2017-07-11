package com.example.android.githubrestcalls;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repos extends AppCompatActivity {

    private static final String TAG = "hi";
    private String site = "https://api.github.com/";
    private String use;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private DefaultItemAnimator defaultItemAnimator;
    private RecyclerViewAdapter adapt;
    private List<Projects> projects = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);
        Intent i = getIntent();
        use = i.getStringExtra("need");
        Log.d(TAG, "onCreate: " + use);
        recyclerView = (RecyclerView) findViewById(R.id.menu);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(site)
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        layoutManager = new LinearLayoutManager(this);
        defaultItemAnimator = new DefaultItemAnimator();
        GithubService githubService = retrofit.create(GithubService.class);
        retrofit2.Call<List<Repositories>> callToGetRepos = githubService.callProfle(use);

        callToGetRepos.enqueue(new retrofit2.Callback<List<Repositories>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Repositories>> call, retrofit2.Response<List<Repositories>> response) {


                for (int i = 0; i < 5; i++) {
                    Log.d(TAG, "onResponse: " + response.body().get(i).getName() ) ;
                    projects.add(new Projects(response.body().get(i).getName(), "" +response.body().get(i).getId() , response.body().get(i).getCommitsUrl(), response.body().get(i).getUpdatedAt()));

                }

                adapt = new RecyclerViewAdapter(projects);



                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(defaultItemAnimator);
                recyclerView.setAdapter(adapt);

            }

            @Override
            public void onFailure(retrofit2.Call<List<Repositories>> call, Throwable t) {

            }
        });






    }


}
