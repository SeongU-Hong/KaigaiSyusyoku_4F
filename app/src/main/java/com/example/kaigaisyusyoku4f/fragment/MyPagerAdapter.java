package com.example.kaigaisyusyoku4f.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new FreeBoard();
            case 1:
                return new InfoBoard();
        }
        return null;
    }

    @Override
    public int getCount() {     //ViewPager의 Page수
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "자유게시판";
            case 1:
                return "정보게시판";
        }
        return null;
    }
}
