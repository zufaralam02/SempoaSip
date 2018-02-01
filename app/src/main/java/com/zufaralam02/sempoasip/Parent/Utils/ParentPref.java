package com.zufaralam02.sempoasip.Parent.Utils;

import android.content.Context;

import com.iapps.libs.helpers.BasePreference;

/**
 * Created by user on 01/02/2018.
 */

public class ParentPref extends BasePreference {
    public static final String PARENT_IS_LOGGED_IN = "parentisloggedin";

    private static ParentPref pref;

    public static ParentPref getInstance(Context context){
        if (pref == null){
            pref = new ParentPref(context);
        }

        return pref;
    }

    private ParentPref (Context context){
        this.context = context;
    }

    @Override
    public String setPrefName() {
        return "parent";
    }
}
