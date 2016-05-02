package com.example.archanaiyer.hungrypenguin.ui;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.archanaiyer.hungrypenguin.R;
import com.example.archanaiyer.hungrypenguin.entities.User;
import com.example.archanaiyer.hungrypenguin.ws.local.FacebookHelper;
import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {
    ProfilePictureView profilePictureView;
    TextView username;
    TextView email;

    private static String TAG = "ProfileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FacebookHelper facebookHelper = new FacebookHelper();
        boolean loggedIn = facebookHelper.isFacebookLoggedIn();

        if (loggedIn) {
            Profile profile = Profile.getCurrentProfile();

            profilePictureView = (ProfilePictureView) findViewById(R.id.profile_image);
            profilePictureView.setProfileId(profile.getId());

            username = (TextView) findViewById(R.id.username);
            username.setText(profile.getFirstName() + " " + profile.getLastName());

        } else {
            SharedPreferences sPref = getApplicationContext().getSharedPreferences("user", 0);
            String profile = sPref.getString("user", "");
            Log.d(TAG, profile);
            try {
                JSONObject obj = new JSONObject(profile);
                User u = new User();
                u.fromJson(obj);

                username = (TextView) findViewById(R.id.username);
                username.setText(u.getFirstName() + " " + u.getLastName());

                email = (TextView) findViewById(R.id.profile_email);
                email.setText(u.getEmail());

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}