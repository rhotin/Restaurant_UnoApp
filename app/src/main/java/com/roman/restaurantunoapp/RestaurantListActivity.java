package com.roman.restaurantunoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class RestaurantListActivity extends AppCompatActivity implements DownloadTask.Communicator{

    public static final String UNOAPP_URL = "http://api.digitalmarketingbox.com/list_companies?api_id=UNOappTest&api_secret=20c8be7c56a98a9a9746b6a0f5558c47";
    ListView restListView;
    ProgressBar progressBar;
    TextView messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        restListView = (ListView) findViewById(R.id.listView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        messageText = (TextView) findViewById(R.id.textView);

        DownloadTask downloadTask = new DownloadTask(this);
        downloadTask.execute();

        restListView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        messageText.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateProgressTo(int progress) {
        progressBar.setProgress(progress);
    }

    @Override
    public void updateUI(final ArrayList<RestaurantObject> restaurantsArrayList) {
        restListView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        messageText.setVisibility(View.GONE);

        RestaurantAdapter adapter = new RestaurantAdapter(this, restaurantsArrayList);
        restListView.setAdapter(adapter);


        restListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                RestaurantObject objectToPass = restaurantsArrayList.get(position);

                Intent intent = new Intent(RestaurantListActivity.this, RestaurantDetailActivity.class);
                intent.putExtra("theRestObject", objectToPass);
                startActivity(intent);
            }
        });
    }
}
