package com.example.groupassignment.ui.shop;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.groupassignment.R;
import com.example.groupassignment.ui.pet.AccessoriesFragment;
import com.example.groupassignment.ui.pet.WallpapersFragment;

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {


// The following code is modified from: Coding in Flow (2017)
// 'Tab Layout with Different Fragments - Android Studio Tutorial'
// https://www.youtube.com/watch?v=h4HwU_ENXYM&t=
// This class defines tabs and navigation for PetInventory.class. Inventory displays 2 tabs: Accessories and Wallpapers

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_3, R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FoodFrag();
                break;
            case 1:
                fragment = new AccessoriesFrag();
                break;
            case 2:
                fragment = new WallpapersFrag();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}