package com.example.archanaiyer.hungrypenguin.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.archanaiyer.hungrypenguin.R;
import com.example.archanaiyer.hungrypenguin.adapter.ReviewActivityAdapter;
import com.example.archanaiyer.hungrypenguin.data.DishData;
import com.example.archanaiyer.hungrypenguin.entities.Dish;
import com.example.archanaiyer.hungrypenguin.entities.DishReview;
import com.example.archanaiyer.hungrypenguin.entities.User;
import com.example.archanaiyer.hungrypenguin.util.SharedPrefsHelper;
import com.example.archanaiyer.hungrypenguin.ws.remote.RemoteService;
import com.github.jorgecastilloprz.FABProgressCircle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReviewsActivity extends AppCompatActivity {
    RecyclerView rv;
    ReviewActivityAdapter adapter;
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private List<DishReview> dishes = new ArrayList<>();
    private static String TAG = "ReviewsActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        final Context context = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        FABProgressCircle fabProgressCircle = (FABProgressCircle) findViewById(R.id.fabProgressCircle);
        setSupportActionBar(toolbar);
        rv = (RecyclerView) findViewById(R.id.recycler_view_reviews);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sPref = getApplicationContext().getSharedPreferences("user", 0);
                String profile = sPref.getString("user", "");
                User u = new User();

                try {
                    u.fromJson(new JSONObject(profile));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for(int i=0; i < dishes.size(); i++) {

                    RecyclerView.ViewHolder viewHolder = rv.findViewHolderForPosition(i);
                    EditText editText = (EditText) viewHolder.itemView.findViewById(R.id.review_string);
                    String reviewString = editText.getText().toString();
                    Log.d(TAG, reviewString);
                    DishReview currDish = dishes.get(i);
                    RemoteService.sendReview(currDish.getDishId(), reviewString, u.getUsername(), context);
                }
                Snackbar.make(view, "Review submitted", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        SharedPrefsHelper mSharedPrefsHelper = new SharedPrefsHelper(this);
        Map<Integer, Integer> orders = mSharedPrefsHelper.getOrder();

        for (Integer orderId : orders.keySet()) {
            Dish currDish = DishData.getDish(orderId);

            DishReview dishReview = new DishReview(currDish.name, "test", currDish.id);
            dishes.add(dishReview);
        }


        adapter = new ReviewActivityAdapter(dishes);
        rv.setAdapter(adapter);
    }

    private void addDrawerItems() {
        String[] osArray = { "Android", "iOS", "Windows", "OS X", "Linux" };
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
    }
}
