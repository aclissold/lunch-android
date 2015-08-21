package com.andrewclissold.lunch.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Andrew Clissold on 8/20/15.
 */
@ParseClassName("Eateries")
public class Eatery extends ParseObject {

    public String getPlace() {
        return getString("place");
    }

    public void setPlace(String place) {
        put("place", place);
    }

    public int getVotes() {
        return getInt("votes");
    }

    public void setVotes(int votes) {
        put("vote", votes);
    }

    public boolean isPushed() {
        return getBoolean("isPushed");
    }

    public void setPushed(boolean pushed) {
        put("isPushed", pushed);
    }

}
