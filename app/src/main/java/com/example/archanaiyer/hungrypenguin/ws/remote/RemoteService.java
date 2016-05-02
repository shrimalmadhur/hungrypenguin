package com.example.archanaiyer.hungrypenguin.ws.remote;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.archanaiyer.hungrypenguin.data.DishData;
import com.example.archanaiyer.hungrypenguin.entities.Dish;
import com.example.archanaiyer.hungrypenguin.entities.Restaurant;
import com.example.archanaiyer.hungrypenguin.entities.RestaurantList;
import com.example.archanaiyer.hungrypenguin.entities.User;
import com.example.archanaiyer.hungrypenguin.ui.LoginActivity;
import com.example.archanaiyer.hungrypenguin.ui.RestaurantDetailActivity;
import com.example.archanaiyer.hungrypenguin.ui.RestaurantListActivity;
import com.loopj.android.http.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


/**
 * Created by archanaiyer on 4/12/16.
 */
public class RemoteService {
    private static final String TAG = "RemoteService";

    public static void login(String username, String password, final Context context) {
        SyncHttpClient client = new SyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("username", username);
        params.add("password", password);
        client.post(NetworkUtils.getEndPoint(NetworkConstants.LOGIN), params, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String s = new String(response);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(s);
                    String result = jsonObject.getString("user");
                    JSONObject userJSON = new JSONObject(result);
                    User u = new User();
                    u.fromJson(userJSON);

                    Log.i(TAG, result);

                    SharedPreferences sPref = context.getSharedPreferences("user", 0);
                    SharedPreferences.Editor editor = sPref.edit();
                    editor.putString("user", u.toJson().toString());

                    // call all restaurants
                    RemoteService.getAllRestaurants(context);


                } catch (JSONException e) {
                    Log.i(TAG, "Not Logged In");
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d(TAG, "Login Service is not up");
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }

    public static void getAllRestaurants(final Context context) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(NetworkUtils.getEndPoint(NetworkConstants.ALL_RESTAURANTS), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String responseString = new String(responseBody);
                JSONObject responseObj;
                try {
                    responseObj = new JSONObject(responseString);

                    // TODO: prepare this list from responseObj
                    List<Restaurant> restaurantList = new ArrayList<Restaurant>();
                    RestaurantList rList = new RestaurantList(restaurantList);
                    Intent intent = new Intent(context, RestaurantListActivity.class);
                    intent.putExtra("restaurants", rList);

                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d(TAG, "Get all restaurants Service is not up");
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }

    public static void getRestaurant(final Context context, int restaurantId) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("id", String.valueOf(restaurantId));
        client.get(NetworkUtils.getEndPoint(NetworkConstants.RESTAURANT), params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String responseString = new String(responseBody);
                try {
                    JSONObject responseObj = new JSONObject(responseString);
                    // TODO: get Dishes from response

                    // dynamic dish data
                    List<Dish> dishes = new ArrayList<Dish>();
                    DishData dishData = new DishData(dishes);

                    Intent intent = new Intent(context, RestaurantDetailActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i(TAG, "Get particular restaurant Service is not up");
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }

    public static void register(User user, final Context context) {
        AsyncHttpClient client = new AsyncHttpClient();

        RequestParams params = new RequestParams();
        params.add("username", user.getUsername());
        params.add("password", user.getPassword());
        params.add("firstname", user.getFirstName());
        params.add("lastname", user.getLastName());
        params.add("email", user.getEmail());
        params.add("facebookId", user.getFacebookId());

        Log.i(TAG, "hitting url: " + NetworkUtils.getEndPoint(NetworkConstants.REGISTER));
        client.post(NetworkUtils.getEndPoint(NetworkConstants.REGISTER), params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                // TODO: if registration is successful then redirect to Login Activity;
                boolean isRegistered = true; // TODO: change this logic to get boolean
                if (isRegistered) {
                    String response = new String(responseBody);
                    JSONObject responseObject = null;
                    try {
                        responseObject = new JSONObject(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, response);
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } else {
                    // TODO: notify by Toast
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d(TAG, "Register Service is not up");
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }

    public static void getProfile(String username) {
        SyncHttpClient client = new SyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("id", username);
        client.get(NetworkUtils.getEndPoint(NetworkConstants.PROFILE), params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d(TAG, "Get profile Service is not up");
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }


    public static void getOrdersHistory(String username) {
        SyncHttpClient client = new SyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("username", username);
        client.get(NetworkUtils.getEndPoint(NetworkConstants.HISTORY), params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d(TAG, "Get Order History Service is not up");
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }

    public static void getReviews(int dishId) {
        SyncHttpClient client = new SyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("id", String.valueOf(dishId));
        client.get(NetworkUtils.getEndPoint(NetworkConstants.REVIEWS), params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d(TAG, "Get reviews Service is not up");
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }


    public static void sendReview(int dishId, String review) {
        SyncHttpClient client = new SyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("id", String.valueOf(dishId));
        params.add("review", review);
        client.post(NetworkUtils.getEndPoint(NetworkConstants.REVIEW), params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d(TAG, "Send Review Service is not up");
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }
}