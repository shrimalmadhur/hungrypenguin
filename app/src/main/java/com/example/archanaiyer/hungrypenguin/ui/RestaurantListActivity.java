package com.example.archanaiyer.hungrypenguin.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

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
}
