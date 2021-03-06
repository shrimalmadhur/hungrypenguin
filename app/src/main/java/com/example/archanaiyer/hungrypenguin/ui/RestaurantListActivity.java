package com.example.archanaiyer.hungrypenguin.ui;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.archanaiyer.hungrypenguin.R;
import com.example.archanaiyer.hungrypenguin.adapter.RVSampleAdapter;
import com.example.archanaiyer.hungrypenguin.entities.Restaurant;
import com.example.archanaiyer.hungrypenguin.entities.RestaurantList;
import com.example.archanaiyer.hungrypenguin.entities.User;
import com.example.archanaiyer.hungrypenguin.util.SharedPrefsHelper;
import com.example.archanaiyer.hungrypenguin.ws.remote.RemoteService;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;

public class RestaurantListActivity extends AppCompatActivity {

    private RecyclerView rv;
    //final LinearLayoutManager llm;
    RVSampleAdapter adapter;
    List<Restaurant> restaurants;
    private final String TAG = "RestaurantListActivity";

    //NFC stuff
    private NfcAdapter mNfcAdapter;
    private PendingIntent mPendingIntent;
    private NdefMessage mNdefPushMessage;


    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        mDrawerList = (ListView)findViewById(R.id.navList);
        addDrawerItems();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get restaurants List from Intent
        RestaurantList rList = (RestaurantList) getIntent().getExtras().get("restaurants");
        restaurants = rList.getRestaurantList();

        // clear the orderlist
        SharedPrefsHelper mSharedPrefs = new SharedPrefsHelper(this);
        mSharedPrefs.clear();

        resolveIntent(getIntent());
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (mAdapter == null) {
            Log.d(TAG, "NFC adapter null error");
            finish();
            return;
        }else{
            Log.d(TAG, "NFC Adapter is NOT null");
        }

        mPendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        mNdefPushMessage = new NdefMessage(new NdefRecord[] { newTextRecord(
                "Message from NFC Reader :-)", Locale.ENGLISH, true) });

        rv = (RecyclerView) findViewById(R.id.reView);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        adapter = new RVSampleAdapter(restaurants, this);
        rv.setAdapter(adapter);

    }

    private NdefRecord newTextRecord(String text, Locale locale, boolean encodeInUtf8) {
        byte[] langBytes = locale.getLanguage().getBytes(Charset.forName("US-ASCII"));

        Charset utfEncoding = encodeInUtf8 ? Charset.forName("UTF-8") : Charset.forName("UTF-16");
        byte[] textBytes = text.getBytes(utfEncoding);

        int utfBit = encodeInUtf8 ? 0 : (1 << 7);
        char status = (char) (utfBit + langBytes.length);

        byte[] data = new byte[1 + langBytes.length + textBytes.length];
        data[0] = (byte) status;
        System.arraycopy(langBytes, 0, data, 1, langBytes.length);
        System.arraycopy(textBytes, 0, data, 1 + langBytes.length, textBytes.length);

        return new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], data);
    }

    private void addDrawerItems() {
        String[] osArray = { "Android", "iOS", "Windows", "OS X", "Linux" };
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    private void resolveIntent(Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "IN RESOLVE INTENT fn " + action);
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage[] msgs;
            if (rawMsgs != null) {
                msgs = new NdefMessage[rawMsgs.length];
                for (int i = 0; i < rawMsgs.length; i++) {
                    msgs[i] = (NdefMessage) rawMsgs[i];
                }
            } else {

                Log.d(TAG, "NFC discovered");
                RemoteService.getRestaurant(getApplicationContext(), 1, "The Sandwich Spot");
                Intent i = new Intent(this, RestaurantDetailActivity.class);
                i.putExtra("name", "The Sandwich Spot");
                startActivity(i);
            }
        }
    }


    public void showProfile(MenuItem item) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void showHistory(MenuItem item) {
        SharedPreferences sPref = getApplicationContext().getSharedPreferences("user", 0);
        String profile = sPref.getString("user", "");
        User u = new User();
        try {
            u.fromJson(new JSONObject(profile));
            RemoteService.getOrdersHistory(this, u.getUsername());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void logout(MenuItem item) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mNfcAdapter != null) {
            if (!mNfcAdapter.isEnabled()) {
                Log.d(TAG, "NFC Adapter is NOT ENABLED");
            }
            Log.d(TAG, "NFC Adapter is ENABLED in ONRESUME");
            mNfcAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mNfcAdapter != null) {
            mNfcAdapter.disableForegroundDispatch(this);
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        setIntent(intent);
        resolveIntent(intent);
    }
}
