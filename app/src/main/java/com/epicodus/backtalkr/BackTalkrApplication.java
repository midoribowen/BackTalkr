package com.epicodus.backtalkr;

import android.app.Application;

import com.firebase.client.Firebase;

public class BackTalkrApplication extends Application {
    private static BackTalkrApplication app;
    private Firebase mFirebaseRef;

    public static BackTalkrApplication getAppInstance() {
        return app;
    }

    public Firebase getFirebaseRef() {
        return mFirebaseRef;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Firebase.setAndroidContext(this);
        mFirebaseRef = new Firebase(this.getString(R.string.firebase_url));
    }
}
