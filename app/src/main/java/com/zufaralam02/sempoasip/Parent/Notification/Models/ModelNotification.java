package com.zufaralam02.sempoasip.Parent.Notification.Models;

/**
 * Created by user on 18/01/2018.
 */

public class ModelNotification {
    private int titleNotif;
    private int detailNotif;
    private int timeNotif;
    private int imageNotif;
    private boolean isReaded;

    public int getTitleNotif() {
        return titleNotif;
    }

    public void setTitleNotif(int titleNotif) {
        this.titleNotif = titleNotif;
    }

    public int getTimeNotif() {
        return timeNotif;
    }

    public void setTimeNotif(int timeNotif) {
        this.timeNotif = timeNotif;
    }

    public int getDetailNotif() {
        return detailNotif;
    }

    public void setDetailNotif(int detailNotif) {
        this.detailNotif = detailNotif;
    }

    public int getImageNotif() {
        return imageNotif;
    }

    public void setImageNotif(int imageNotif) {
        this.imageNotif = imageNotif;
    }

    public boolean isReaded() {
        return isReaded;
    }

    public void setReaded(boolean readed) {
        isReaded = readed;
    }

    public ModelNotification(int titleNotif, int detailNotif, int timeNotif, int imageNotif, boolean isReaded) {
        setTitleNotif(titleNotif);
        setDetailNotif(detailNotif);
        setTimeNotif(timeNotif);
        setImageNotif(imageNotif);
        setReaded(isReaded);
    }

}
