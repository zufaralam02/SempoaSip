package com.iapps.libs.generics;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.miguelbcr.ui.rx_paparazzo.RxPaparazzo;

/**
 * Created by marcelsantoso.
 * <p>
 * 5/24/16
 */
public class BaseApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        RxPaparazzo.register(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

    static {
//        RoboGuice.setUseAnnotationDatabases(false);
    }

}
