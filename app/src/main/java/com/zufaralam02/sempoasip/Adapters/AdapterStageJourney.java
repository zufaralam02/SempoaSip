package com.zufaralam02.sempoasip.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.zufaralam02.sempoasip.Fragments.FragmentStage;

import java.util.List;

/**
 * Created by user on 02/01/2018.
 */

public class AdapterStageJourney extends FragmentPagerAdapter {

    public AdapterStageJourney(android.support.v4.app.FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    //        private int NUM_ITEMS = 5;
    private List<String[]> listStage;

    public List<String[]> getListStage() {
        return listStage;
    }

    public void setListStage(List<String[]> listStage) {
        this.listStage = listStage;
    }

    @Override
    public Fragment getItem(int position) {
//            switch (position) {
//                case 0:
//                    return FragmentStage.newInstance(0);
//                case 1:
//                    return FragmentStage.newInstance(1);
//                case 2:
//                    return FragmentStage.newInstance(2);
//                default:
//                    return null;
//            }

        return FragmentStage.newInstance(position, listStage.get(position));
    }

    @Override
    public int getCount() {
        return listStage.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }
}
