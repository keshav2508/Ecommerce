package com.example.list.Adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.list.FirstFragment;
import com.example.list.R;
import com.example.list.SecondFragment;

import org.jetbrains.annotations.NotNull;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    @StringRes
    private static final int[] TAB_TITLES = new int[] { R.string.tab_text_1, R.string.tab_text_2 };
    private final Context mContext;
    public ViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }
    @NotNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return FirstFragment.newInstance();
        } else {
            return SecondFragment.newInstance();
        }
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }
    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}