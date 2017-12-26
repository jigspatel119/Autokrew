package com.autokrew.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.autokrew.fragments.ProfileFragment;

import java.util.ArrayList;

public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

    ArrayList<String> mProfileTabs;

    public TabFragmentPagerAdapter(FragmentManager fm ,ArrayList<String> mProfileTabs) {
        super(fm);
        this.mProfileTabs = mProfileTabs;
    }

    @Override
    public Fragment getItem(int position) {

        return ProfileFragment.newInstance();
    }

    @Override
    public int getCount() {
        return mProfileTabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mProfileTabs.get(position);
    }
}
