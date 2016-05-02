package com.example.archanaiyer.hungrypenguin.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.archanaiyer.hungrypenguin.R;
import com.example.archanaiyer.hungrypenguin.entities.DishReview;

import java.util.List;

/**
 * Created by Knock on 4/30/16.
 */
public class ReviewActivityAdapter extends RecyclerView.Adapter<ReviewActivityAdapter.DishReviewsItemHolder>{

    List<DishReview> dishReviews;

    public static class DishReviewsItemHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView dishName;
        EditText review;

        public DishReviewsItemHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.review_cv);
            dishName = (TextView) itemView.findViewById(R.id.review_dish);
            review = (EditText) itemView.findViewById(R.id.review_string);
        }
    }


    public ReviewActivityAdapter(List<DishReview> dishReviews) {
        this.dishReviews = dishReviews;
    }

    @Override
    public DishReviewsItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.review_card, parent, false);
        DishReviewsItemHolder sh = new DishReviewsItemHolder(v);
        return sh;
    }

    @Override
    public void onBindViewHolder(DishReviewsItemHolder holder, int position) {
        DishReview currDish = dishReviews.get(position);

        holder.dishName.setText(currDish.getDishName());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
