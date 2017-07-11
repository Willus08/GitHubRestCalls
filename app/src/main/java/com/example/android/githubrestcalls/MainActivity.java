package com.example.android.githubrestcalls;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "inputs";
    private String site = "https://api.github.com/users/";
    String Url;
    Bitmap bmp;
    Reciver reciver = new Reciver();
    IntentFilter filter = new IntentFilter();
    TextView textViewN;
    TextView textViewE;
    TextView textViewD;
    TextView textViewF;

    private EditText search;
    private ImageView profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = (EditText) findViewById(R.id.etUser);
        profile = (ImageView) findViewById(R.id.ivProfile);
        textViewN = (TextView) findViewById(R.id.tvName);
        textViewE = (TextView) findViewById(R.id.tvEmail);
        textViewD = (TextView) findViewById(R.id.tvDate);
        textViewF = (TextView) findViewById(R.id.tvFolow);
    }

    public void Search(View view) {
        String user = site + search.getText().toString();
        Intent okHttp = new Intent(this, MyIntentService.class);
        okHttp.putExtra("url" , user);
        startService(okHttp);




    }

    @Override
    protected void onStart() {
        super.onStart();
        filter.addAction("send");
        registerReceiver(reciver,filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(reciver);
    }

    public void Repose(View view) {
        Intent intent = new Intent(this,Repos.class);
        intent.putExtra("need", search.getText().toString());

        startActivity(intent);
    }

    class Reciver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
             Users users = (Users) bundle.getSerializable("sender");
            String name = users.getName().toString();
            final String avatar = users.getAvatarUrl();
            Url = users.getUrl();
            String created = users.getCreatedAt();
            String folowers = ""+users.getFollowers();



            try {
                final URL url = new URL(users.getAvatarUrl());
                Runnable run = new Runnable() {
                    @Override
                    public void run() {
                        try {
                             bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                };
                Thread thread = new Thread(run);
                thread.start();
                profile.setImageBitmap(bmp);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            profile.refreshDrawableState();
            textViewN.setText("Name: " + name);
            textViewE.setText("URL: " + Url);
            textViewD.setText("Date Created: " + created);
            textViewF.setText("Folowers: " + folowers);
            Log.d(TAG, "onReceive: " + name + avatar + created + folowers );

        }
    }
}
