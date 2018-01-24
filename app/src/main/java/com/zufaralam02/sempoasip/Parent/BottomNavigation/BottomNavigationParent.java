package com.zufaralam02.sempoasip.Parent.BottomNavigation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.zufaralam02.sempoasip.Base.BaseActivitySempoa;
import com.zufaralam02.sempoasip.Parent.Home.Fragments.FragmentHomeParent;
import com.zufaralam02.sempoasip.Parent.Notification.Fragments.FragmentNotificationParent;
import com.zufaralam02.sempoasip.Parent.Profil.Fragments.FragmentProfilParent;
import com.zufaralam02.sempoasip.Parent.Wallet.Fragments.FragmentWalletParent;
import com.zufaralam02.sempoasip.R;

import java.lang.reflect.Field;

public class BottomNavigationParent extends BaseActivitySempoa {

    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }

//    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home_parent:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameParent, new FragmentHomeParent()).commit();
                    return true;
                case R.id.navigation_wallet_parent:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameParent, new FragmentWalletParent()).commit();
                    return true;
                case R.id.navigation_notification_parent:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameParent, new FragmentNotificationParent()).commit();
                    return true;
                case R.id.navigation_profil_parent:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameParent, new FragmentProfilParent()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navigation_parent);

//        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationParent.disableShiftMode(navigation);
        navigation.setSelectedItemId(R.id.navigation_home_parent);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameParent, new FragmentHomeParent()).commit();
    }

}
