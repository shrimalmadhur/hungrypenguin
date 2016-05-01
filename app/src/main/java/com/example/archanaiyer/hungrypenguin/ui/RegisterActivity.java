package com.example.archanaiyer.hungrypenguin.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.archanaiyer.hungrypenguin.R;
import com.example.archanaiyer.hungrypenguin.entities.User;
import com.example.archanaiyer.hungrypenguin.util.ValidationHelper;
import com.example.archanaiyer.hungrypenguin.ws.remote.RemoteService;

public class RegisterActivity extends AppCompatActivity {

    private EditText mUserName;
    private EditText mPassword;
    private EditText mFirstName;
    private EditText mLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button mRegisterButton = (Button) findViewById(R.id.register_button);
        final Context mContext = this;
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserName = (EditText) findViewById(R.id.register_username);
                mPassword = (EditText) findViewById(R.id.register_password);
                mFirstName = (EditText) findViewById(R.id.register_firstname);
                mLastName = (EditText) findViewById(R.id.register_lastname);

                String username = mUserName.getText().toString();
                String password = mPassword.getText().toString();
                String firstName = mFirstName.getText().toString();
                String lastName = mLastName.getText().toString();

                if (!TextUtils.isEmpty(password) && !ValidationHelper.isPasswordValid(password)) {
                    mPassword.setError(getString(R.string.error_invalid_password));
                    mPassword.requestFocus();
                    return;
                }

                // Check for a valid email address.
                if (TextUtils.isEmpty(username)) {
                    mUserName.setError(getString(R.string.error_field_required));
                    mUserName.requestFocus();
                    return;
                } else if (!ValidationHelper.isEmailValid(username)) {
                    mUserName.setError(getString(R.string.error_invalid_email));
                    mUserName.requestFocus();
                    return;
                }

                if(!TextUtils.isEmpty(firstName)){
                    mFirstName.setError("This field is required");
                    mFirstName.requestFocus();
                }

                if(!TextUtils.isEmpty(lastName)){
                    mLastName.setError("This field is required");
                    mLastName.requestFocus();
                }

                User user = new User(username, firstName, lastName, password, username, null);


                RemoteService.register(user,  mContext);
            }
        });
    }
}
