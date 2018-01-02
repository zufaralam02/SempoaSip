package com.iapps.libs.generics;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.iapps.common_library.R;
import com.iapps.libs.helpers.BaseUIHelper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import butterknife.ButterKnife;

public class GenericActivity
        extends FragmentActivity implements
                                 GoogleApiClient.ConnectionCallbacks,
                                 GoogleApiClient.OnConnectionFailedListener {
    int containerId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Butter knife init
        ButterKnife.bind(this);

        // Image loader settings
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();

        ImageLoader.getInstance().init(config);

        // Activate location
        buildGoogleApiClient();
    }

    // ================================================================================
    // Fragment Functions
    // ================================================================================
    public void setContainerId(int containerId) {
        this.containerId = containerId;
    }

    public void addFragmentNoAnim(Fragment frag) {
        if (containerId > 0) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(containerId, frag).addToBackStack(null)
                       .commit();

            //            if (getSupportActionBar() != null)
            //                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    public void addFragment(Fragment frag) {
        addFragment(this.containerId, frag, null);
    }

    public void addFragment(Fragment frag, String tag) {
        addFragment(this.containerId, frag, tag);
    }

    public void setFragment(Fragment frag) {
        setFragment(this.containerId, frag);
    }

    public void addFragment(int containerId, Fragment frag, String tag) {
        if (containerId > 0) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (getSupportFragmentManager().getBackStackEntryCount() >= 1) {
                transaction.setCustomAnimations(R.anim.up_from_bottom_slow, 0, 0,
                                                R.anim.up_from_bottom_slow_exit);
            } else {
                transaction.setCustomAnimations(R.anim.zoom_in, R.anim.zoom_in);
            }

            transaction.add(containerId, frag, tag).addToBackStack(null)
                       .commit();

            //            if (getSupportActionBar() != null)
            //                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        BaseUIHelper.hideKeyboard(this);
    }

    /**
     * Add fragment on top of the current one
     *
     * @param frag
     */
    public void addFragment(int containerId, Fragment frag) {
       addFragment(containerId, frag, null);
    }

    public void addFragment(Fragment frag, int animIn, int animOut) {
        if (containerId > 0) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


            transaction.setCustomAnimations(animIn, animOut, animIn, animOut);


            transaction.add(containerId, frag).addToBackStack(null)
                       .commit();

            //            if (getSupportActionBar() != null)
            //                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            // setSupportProgressBarIndeterminateVisibility
        }
    }

    /**
     * Change to new fragment
     *
     * @param frag
     */
    public void setFragment(int containerId, Fragment frag) {
        if (containerId > 0) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (getSupportFragmentManager().getBackStackEntryCount() >= 1) {
                transaction.setCustomAnimations(R.anim.up_from_bottom_slow, 0, 0,
                                                R.anim.up_from_bottom_slow_exit);
            } else {
                transaction.setCustomAnimations(R.anim.zoom_in, R.anim.zoom_in);
            }

            transaction.replace(containerId, frag).addToBackStack(null)
                       .commit();

            //            if (getSupportActionBar() != null)
            //                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            // setSupportProgressBarIndeterminateVisibility
        }
    }

    public void setFragment(Fragment frag, int animIn, int animOut) {
        if (containerId > 0) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


            transaction.setCustomAnimations(animIn, animOut, animIn, animOut);


            transaction.replace(containerId, frag).addToBackStack(null)
                       .commit();

            //            if (getSupportActionBar() != null)
            //                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            // setSupportProgressBarIndeterminateVisibility
        }
    }

    public void addFragmentWithAnim(Fragment frag, int animIn, int animOut) {
        if (containerId > 0) {
            getSupportFragmentManager().beginTransaction().setCustomAnimations(animIn, animOut, animIn, animOut)
                                       .add(containerId, frag).addToBackStack(null).commit();

            //            if (getSupportActionBar() != null)
            //                getSupportActionBar().setDisplayHomeAsUpEnabled(false);

            // Hide keyboard by default
            BaseUIHelper.hideKeyboard(this);
        }
    }

    /**
     * Clear all fragments
     */
    public void clearFragment() {
        try {
            getSupportFragmentManager().popBackStack(null,
                                                     FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } catch (Exception e) {
            Log.e("Error : clear fragment", e.getMessage());
        }
    }

    /**
     * Remove top of fragments
     */
    public void popBackstack() {
        getSupportFragmentManager().popBackStack();
    }

    // ================================================================================
    // Behavior Controller
    // ================================================================================

    /**
     * Controls behavior of the back button
     */
    @Override
    public void onBackPressed() {
        BaseUIHelper.hideKeyboard(this);

        // Only close apps when there's no backstack
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    // ================================================================================
    // Location
    // ================================================================================
    GoogleApiClient mGoogleApiClient;
    Location        mLastLocation;

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public Location getLocation() {
        return mLastLocation;
    }
}
