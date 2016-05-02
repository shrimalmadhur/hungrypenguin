package com.example.archanaiyer.hungrypenguin.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.archanaiyer.hungrypenguin.R;
import com.example.archanaiyer.hungrypenguin.entities.Restaurant;
import com.example.archanaiyer.hungrypenguin.ui.RestaurantDetailActivity;
import com.example.archanaiyer.hungrypenguin.ws.remote.RemoteService;

import java.util.List;

/**
 * Created by archanaiyer on 4/4/16.
 */
public class RVSampleAdapter extends RecyclerView.Adapter<RVSampleAdapter.SampleHolder> {

    List<Restaurant> restaurants;
    Context context;

    public class SampleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RelativeLayout rv;
        ImageView thumbnail;
        TextView title;
        TextView address;
        TextView dollar;
        Context c;

        public SampleHolder(View itemView, int viewType, Context cntxt) {
            super(itemView);
            this.c = cntxt;
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
            rv = (RelativeLayout) itemView.findViewById(R.id.restaurantrv);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            title = (TextView) itemView.findViewById(R.id.title);
            address = (TextView) itemView.findViewById(R.id.address);
            dollar = (TextView) itemView.findViewById(R.id.dollar);
        }


        @Override
        public void onClick(View v) {
            //Toast.makeText(context, restaurants.get(getPosition()).name, Toast.LENGTH_SHORT).show();
            RemoteService.getRestaurant(c, restaurants.get(getPosition()).getId());

        }
    }

    public RVSampleAdapter(List<Restaurant> restaurants, Context passedContext) {
        this.restaurants = restaurants;
        this.context = passedContext;
    }

    @Override
    public SampleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.restaurant_card, parent, false);
        SampleHolder sh = new SampleHolder(v, viewType, context);
        return sh;
    }

    @Override
    public void onBindViewHolder(SampleHolder holder, final int position) {
        holder.title.setText(restaurants.get(position).getName());
        holder.address.setText(restaurants.get(position).getAddress());
        holder.dollar.setText(restaurants.get(position).getDollar());
//        holder.thumbnail.setImageURI(Uri.parse(restaurants.get(position).imageUrl));
        Glide.with(holder.itemView.getContext())
                .load(restaurants.get(position).getImage())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
