package com.iapps.libs.helpers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.EditText;

import com.iapps.libs.views.LoadingCompound;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by korneliussendy on 11/6/16.
 */
public abstract class OkHTTPImb extends OkHttpClient {
    private Fragment fragment;
    private Activity activity;
    private boolean displayProgress, displayError = true;
    private ProgressDialog mDialog;
    private             int     page                  = 1;
    // ================================================================================
    // BaseConstants
    // ================================================================================
    public static final String  EXACT                 = "exact";
    public static final String  START                 = "start";
    public static final String  END                   = "end";
    public static final String  ANYWHERE              = "anywhere";
    public static final String  STATUS_CODE           = "status_code";
    public static final String  STATUS_MESSAGE        = "status_message";
    public static final String  SEARCH                = "search";
    public static final String  PAGE                  = "page";
    public static final String  LIMIT                 = "limit";
    public static final String  ERR_TIMEOUT           = "Connection timeout";
    public static final String  ERR_CONNECTION        = "Failed to connect to server";
    public static final String  ERR_NO_STATUS_CODE    = "No value for status code";
    public static final String  ERR_UNKNOWN           = "Unknown response from server";
    public static final String  ERR_UNKNOWN_ERROR     = "Unknown Error";
    public static final String  ERR_NETWORK           = "Network error";
    public static final String  LOADING               = "Loading...";
    public static final int     DEFAULT_PAGE          = 1;
    public static final int     DEFAULT_LIMIT         = 30;
    public static final int     CODE_BACKEND_FAIL     = 101;
    public static final int     CODE_EMPTY_RESPONSE   = 102;
    public static final int     CODE_INVALID_RESPONSE = 103;
    public static final int     CODE_TIMEOUT          = 104;
    public static final int     CODE_NETWORK          = 105;
    public static final int     CODE_UNKOWN           = 106;
    private             boolean IS_DEBUGGING          = true;
    private             String  LOG                   = "com.imb.swat.HTTP Log";
    private LoadingCompound ld;
    // ================================================================================
    // OK HTTP
    // ================================================================================
    private String param = "", LOGGER = "OkHTTPImb";
    private HashMap<String, String> postQuery;
    private Context                 context;
    private URL                     url;
    private Call                    call;

    public OkHTTPImb(Activity activity, boolean displayProgress) {
        this.activity = activity;
        this.displayProgress = displayProgress;
        setDefaultValue();
    }

    public OkHTTPImb(Activity activity, LoadingCompound ld) {
        this.activity = activity;
        this.ld = ld;
        setDefaultValue();
    }

    public OkHTTPImb(Fragment frag, boolean displayProgress) {
        this.fragment = frag;
        this.displayProgress = displayProgress;
        setDefaultValue();
    }

    public OkHTTPImb(Fragment frag, LoadingCompound ld) {
        this.fragment = frag;
        this.ld = ld;
        setDefaultValue();
    }

    private void setDefaultValue() {
        if (fragment != null)
            context = fragment.getContext();
        else if (activity != null)
            context = activity;
        try {
            this.url = new URL(url());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.postQuery = new HashMap<>();
        //        if (!isEmpty(search()))
        //            setGetParams(
        //                    SEARCH, search());

        if (page() > 0)
            setGetParams(
                    PAGE, page());

        if (limit() >= 0)
            setGetParams(
                    LIMIT, limit());
    }

    public Context getContext() {
        if (fragment != null)
            return fragment.getActivity();

        else if (activity != null)
            return activity;

        return null;
    }

    public OkHTTPImb setDisplayProgress(boolean displayProgress) {
        this.displayProgress = displayProgress;
        return this;
    }

    public OkHTTPImb setDisplayError(boolean displayError) {
        this.displayError = displayError;
        return this;
    }

    public abstract String url();

    private void preExecute() {
        if (ld != null && page() <= 1) {
            ld.showLoading();
        } else if (displayProgress) {
            if (fragment != null)
                mDialog = ProgressDialog.show(fragment.getActivity(), "", LOADING);
            else if (activity != null)
                mDialog = ProgressDialog.show(activity, "", LOADING);
        }

        //        if (fragment != null)
        //            setGetParams("token", generateToken(fragment.getActivity()));
        //        else if (activity != null)
        //            setGetParams("token", generateToken(activity));
    }

    public void execute() {

        preExecute();

        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : postQuery.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }

        RequestBody formBody = builder.build();

        Log.d(LOGGER, url.toString());

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .addHeader("Accept-Encoding", "identity")
                .build();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                //                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (mDialog != null)
                    mDialog.dismiss();
                Log.e(LOGGER, e.toString());
                if (hasCause(e, SocketTimeoutException.class))
                    failed(CODE_TIMEOUT, ERR_TIMEOUT);
                else if (hasCause(e, UnknownHostException.class))
                    failed(CODE_NETWORK, ERR_CONNECTION);
                else
                    failed(CODE_UNKOWN, ERR_UNKNOWN_ERROR);
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Log.e(LOGGER, "FAILED", new IOException("Unexpected code " + response));
                    failed(CODE_TIMEOUT, ERR_CONNECTION);
                    return;
                }
                Log.d(LOGGER, "RESPONDED");
                String message = "";

                try {
                    String myResults = response.body().string();
                    message = myResults;
                    if (IS_DEBUGGING && !BaseHelper.isEmpty(myResults))
                        Log.d(LOGGER, myResults);

                    if (myResults.equals("")) {
                        failed(CODE_INVALID_RESPONSE, ERR_UNKNOWN);
                        return;
                    }
                    JSONObject json = new JSONObject(myResults);
                    if (json.optInt(STATUS_CODE, -1) == 1) {
                        Log.d(LOGGER, "SUCCESS");

                        success(json);
                        //                    } else if (json.optInt(STATUS_CODE, -1) == -1) {
                        //                        failed(CODE_INVALID_RESPONSE, ERR_NO_STATUS_CODE);
                    } else {
                        failed(CODE_BACKEND_FAIL, json);
                    }

                } catch (JSONException e) {
                    Log.e(LOGGER, "Failed JSONException");
                    failed(CODE_UNKOWN, message);
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.e(LOGGER, "Failed IOException");
                    if (hasCause(e, SocketTimeoutException.class))
                        failed(CODE_TIMEOUT, ERR_TIMEOUT);
                    else if (hasCause(e, UnknownHostException.class))
                        failed(CODE_NETWORK, ERR_CONNECTION);
                    else
                        failed(CODE_UNKOWN, ERR_UNKNOWN_ERROR);
                }

                Log.d(LOGGER, "End of Response");
                if (mDialog != null)
                    mDialog.dismiss();
            }
        });
    }

    public boolean cancel() {
        if (call == null)
            return true;
        else if (call.isExecuted())
            return true;
        else if (call.isCanceled())
            return true;
        else {
            call.cancel();
            return true;
        }
    }

    // ================================================================================
    // SUCCESS AND FAILED UI THREAD CONVERTER
    // ================================================================================

    private void success(final JSONObject json) {
        Handler mainHandler = new Handler(context.getMainLooper());
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                if (ld != null)
                    ld.hide();

                if (fragment != null && !fragment.isVisible())
                    return;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    if (activity != null && (activity.isDestroyed() || activity.isFinishing()))
                        return;
                } else if (activity != null && activity.isFinishing())
                    return;

                onSuccessRaw(json);
                onSuccess(json);
            }
        });
        //        if(fragment!= null)
        //            fragment.getActivity().runOnUiThread(new Runnable() {
        //                @Override
        //                public void run() {
        //                    if (ld != null)
        //                        ld.hide();
        //                    onSuccess(json);
        //                }
        //            });
        //        else if (activity !=null)
        //            activity.runOnUiThread(new Runnable() {
        //                @Override
        //                public void run() {
        //                    if (ld != null)
        //                        ld.hide();
        //                    onSuccess(json);
        //                }
        //            });
    }

    private void failed(final int code, final JSONObject j) {
        Handler mainHandler = new Handler(context.getMainLooper());
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                if (ld != null)
                    ld.hide();
                onFail(code, j);
            }
        });
    }

    private void failed(final int code, final String msg) {
        Handler mainHandler = new Handler(context.getMainLooper());
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                if (ld != null)
                    ld.hide();
                onFail(code, msg);
            }
        });
    }

    // ================================================================================
    // PUBLIC ON SUCCESS AND ON FAILED
    // ================================================================================

    public abstract void onSuccess(JSONObject j);

    public boolean onSuccessRaw(JSONObject j) {
        return false;
    }

    public void onFail(int code, JSONObject j) {
        try {
            if (j.has("err")) {
                onFail(code, j.getString("err").replace("<br>", "\n"));
            } else {
                onFail(code, j.getString(STATUS_MESSAGE));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onFail(int code, String message) {
        if (code == CODE_UNKOWN) {
            displayProgress = true;
            displayError = true;
        }

        if (mDialog != null && mDialog.isShowing())
            mDialog.dismiss();

        if (displayError)
            if (displayProgress) {
                if (fragment != null)
                    showAlert(fragment.getActivity(), message);
                else if (activity != null)
                    showAlert(activity, message);
            } else if (ld != null)
                ld.showError("", message);
    }

    protected AlertDialog showAlert(Context context, String message) {
        if (mDialog != null)
            mDialog.dismiss();

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        };

        try {
            return new AlertDialog.Builder(context).setMessage(message)
                                                   .setCancelable(true)
                                                   .setPositiveButton("Ok", listener)
                                                   .show();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // ================================================================================
    // SERVER TOKENS & HELPERS
    // ================================================================================

    public static String generateToken(Context ctx) {
        String[] arrName   = {"acel", "jhomponk", "budi", "cikun", "abui"};
        int      randIndex = new Random().nextInt(5);
        JodaTimeAndroid.init(ctx);
        String token = arrName[randIndex] +
                DateTime.now().toDateTime(DateTimeZone.UTC).toString(BaseConstants.DATE_YMD);
        token = BaseHelper.encryptMd5(token);

        return token;
    }

    private static boolean hasCause(Throwable e, Class<? extends Throwable> cl) {
        return cl.isInstance(e) || e.getCause() != null && hasCause(e.getCause(), cl);
    }

    // ================================================================================
    // LIMIT, SEARCH AND PAGINATION
    // ================================================================================

    public int limit() {
        return DEFAULT_LIMIT;
    }

    public int page() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
        setGetParams(PAGE, page);
    }

    public String search() {
        return "";
    }

    // ================================================================================
    // POST AND GET HELPER
    // ================================================================================
    public OkHTTPImb setPostParams(String key, double value) {
        String d = String.valueOf(value);
        this.setPostParams(key, d);

        return this;
    }

    public OkHTTPImb setPostParams(String key, int value) {
        String val = String.valueOf(value);
        return setPostParams(key, val);
    }

    public OkHTTPImb setPostParams(String key, EditText edt) {
        return setPostParams(key, edt.getText().toString());
    }

    public OkHTTPImb setPostParams(String key, String val) {
        if (BaseHelper.isEmpty(key) || BaseHelper.isEmpty(val))
            return this;

        postQuery.put(key, val);
        if (IS_DEBUGGING)
            Log.d(LOGGER, key + " : " + val);
        return this;
    }

    public OkHTTPImb setGetParams(String key, int value) {
        String val = String.valueOf(value);
        return setGetParams(key, val);
    }

    public OkHTTPImb setGetParams(String key, EditText edt) {
        return setGetParams(key, edt.getText().toString());
    }

    public OkHTTPImb setGetParams(String key, String value) {
        if (key != null && value != null && key.trim().length() > 0 && value.trim().length() > 0) {
            String currentUrl = url.toString();
            if (currentUrl.contains("?") && currentUrl.indexOf("?") <= currentUrl.length()) {
                try {

                    currentUrl += "&" + key + "=" + escapeUrlParam(value);
                    this.url = new URL(currentUrl);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    currentUrl += "?" + key + "=" + escapeUrlParam(value);
                    this.url = new URL(currentUrl);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
        if (IS_DEBUGGING)
            Log.d(LOGGER, key + " : " + value);
        return this;
    }

    public String escapeUrlParam(String param) {
        param = param.replace("%", "%25").replace("$", "%24").replace("`", "%60")
                     .replace("<", "%3C").replace(">", "%3E").replace("=", "%3D").replace("'", "%27")
                     .replace("/", "%2F").replace(":", "%3A").replace("+", "%2B").replace("\"", "%22")
                     .replace(" ", "%20").replace("(", "%28").replace(")", "%29").replace("&", "%26")
                     .replace("?", "	%3F");
        return param;
    }

}
