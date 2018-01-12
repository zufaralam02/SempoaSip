package com.zufaralam02.sempoasip.Student.Journey.Models;

/**
 * Created by user on 13/12/2017.
 */

public class ModelJourney {
    private boolean isAvailable;

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public ModelJourney(boolean isAvailable) {
        setAvailable(isAvailable);
    }

}
