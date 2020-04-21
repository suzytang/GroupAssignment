package com.example.groupassignment.ui.pet;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.groupassignment.R;

// The following code is modified from: Coding in Flow (2017)
// 'Tab Layout with Different Fragments - Android Studio Tutorial'
// https://www.youtube.com/watch?v=h4HwU_ENXYM&t=
// This class defines tabs and navigation for PetInventory.class. Inventory displays 2 tabs: Accessories and Wallpapers

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // getItem allows user to switch between fragments based on the tab selected
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new AccessoriesFragment();
                break;
            case 1:
                fragment = new WallpapersFragment();
                break;
        }
        return fragment;
    }

    // getPageTitle returns the tab titles from the array above
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    // getCount returns the number of tabs, in this case 2
    @Override
    public int getCount() {
        return 2;
    }
}