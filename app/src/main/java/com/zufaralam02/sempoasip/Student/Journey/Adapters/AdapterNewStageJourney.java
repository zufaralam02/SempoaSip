package com.zufaralam02.sempoasip.Student.Journey.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zufaralam02.sempoasip.Student.Journey.Activities.NewStageJourney;

import java.util.List;

/**
 * Created by user on 29/12/2017.
 */

public class AdapterNewStageJourney extends FragmentPagerAdapter {
    NewStageJourney newStageJourney;

    public NewStageJourney getNewStageJourney() {
        return newStageJourney;
    }

    public void setNewStageJourney(NewStageJourney newStageJourney) {
        this.newStageJourney = newStageJourney;
    }

    private List<String[]> listNewStage;

    public List<String[]> getListNewStage() {
        return listNewStage;
    }

    public void setListNewStage(List<String[]> listNewStage) {
        this.listNewStage = listNewStage;
    }

    private List<Fragment> listFragment;

    public List<Fragment> getListFragment() {
        return listFragment;
    }

    public void setListFragment(List<Fragment> listFragment) {
        this.listFragment = listFragment;
    }

    public AdapterNewStageJourney(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
//            return FragmentNewStage.newInstance(position, listNewStage.get(position));
//            FragmentNewStage fragmentNewStage;
//            fragmentNewStage = FragmentNewStage.newInstance(position, listNewStage.get(position));
//            fragmentNewStage.setParent(newStageJourney);
//            return fragmentNewStage;

//            FragmentNewStage fragmentNewStage;
//            fragmentNewStage = FragmentNewStage.newInstance(position, listFragment.get(position));
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
//            return listNewStage.size();
        return listFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }
}