package com.andrewclissold.lunch;

import android.app.Application;

import com.andrewclissold.lunch.model.Eatery;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by aclissold on 8/17/15.
 */
public class LunchApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(Eatery.class);
        Parse.initialize(this, BuildConfig.LUNCH_APPLICATION_ID, BuildConfig.LUNCH_CLIENT_KEY);
    }
}
