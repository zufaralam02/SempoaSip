package com.zufaralam02.sempoasip.Parent.Home.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.TextView;

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

    TextView tvChildRank, tvChildWallet;

    public TextView getTvChildRank() {
        return tvChildRank;
    }

    public void setTvChildRank(TextView tvChildRank) {
        this.tvChildRank = tvChildRank;
    }

    public TextView getTvChildWallet() {
        return tvChildWallet;
    }

    public void setTvChildWallet(TextView tvChildWallet) {
        this.tvChildWallet = tvChildWallet;
    }

    public AdapterChildHome(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

//        if (listChild.size() == 0) {
//            tvChildWallet.setText(listChild.indexOf(0));
//            tvChildRank.setText(listChild.indexOf(0));
//        } else if (listChild.size() == 1) {
//            tvChildWallet.setText(listChild.indexOf(1));
//            tvChildRank.setText(listChild.indexOf(1));
//        }

        return FragmentChildHome.newInstance(position, listChild.get(position));
    }

    @Override
    public int getCount() {
        return listChild.size();
    }
}
