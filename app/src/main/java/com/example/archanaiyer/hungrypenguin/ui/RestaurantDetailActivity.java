package com.example.archanaiyer.hungrypenguin.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.archanaiyer.hungrypenguin.R;

public class RestaurantDetailActivity extends AppCompatActivity {
    FragmentPagerAdapter adapterViewPager;
    String title;
    SharedPreferences main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        main = this.getSharedPreferences("main", 1);

        // set title based on whther it has been opened before
        if (savedInstanceState != null) {
            title = savedInstanceState.getString("title");
        } else if (getIntent() != null && getIntent().hasExtra("name")) {
            title = getIntent().getStringExtra("name");
            main.edit().putString("title", title).commit();
        } else {
            title = main.getString("title", "");
        }
        this.setTitle(title);

        // Set up view pager for tabs on top
        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapterViewPager);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewpager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_checkout) {
            Intent i = new Intent(RestaurantDetailActivity.this, CheckoutActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putString("title", title);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void gotoDetail(int id) {
        Intent i = new Intent(RestaurantDetailActivity.this, FoodDetailActivity.class);
        i.putExtra("id", id);
        startActivity(i);
    }


    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;
        private String[] titles = new String[]{"Trending", "Menu", "Interesting"};

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return DishListFragment.newInstance(0);
                case 1:
                    return DishListFragment.newInstance(1);
                case 2:
                    return DishListFragment.newInstance(2);
                default:
                    return null;
            }
        }
    }
}

