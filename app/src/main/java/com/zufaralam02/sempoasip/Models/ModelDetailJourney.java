package com.zufaralam02.sempoasip.Models;

/**
 * Created by user on 14/12/2017.
 */

public class ModelDetailJourney {
    private String textDetailJourney;
    private boolean isAvailabe;

    public boolean isAvailabe() {
        return isAvailabe;
    }

    public void setAvailabe(boolean availabe) {
        isAvailabe = availabe;
    }

    public String getTextDetailJourney() {
        return textDetailJourney;
    }

    public void setTextDetailJourney(String textDetailJourney) {
        this.textDetailJourney = textDetailJourney;
    }

    public ModelDetailJourney(String text, boolean isAvailable) {
        setTextDetailJourney(text);
        setAvailabe(isAvailable);
    }

}
