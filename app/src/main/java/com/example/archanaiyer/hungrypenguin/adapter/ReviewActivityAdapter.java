package com.example.archanaiyer.hungrypenguin.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.archanaiyer.hungrypenguin.entities.DishReview;

import java.util.List;

/**
 * Created by Knock on 4/30/16.
 */
public class ReviewActivityAdapter extends RecyclerView.Adapter<ReviewActivityAdapter.DishReviewsItemHolder>{


    List<DishReview> dishReviews;

    public static class DishReviewsItemHolder extends RecyclerView.ViewHolder {

        public DishReviewsItemHolder(View itemView) {
            super(itemView);
        }
    }
    @Override
    public ReviewActivityAdapter.DishReviewsItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(DishReviewsItemHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
