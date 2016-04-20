package com.example.archanaiyer.hungrypenguin.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.archanaiyer.hungrypenguin.R;

public class PostOrderActivity extends AppCompatActivity {
    private Button callHuman;
    private Button leaveReview;
    private Button payBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_order);

        callHuman = (Button) findViewById(R.id.call_human);
        leaveReview = (Button) findViewById(R.id.leave_review);
        payBill = (Button) findViewById(R.id.pay_bill);
    }

    public void callHuman(View view) {
        Toast.makeText(getApplicationContext(), "Your server has been called!", Toast.LENGTH_LONG).show();
    }

    public void payBill(View view) {
        Toast.makeText(getApplicationContext(), "Your server has been called to give you the bill", Toast.LENGTH_LONG).show();
    }

    public void leaveReview(View view) {
        Intent intent = new Intent(this, ReviewsActivity.class);
        startActivity(intent);
    }
}
