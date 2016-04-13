package com.example.archanaiyer.hungrypenguin.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.archanaiyer.hungrypenguin.R;
import com.example.archanaiyer.hungrypenguin.ws.local.FacebookHelper;
import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;

public class ProfileActivity extends AppCompatActivity {
    ProfilePictureView profilePictureView;
    TextView username;

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
            // TODO: get profile details from database

        }
    }
}