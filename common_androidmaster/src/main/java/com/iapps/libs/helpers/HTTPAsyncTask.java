package com.iapps.libs.helpers;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.EditText;

import com.iapps.libs.objects.Response;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Abstract class to handle HTTP connection to a web server
 *
 * @author melvin
 */
public abstract class HTTPAsyncTask
        extends AsyncTask<String, Void, Response> {
    private boolean httpsEnabled     = false;
    private boolean isCache          = true;
    private boolean isEnableSSLCheck = true;
    private int     retryCount       = 0;
    private URL url;
    private String method = BaseConstants.GET;
    private Fragment fragment;
    private      LinkedHashMap<String, String> params          = new LinkedHashMap<String, String>();
    private      HashMap<String, String>       mHeaderParams   = new HashMap<String, String>();
    static final int                           MAX_CONNECTIONS = 10;

    protected abstract void onPreExecute();

    protected abstract void onPostExecute(Response response);

    /**
     * Get the URL
     *
     * @return the URL being used for the end point
     */
    public URL getUrl() {
        return url;
    }

    /**
     * Set the URL to be used to connect to the end point
     *
     * @param url , the url to be used
     */
    public HTTPAsyncTask setUrl(String url) {

        try {
            this.url = new URL(url);
            if (url.startsWith("https")) {
                this.httpsEnabled = true;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return this;
    }

    public HTTPAsyncTask setGetParams(String key, EditText edt) {
        return setGetParams(key, edt.getText().toString());
    }

    public HTTPAsyncTask setGetParams(String key, String value) {
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

        return this;
    }

    /**
     * Set the header key value pair
     *
     * @param key
     * @param value
     */
    public HTTPAsyncTask setHeader(String key, String value) {
        mHeaderParams.put(key, value);
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

    public HTTPAsyncTask setGetParams(String key, int value) {
        String val = String.valueOf(value);
        return setGetParams(key, val);
    }

    public String getMethod() {
        return method;
    }

    public HTTPAsyncTask setMethod(String method) {
        this.method = method;
        return this;
    }

    public boolean isHttpsEnabled() {
        return httpsEnabled;
    }

    public HTTPAsyncTask setHttpsEnabled(boolean httpsEnabled) {
        this.httpsEnabled = httpsEnabled;
        return this;
    }

    public boolean isEnableSSLCheck() {
        return isEnableSSLCheck;
    }

    public void setEnableSSLCheck(boolean isDisableSSLCheck) {
        this.isEnableSSLCheck = isDisableSSLCheck;
    }

    public HTTPAsyncTask setPostParams(String key, EditText edt) {
        return setPostParams(key, edt.getText().toString());
    }

    public HTTPAsyncTask setPostParams(String key, String value) {
        if (key == null || key.trim().length() <= 0 || value == null || value.trim().length() <= 0) {
            return this;
        }

        this.params.put(key, value);
        this.setMethod(BaseConstants.POST);
        return this;
    }

    public HTTPAsyncTask setPostParams(String key, double value) {
        String d = String.valueOf(value);
        this.setPostParams(key, d);

        return this;
    }

    public HTTPAsyncTask setPostParams(String key, int value) {
        String d = String.valueOf(value);
        this.setPostParams(key, d);

        return this;
    }

    public HTTPAsyncTask setCache(boolean isCache) {
        this.isCache = isCache;

        return this;
    }

    public HTTPAsyncTask execute() {
        //		String url2 = this.url.toString();
        super.execute();

        return this;
    }

    public int connectionTimeout() {
        return BaseConstants.TIMEOUT;
    }

    /**
     * Disables the SSL certificate checking for new instances of {@link HttpsURLConnection} This has been created to
     * aid testing on a local box, not for use on production.
     */
    private static void disableSSLCertificateChecking() {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    @Override
                    public void checkClientTrusted(X509Certificate[] arg0, String arg1)
                            throws CertificateException {
                        // Not implemented
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] arg0, String arg1)
                            throws CertificateException {
                        // Not implemented
                    }
                }
        };

        try {
            SSLContext sc = SSLContext.getInstance("TLS");

            sc.init(null, trustAllCerts, new java.security.SecureRandom());

            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String[] getHeaders(HttpURLConnection con, String header) {
        List<String> values = new ArrayList<String>();
        int          idx    = (con.getHeaderFieldKey(0) == null) ? 1 : 0;
        while (true) {
            String key = con.getHeaderFieldKey(idx);
            if (key == null)
                break;
            if (header.equalsIgnoreCase(key))
                values.add(con.getHeaderField(idx));
            ++idx;
        }
        return values.toArray(new String[values.size()]);
    }

    public boolean isNetworkAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com"); //You can replace it with your name

            if (ipAddr.equals("")) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            return false;
        }
    }

    /*public class InterruptThread implements Runnable {
        Thread        parent;
        URLConnection con;

        public InterruptThread(Thread parent, URLConnection con) {
            this.parent = parent;
            this.con = con;
        }

        public void run() {
            try {
                Thread.sleep(connectionTimeout());
            } catch (InterruptedException e) {

            }
        }
    }*/
    private void createTimeoutListener() {
        Thread timeout = new Thread() {
            public void run() {
                Looper.prepare();

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (HTTPAsyncTask.this.getStatus() != Status.FINISHED)
                            HTTPAsyncTask.this.cancel(true);
                        handler.removeCallbacks(this);
                        if (Looper.myLooper() != null)
                            Looper.myLooper().quit();
                    }
                }, connectionTimeout());

                Looper.loop();
            }
        };
        timeout.start();
    }

    @Override
    protected Response doInBackground(String... urls) {
        createTimeoutListener();
        //        for (int i = 0; i < 2; i++) {
        // init
        HttpURLConnection conn           = null;
        InputStream       in             = null;
        int               http_status    = BaseConstants.STATUS_BAD_REQUEST;
        String            responseString = null;
        Response          response       = null;
        try {
            if (!isNetworkAvailable())
                return response;

            if (!isEnableSSLCheck)
                // Trust all incoming certificates
                disableSSLCertificateChecking();

            if (httpsEnabled) {
                conn = (HttpsURLConnection) url.openConnection();
            } else {
                conn = (HttpURLConnection) url.openConnection();
            }
            if (connectionTimeout() > 0) {
                conn.setConnectTimeout(connectionTimeout());
                conn.setReadTimeout(connectionTimeout());
            }

            //            new Thread(new InterruptThread(Thread.currentThread(), conn)).start();

            System.setProperty("http.maxConnections", String.valueOf(MAX_CONNECTIONS));

            conn.setUseCaches(false);
            Set<Map.Entry<String, String>> header = mHeaderParams.entrySet();
            for (Map.Entry<String, String> entry : header) {
                String key   = entry.getKey();
                String value = entry.getValue();
                conn.setRequestProperty(key, value);
            }

            // Check if the request should be cached in the network level
            if (!isCache) {
                conn.addRequestProperty("Cache-Control", "no-cache");
            }
            if (this.method.equalsIgnoreCase(BaseConstants.POST)) {
                // post data to server
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");

                String twoHyphens = "--";
                String boundary   = "*****" + Long.toString(System.currentTimeMillis()) + "*****";
                String lineEnd    = "\r\n";

                // Original code
                //                    conn.setRequestProperty("Connection", "Keep-Alive");
                //                                        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary="
                //                                                + boundary);
                //                    DataOutputStream outputStream = new DataOutputStream(conn.getOutputStream());
                //                    for (String key : params.keySet()) {
                //                        outputStream.writeBytes(twoHyphens + boundary + lineEnd);
                //                        outputStream.writeBytes("Content-Disposition: form-data; name=\"" + key
                //                                                        + "\"");
                //                        outputStream.writeBytes(lineEnd);
                //                        outputStream.writeBytes("Content-Type: text/plain" + lineEnd);
                //                        outputStream.writeBytes(lineEnd);
                //                        outputStream.writeBytes(params.get(key));
                //                        Log.d("value", key + ":" + params.get(key));
                //                        outputStream.writeBytes(lineEnd);
                //                    }
                //
                //                    outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
                //                    outputStream.flush();
                //                    outputStream.close();

                // UTF-8 support
                conn.setRequestProperty("Content-Type", "multipart/form-data; charset=utf-8; boundary=" + boundary);

                BufferedWriter outputStream = new BufferedWriter(
                        new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
                for (String key : params.keySet()) {
                    outputStream.write(twoHyphens + boundary + lineEnd);
                    outputStream.write("Content-Disposition: form-data; name=\"" + key
                                               + "\"");
                    outputStream.write(lineEnd);
                    outputStream.write("Content-Type: text/plain" + lineEnd);
                    outputStream.write(lineEnd);
                    outputStream.write(params.get(key));
                    Log.d("value", key + ":" + params.get(key));
                    outputStream.write(lineEnd);
                }

                outputStream.write(twoHyphens + boundary + twoHyphens + lineEnd);
                outputStream.flush();
                outputStream.close();
            }

            http_status = conn.getResponseCode();

            if (http_status == BaseConstants.STATUS_SUCCESS) {
                in = conn.getInputStream();
            } else {
                in = conn.getErrorStream();
            }

            if (in != null) {
                // read input from server
                BufferedReader br = new BufferedReader(new InputStreamReader(in));

                StringBuilder sb = new StringBuilder();

                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                responseString = sb.toString().replace("\\'", "'");
                response = new Response(http_status, responseString);
                response.setHeaderContent(conn.getHeaderFields());
            }
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            response = new Response(BaseConstants.STATUS_TIMEOUT, "");
        } catch (EOFException e) {
            e.printStackTrace();
            response = new Response(BaseConstants.STATUS_TIMEOUT, "");
            //            this.execute();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            response = new Response(BaseConstants.STATUS_NO_CONNECTION, "");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            response = new Response(HttpURLConnection.HTTP_INTERNAL_ERROR, "");
        } catch (IOException e) {
            e.printStackTrace();
            response = new Response(BaseConstants.STATUS_NO_CONNECTION, "");
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        if (this.fragment != null) {
            if (!this.fragment.isAdded())
                this.cancel(true);
        }

        if (response != null && response.getStatusCode() != BaseConstants.STATUS_TIMEOUT)
            return response;
        //        }

        return null;
    }

}
