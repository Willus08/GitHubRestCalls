package com.example.android.githubrestcalls;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import static android.content.ContentValues.TAG;


public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String site = intent.getStringExtra("url");
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(site)
                .build();


        try {
            String result =client.newCall(request).execute().body().string();
            Gson gson = new Gson();
            Users users = gson.fromJson(result,Users.class);
            Log.d(TAG, "Set: " + users.getName()  + users.getAvatarUrl() );



            Bundle bundle = new Bundle();

            bundle.putSerializable("sender" , users);

            Intent send = new Intent("send");

            send.putExtras(bundle);
            sendBroadcast(send);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
 }



