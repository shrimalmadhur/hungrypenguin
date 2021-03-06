package com.example.archanaiyer.hungrypenguin.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.archanaiyer.hungrypenguin.R;
import com.example.archanaiyer.hungrypenguin.adapter.CheckoutActivityAdapter;
import com.example.archanaiyer.hungrypenguin.data.DishData;
import com.example.archanaiyer.hungrypenguin.entities.Checkout;
import com.example.archanaiyer.hungrypenguin.entities.Dish;
import com.example.archanaiyer.hungrypenguin.entities.Order;
import com.example.archanaiyer.hungrypenguin.util.MockAction;
import com.example.archanaiyer.hungrypenguin.util.MockActionCallback;
import com.example.archanaiyer.hungrypenguin.util.SharedPrefsHelper;
import com.example.archanaiyer.hungrypenguin.util.ThreadExecutor;
import com.github.jorgecastilloprz.FABProgressCircle;
import com.github.jorgecastilloprz.listeners.FABProgressListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by archanaiyer on 4/4/16.
 */
public class CheckoutActivity extends AppCompatActivity implements MockActionCallback, FABProgressListener {
    private FABProgressCircle fabProgressCircle;
    private boolean taskRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();
        attachListeners();
        //final Intent i = new Intent(CheckoutActivity.this, PostOrderActivity.class);

        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view_checkout);


        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);

        List<Checkout> myOrders = new ArrayList<Checkout>();

        SharedPrefsHelper mSharedPrefsHelper = new SharedPrefsHelper(this);
        Map<Integer, Integer> orders = mSharedPrefsHelper.getOrder();

        for (Integer orderId : orders.keySet()) {
            Dish currDish = DishData.getDish(orderId);
            int qty = orders.get(orderId);

            Checkout currCh = new Checkout(currDish.imageUrl, currDish.name, currDish.cost, qty);
            myOrders.add(currCh);
        }
//        mSharedPrefsHelper.clear();

        Order myOrder = new Order(myOrders);
        CheckoutActivityAdapter adapter = new CheckoutActivityAdapter(myOrder.getOrderList());
        rv.setAdapter(adapter);
        final TextView totalPrice = (TextView) findViewById(R.id.totalPrice);
        totalPrice.setText(String.valueOf("$" + myOrder.getTotalPrice()));
    }

    private void initViews() {
        fabProgressCircle = (FABProgressCircle) findViewById(R.id.fabProgressCircle);
    }

    private void attachListeners() {
        fabProgressCircle.attachListener(this);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!taskRunning) {
                    fabProgressCircle.show();
                    runMockInteractor();
                }
            }
        });
    }

    private void runMockInteractor() {
        ThreadExecutor executor = new ThreadExecutor();
        executor.run(new MockAction(this));
        taskRunning = true;
    }

    @Override
    public void onMockActionComplete() {
        taskRunning = false;
        fabProgressCircle.beginFinalAnimation();
        //fabProgressCircle.hide();
    }

    @Override
    public void onFABProgressAnimationEnd() {
        Snackbar.make(fabProgressCircle, "Order Placed!", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show();
        final Intent i = new Intent(CheckoutActivity.this, PostOrderActivity.class);

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    startActivity(i);
                }
            }
        };
        timerThread.start();

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

    public void logout(MenuItem item) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}