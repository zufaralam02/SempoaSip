package com.zufaralam02.sempoasip.Parent.Utils;

import android.content.Context;

import com.iapps.libs.helpers.BaseHelper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by user on 01/02/2018.
 */

public class Helper extends BaseHelper {
    public static void saveUser (Context ctx, String jsonUser){
        ParentPref.getInstance(ctx).setString("user", jsonUser);
    }

    // public static user getUser (Context ctx)
    public static void getUser (Context ctx){
        String jsonUser = ParentPref.getInstance(ctx).getString("user");
        try {
            JSONObject j = new JSONObject(jsonUser);
            // Convert json jadi user

            //return user
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // return null
    }
}
