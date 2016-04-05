package com.example.archanaiyer.hungrypenguin.ui;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.archanaiyer.hungrypenguin.R;
import com.example.archanaiyer.hungrypenguin.adapter.RVSampleAdapter;
import com.example.archanaiyer.hungrypenguin.data.RestaurantData;
import com.example.archanaiyer.hungrypenguin.model.Restaurant;
import com.example.archanaiyer.hungrypenguin.util.SharedPrefsHelper;

import java.util.List;

public class RestaurantListActivity extends AppCompatActivity {

    private RecyclerView rv;
    //final LinearLayoutManager llm;
    RVSampleAdapter adapter;
    List<Restaurant> restaurants;
    private final String TAG = "SampleActivity";

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        DrawerLayout d = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView)findViewById(R.id.navList);
        addDrawerItems();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // clear the orderlist
        SharedPrefsHelper mSharedPrefs = new SharedPrefsHelper(this);
        mSharedPrefs.clear();

        rv = (RecyclerView) findViewById(R.id.reView);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        RestaurantData rd = new RestaurantData();
        List<Restaurant> restaurants = rd.getRestaurants();

        adapter = new RVSampleAdapter(restaurants, this);
        //adapter.setItemClickListener(this);
        rv.setAdapter(adapter);

    }

    private void addDrawerItems() {
        String[] osArray = { "Android", "iOS", "Windows", "OS X", "Linux" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    public void showProfile(MenuItem item) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void showHistory(MenuItem item) {
        Intent intent = new Intent(this, OrderHistoryActivity.class);
        startActivity(intent);
    }
}
