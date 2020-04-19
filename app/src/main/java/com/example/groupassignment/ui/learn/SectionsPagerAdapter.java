package com.example.groupassignment.ui.learn;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.groupassignment.R;
import com.example.groupassignment.ui.pet.AccessoriesFragment;
import com.example.groupassignment.ui.pet.WallpapersFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_4, R.string.tab_text_5, R.string.tab_text_6};
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
                fragment = new LearnFrag();
                break;
            case 1:
                fragment = new QuizFrag();
                break;
            case 2:
                fragment = new PractiseFrag();
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
}