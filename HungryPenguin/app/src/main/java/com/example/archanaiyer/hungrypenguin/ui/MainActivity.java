package com.example.archanaiyer.hungrypenguin.ui;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.archanaiyer.hungrypenguin.R;

public class MainActivity extends AppCompatActivity {
    FragmentPagerAdapter adapterViewPager;
    String title;
    SharedPreferences main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        main = this.getSharedPreferences("main", 1);

        if (savedInstanceState != null) {
            title = savedInstanceState.getString("title");
        } else if (getIntent() != null && getIntent().hasExtra("name")) {
            title = getIntent().getStringExtra("name");
            main.edit().putString("title", title).commit();
        } else {
            title = main.getString("title", "");
        }
        this.setTitle(title);
        // Set up view pager
//        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
//        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
//        viewpager.setAdapter(adapterViewPager);
//
//        // Give the TabLayout the ViewPager
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewpager);
    }
}
