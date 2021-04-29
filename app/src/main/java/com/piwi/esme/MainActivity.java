package com.piwi.esme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

public class MainActivity extends AppCompatActivity {

    private TextView info;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    ShareButton btn_link;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FacebookSdk.sdkInitialize(getApplicationContext());

        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_main);
        info = (TextView)findViewById(R.id.info);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        btn_link=findViewById(R.id.btn_link);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                info.setText("User ID: " + loginResult.getAccessToken().getUserId() + "\n" + "Auth Token: " + loginResult.getAccessToken().getToken());
            }

            @Override
            public void onCancel() {
                info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException e) {
                info.setText("Login attempt failed.");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode , resultCode , data);

     callbackManager.onActivityResult(requestCode, resultCode, data);
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://developers.facebook.com"))
                .setShareHashtag(new ShareHashtag.Builder()
                        .setHashtag("#Esmeralduchi").build())
                .build();

     btn_link.setShareContent(content);

    }
}
