package com.zufaralam02.sempoasip.Student.BottomNavigation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.zufaralam02.sempoasip.Base.BaseActivitySempoa;
import com.zufaralam02.sempoasip.Student.Challenge.Fragments.FragmentChallenge;
import com.zufaralam02.sempoasip.Student.Home.Fragments.FragmentHome;
import com.zufaralam02.sempoasip.Student.Journey.Fragments.FragmentJourney;
import com.zufaralam02.sempoasip.Student.Profil.Fragments.FragmentProfil;
import com.zufaralam02.sempoasip.R;
import com.zufaralam02.sempoasip.Student.Shop.Fragments.FragmentShop;

import java.lang.reflect.Field;

public class BottomNavigationStudent extends BaseActivitySempoa {

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
                case R.id.navigation_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new FragmentHome()).commit();
                    return true;
                case R.id.navigation_journey:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new FragmentJourney()).commit();
                    return true;
                case R.id.navigation_challenge:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new FragmentChallenge()).commit();
                    return true;
                case R.id.navigation_shop:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new FragmentShop()).commit();
                    return true;
                case R.id.navigation_profil:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new FragmentProfil()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navigation_student);

//        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationStudent.disableShiftMode(navigation);
        navigation.setSelectedItemId(R.id.navigation_journey);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new FragmentJourney()).commit();
    }

}
