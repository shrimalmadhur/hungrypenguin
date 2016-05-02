package com.example.archanaiyer.hungrypenguin.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.archanaiyer.hungrypenguin.R;
import com.example.archanaiyer.hungrypenguin.entities.Dish;
import com.example.archanaiyer.hungrypenguin.ws.remote.RemoteService;

public class FoodDetailActivity extends AppCompatActivity {
    TextView dishName;
    ImageView dishImage;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        dishName = (TextView) findViewById(R.id.dish_name);
        dishImage = (ImageView) findViewById(R.id.dish_image);
        ratingBar = (RatingBar) findViewById(R.id.rating_bar);

        Dish d = (Dish) getIntent().getExtras().get("dish");
        dishName.setText(d.name);

        RemoteService.getReviews(d.id, this);

        Glide.with(getApplicationContext()).load(d.imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(dishImage);

        ratingBar.setRating(d.rating);
    }

    public void showProfile(MenuItem item) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void showHistory(MenuItem item) {
        Intent intent = new Intent(this, OrderHistoryActivity.class);
        startActivity(intent);
    }

    public void logout(MenuItem item) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
