package com.andrewclissold.lunch;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by aclissold on 8/17/15.
 */
public class LunchApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, BuildConfig.LUNCH_APPLICATION_ID, BuildConfig.LUNCH_CLIENT_KEY);
    }
}
