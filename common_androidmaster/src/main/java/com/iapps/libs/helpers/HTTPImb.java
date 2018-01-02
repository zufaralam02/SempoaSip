package com.iapps.libs.helpers;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.iapps.libs.views.LoadingCompound;

/**
 * Created by marcelsantoso on 5/30/15.
 */
public abstract class HTTPImb
        extends OkHTTPImb {
    public HTTPImb(Activity activity, boolean displayProgress) {
        super(activity, displayProgress);
    }

    public HTTPImb(Activity activity, LoadingCompound ld) {
        super(activity, ld);
    }

    public HTTPImb(Fragment frag, boolean displayProgress) {
        super(frag, displayProgress);
    }

    public HTTPImb(Fragment frag, LoadingCompound ld) {
        super(frag, ld);
    }

    //    private Fragment       fragment;
//    private Activity       activity;
//    private boolean        displayProgress;
//    private ProgressDialog mDialog;
//    private             int     page                  = 1;
//    // ================================================================================
//    // BaseConstants
//    // ================================================================================
//    public static final String  EXACT                 = "exact";
//    public static final String  START                 = "start";
//    public static final String  END                   = "end";
//    public static final String  ANYWHERE              = "anywhere";
//    public static final String  STATUS_CODE           = "status_code";
//    public static final String  STATUS_MESSAGE        = "status_message";
//    public static final String  SEARCH                = "search";
//    public static final String  PAGE                  = "page";
//    public static final String  LIMIT                 = "limit";
//    public static final String  ERR_TIMEOUT           = "Connection timeout";
//    public static final String  ERR_CONNECTION        = "Failed to connect to server";
//    public static final String  ERR_UNKNOWN           = "Unknown response from server";
//    public static final String  ERR_NETWORK           = "Network error";
//    public static final String  LOADING               = "Loading...";
//    public static final int     DEFAULT_PAGE          = 1;
//    public static final int     DEFAULT_LIMIT         = 10;
//    public static final int     CODE_BACKEND_FAIL     = 101;
//    public static final int     CODE_EMPTY_RESPONSE   = 102;
//    public static final int     CODE_INVALID_RESPONSE = 103;
//    private             boolean IS_DEBUGGING          = true;
//    private             String  LOG                   = "com.imb.swat.HTTP Log";
//    private LoadingCompound ld;
//
//    public HTTPImb(Activity activity, boolean displayProgress) {
//        this.activity = activity;
//        this.displayProgress = displayProgress;
//
//        setDefaultValue();
//    }
//
//    public HTTPImb(Activity activity, LoadingCompound ld) {
//        this.activity = activity;
//        this.ld = ld;
//
//        setDefaultValue();
//    }
//
//    public HTTPImb(Fragment frag, boolean displayProgress) {
//        this.fragment = frag;
//        this.displayProgress = displayProgress;
//
//        setDefaultValue();
//    }
//
//    public HTTPImb(Fragment frag, LoadingCompound ld) {
//        this.fragment = frag;
//        this.ld = ld;
//        setDefaultValue();
//    }
//
//    public void setDefaultValue() {
//        if (!isEmpty(url()))
//            setUrl(url());
//
//        if (!isEmpty(search()))
//            setGetParams(
//                    SEARCH, search());
//
//        if (page() > 0)
//            setGetParams(
//                    PAGE, page());
//
//        if (limit() >= 0)
//            setGetParams(
//                    LIMIT, limit());
//    }
//
//    @Override
//    protected void onPreExecute() {
//        if (displayProgress) {
//            if (fragment != null)
//                mDialog = ProgressDialog.show(fragment.getActivity(), "", LOADING);
//            else if (activity != null)
//                mDialog = ProgressDialog.show(activity, "", LOADING);
//        }
//        if (ld != null && page() <= 1) {
//            ld.showLoading();
//        }
//    }
//
//    @Override
//    protected void onPostExecute(Response response) {
//        if (!isValidResponse(response))
//            return;
//
//        onFinishLoading();
//
//        if (mDialog != null)
//            mDialog.dismiss();
//
//
//        if (!onSuccessRaw(response)) {
//            JSONObject json;
//            if (fragment != null)
//                json = handleResponse(response, false, fragment.getActivity());
//            else
//                json = handleResponse(response, false, activity);
//
//            if (json != null) {
//                try {
//                    if (json.getInt(
//                            STATUS_CODE) == 1) {
//                        if (ld != null)
//                            ld.hide();
//                        onSuccess(json);
//                    } else {
//                        onFail(CODE_BACKEND_FAIL, json);
//                    }
//
//                } catch (JSONException e) {
//                    onFail(CODE_INVALID_RESPONSE, e.getMessage());
//                    e.printStackTrace();
//                }
//            } else {
//                // Failed to parse JSON
//                onFail(CODE_EMPTY_RESPONSE, ERR_UNKNOWN);
//            }
//        }
//    }
//
//    public abstract void onSuccess(JSONObject j);
//
//    public void onFail(int code, JSONObject j) {
//        try {
//            if (j.has("err")) {
//                onFail(code, j.getString("err").replace("<br>", "\n"));
//            } else {
//                onFail(code, j.getString(STATUS_MESSAGE));
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public boolean onSuccessRaw(Response response) {
//        return false;
//    }
//
//    public void onFinishLoading() {
//
//    }
//
//    public void onFail(int code, String message) {
//        if (mDialog != null && mDialog.isShowing())
//            mDialog.dismiss();
//
//        if (displayProgress) {
//            if (fragment != null)
//                showAlert(fragment.getActivity(), message);
//            else if (activity != null)
//                showAlert(activity, message);
//        } else if (ld != null)
//            ld.showError("", message);
//    }
//
//    public int limit() {
//        return DEFAULT_LIMIT;
//    }
//
//    public int page() {
//        return this.page;
//    }
//
//    public void setPage(int page) {
//        this.page = page;
//        setGetParams(PAGE, page);
//    }
//
//    public abstract String url();
//
//    public boolean isDebugging() {
//        return this.IS_DEBUGGING;
//    }
//
//    public String log() {
//        return this.LOG;
//    }
//
//    public String search() {
//        return "";
//    }
//
//    private boolean isEmpty(String string) {
//        return string == null || string.trim().length() == 0;
//    }
//
//    protected AlertDialog showAlert(
//            Context context, String message) {
//        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        };
//
//        return new AlertDialog.Builder(context).setMessage(message)
//                                               .setCancelable(true)
//                                               .setNeutralButton(
//                                                       android.R.string.ok,
//                                                       listener)
//                                               .show();
//    }
//
//    protected boolean isValidResponse(Response response) {
//        if (response != null) {
//            if (fragment != null)
//                if (fragment.getActivity() != null
//                        && ((fragment.getActivity()).isFinishing() || !fragment.isVisible()))
//                    return false;
//
//            if (activity != null && activity.isFinishing())
//                return false;
//
//            if (isDebugging())
//                Log.d(log(), response.getContent().toString());
//        } else
//            return false;
//
//        return true;
//    }
//
//    protected JSONObject handleResponse(
//            Response response, boolean shouldDisplayDialog,
//            Context context) {
//        if (response != null) {
//            JSONObject json = response.getContent();
//            if (response.getStatusCode() == BaseConstants.STATUS_SUCCESS) {
//                return json;
//            } else if (response.getStatusCode() == BaseConstants.STATUS_TIMEOUT) {
//                if (shouldDisplayDialog && context != null) {
//                    showAlert(context, ERR_TIMEOUT);
//                }
//            } else {
//                showAlert(context, ERR_CONNECTION);
//            }
//        } else {
//            if (shouldDisplayDialog && context != null) {
//                showAlert(context, ERR_NETWORK);
//            }
//        }
//
//        return null;
//    }
//
//    public static String generateToken(Context ctx) {
//        String[] arrName   = {"acel", "jhomponk", "budi", "cikun", "abui"};
//        int      randIndex = new Random().nextInt(5);
//        JodaTimeAndroid.init(ctx);
//        String token = arrName[randIndex] +
//                DateTime.now().toDateTime(DateTimeZone.UTC).toString(BaseConstants.DATE_YMD);
//        token = BaseHelper.encryptMd5(token);
//
//        return token;
//    }
//
//    @Override
//    public HTTPAsyncTask execute() {
//        if (fragment != null)
//            setGetParams("token", generateToken(fragment.getActivity()));
//        else if (activity != null)
//            setGetParams("token", generateToken(activity));
//
//        Log.d("log", this.getUrl().toString());
//        return super.execute();
//    }
//
//    @Override
//    protected void onCancelled() {
//        super.onCancelled();
//        onFail(0, "Connection timeout, please try again");
//    }
}

