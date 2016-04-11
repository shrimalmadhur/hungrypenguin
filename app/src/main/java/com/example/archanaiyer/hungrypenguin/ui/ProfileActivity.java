package com.example.archanaiyer.hungrypenguin.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.archanaiyer.hungrypenguin.R;
import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;

public class ProfileActivity extends AppCompatActivity {
    ProfilePictureView profilePictureView;
    TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Profile profile = Profile.getCurrentProfile();
        System.out.println(profile.getFirstName());
        System.out.println(profile.getId());

        profilePictureView = (ProfilePictureView) findViewById(R.id.profile_image);
        profilePictureView.setProfileId(profile.getId());

        username = (TextView)findViewById(R.id.username);
        username.setText(profile.getFirstName());
    }
}
