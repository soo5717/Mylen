package com.example.mylen;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.mylen.FragmentInfo.FragmentInfo;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<FragmentInfo> items = new ArrayList<FragmentInfo>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    public void addFragment(int iconResld, Fragment fragment) {
        FragmentInfo info = new FragmentInfo(iconResld, fragment);
        items.add(info);
    }

    public FragmentInfo getFragmentInfo(int position){
        return items.get(position);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return items.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return items.size();
    }
}