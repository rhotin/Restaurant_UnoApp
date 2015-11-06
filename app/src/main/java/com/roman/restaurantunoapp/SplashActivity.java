package com.roman.restaurantunoapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity implements DownloadTask.Communicator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        int secondsDelayed = 2;
        DownloadTask downloadTask = new DownloadTask(this);
        downloadTask.execute();
        new Handler().postDelayed(new Runnable(){
            public void run() {
                startActivity(new Intent(SplashActivity.this, RestaurantListActivity.class));
                finish();
            }
        }, secondsDelayed *1000);
    }

    @Override
    public void updateProgressTo(int progress) {

    }

    @Override
    public void updateUI(ArrayList<RestaurantObject> photosArrayList) {

    }
}
