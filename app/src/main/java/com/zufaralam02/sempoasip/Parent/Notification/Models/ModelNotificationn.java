
package com.zufaralam02.sempoasip.Parent.Notification.Models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ModelNotificationn {

    @SerializedName("result")
    private List<Result> mResult;
    @SerializedName("status_code")
    private Long mStatusCode;
    @SerializedName("status_message")
    private String mStatusMessage;

    public List<Result> getResult() {
        return mResult;
    }

    public void setResult(List<Result> result) {
        mResult = result;
    }

    public Long getStatusCode() {
        return mStatusCode;
    }

    public void setStatusCode(Long statusCode) {
        mStatusCode = statusCode;
    }

    public String getStatusMessage() {
        return mStatusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        mStatusMessage = statusMessage;
    }

}
