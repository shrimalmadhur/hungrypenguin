package com.example.archanaiyer.hungrypenguin.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.archanaiyer.hungrypenguin.R;
import com.example.archanaiyer.hungrypenguin.data.DishData;
import com.example.archanaiyer.hungrypenguin.model.Dish;
import com.example.archanaiyer.hungrypenguin.util.DetailEventHandler;
import com.example.archanaiyer.hungrypenguin.util.PrefsEventHandler;
import com.example.archanaiyer.hungrypenguin.util.SharedPrefsHelper;

import java.util.List;

/**
 * Created by archanaiyer on 4/4/16.
 */
public class DishesAdapter extends RecyclerView.Adapter<DishesAdapter.ViewHolder> {
    private List<Dish> dishes;
    private SharedPrefsHelper sharedPrefsHelper;
    private PrefsEventHandler handler;
    private DetailEventHandler detailHandler;

    public DishesAdapter(List<Dish> data, SharedPrefsHelper helper,
                         PrefsEventHandler handler, DetailEventHandler detailHandler) {
        this.detailHandler = detailHandler;
        dishes = data;
        sharedPrefsHelper = helper;
        this.handler = handler;
    }

    @Override
    public DishesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.dish_order_card, parent, false);
        DishesAdapter.ViewHolder vh = new DishesAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(DishesAdapter.ViewHolder holder, int position) {
        Dish dish = dishes.get(position);
        holder.title.setTag(dish.id);
        holder.title.setText(dish.name);
        holder.cost.setText(Double.toString(dish.cost));
        holder.cost.setTag(dish.id);
        holder.stats.setText(dish.trendingStats + " Penguins bought this");

        int qty = sharedPrefsHelper.getValue(dish.id);
        if (qty > 0) {
            holder.cost.setText("$" + Double.toString(dish.cost) + "(" + String.valueOf(qty) + ")");
        } else {
            holder.cost.setText("$" + Double.toString(dish.cost));
        }

        Uri uri = Uri.parse(dish.imageUrl);

        final Context mContext = holder.thumbnail.getContext();
        Glide.with(mContext).load(uri).asGif().into(holder.thumbnail);

        holder.cost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPrefsHelper.updateOrder((Integer) v.getTag(), 1);
                int qty = sharedPrefsHelper.getValue((Integer) v.getTag());
                if (qty > 0) {
                    Button b = (Button) v;
                    Dish d = DishData.getDish((Integer) v.getTag());
                    b.setText("$" + d.cost + "(" + String.valueOf(qty) + ")");
                }
            }
        });

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailHandler.gotoDetail((Integer) v.getTag());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail;
        TextView title;
        TextView stats;
        Button cost;

        public ViewHolder(View itemView) {
            super(itemView);
            stats = (TextView) itemView.findViewById(R.id.stats);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            title = (TextView) itemView.findViewById(R.id.title);
            cost = (Button) itemView.findViewById(R.id.costButton);
        }
    }
}
