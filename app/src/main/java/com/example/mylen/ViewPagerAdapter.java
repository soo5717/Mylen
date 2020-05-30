package com.example.mylen;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private int tabcount;
    public ViewPagerAdapter(FragmentManager fm, int tabcount){
        super(fm);
        this.tabcount = tabcount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                FragmentViewpagerFirst Fragment1 = new FragmentViewpagerFirst();
            case 1:
                FragmentViewpagerSecond Fragment2 = new FragmentViewpagerSecond();
            case 2:
                FragmentViewpagerThird Fragment3 = new FragmentViewpagerThird();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabcount;
    }
}
