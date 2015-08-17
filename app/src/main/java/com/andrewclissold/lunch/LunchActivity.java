package com.andrewclissold.lunch;

import android.app.Activity;
import android.os.Bundle;

import com.parse.Parse;


public class LunchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, BuildConfig.LUNCH_APPLICATION_ID, BuildConfig.LUNCH_CLIENT_KEY);
    }

}
