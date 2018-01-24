package com.zufaralam02.sempoasip.Parent.Home.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zufaralam02.sempoasip.Parent.Home.Fragments.FragmentChildHome;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 22/01/2018.
 */

public class AdapterChildHome extends FragmentPagerAdapter {
    public List<String[]> getListChild() {
        return listChild;
    }

    public void setListChild(List<String[]> listChild) {
        this.listChild = listChild;
    }

    List<String[]> listChild;

    public AdapterChildHome(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentChildHome.newInstance(position, listChild.get(position));
    }

    @Override
    public int getCount() {
        return listChild.size();
    }
}
