
package com.zufaralam02.sempoasip.Parent.Notification.Models;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Result {

    @SerializedName("notification_belongs_id")
    private String mNotificationBelongsId;
    @SerializedName("notification_content")
    private String mNotificationContent;
    @SerializedName("notification_created")
    private String mNotificationCreated;
    @SerializedName("notification_id")
    private String mNotificationId;
    @SerializedName("notification_title")
    private String mNotificationTitle;
    @SerializedName("notification_type")
    private String mNotificationType;
    @SerializedName("notification_updated")
    private String mNotificationUpdated;

    public String getNotificationBelongsId() {
        return mNotificationBelongsId;
    }

    public void setNotificationBelongsId(String notificationBelongsId) {
        mNotificationBelongsId = notificationBelongsId;
    }

    public String getNotificationContent() {
        return mNotificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        mNotificationContent = notificationContent;
    }

    public String getNotificationCreated() {
        return mNotificationCreated;
    }

    public void setNotificationCreated(String notificationCreated) {
        mNotificationCreated = notificationCreated;
    }

    public String getNotificationId() {
        return mNotificationId;
    }

    public void setNotificationId(String notificationId) {
        mNotificationId = notificationId;
    }

    public String getNotificationTitle() {
        return mNotificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        mNotificationTitle = notificationTitle;
    }

    public String getNotificationType() {
        return mNotificationType;
    }

    public void setNotificationType(String notificationType) {
        mNotificationType = notificationType;
    }

    public String getNotificationUpdated() {
        return mNotificationUpdated;
    }

    public void setNotificationUpdated(String notificationUpdated) {
        mNotificationUpdated = notificationUpdated;
    }

}
