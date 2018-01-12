package com.zufaralam02.sempoasip.Base;

import android.app.Application;

import com.zufaralam02.sempoasip.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by user on 09/01/2018.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/fontRegular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
