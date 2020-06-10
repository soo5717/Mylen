package com.example.mylen.feature.FragmentInfo;

import androidx.fragment.app.Fragment;

public class FragmentInfo {
    private int iconResId;
    private Fragment fragment;

    public FragmentInfo(int iconResld, Fragment fragment) {
        this.iconResId = iconResld;
        this.fragment = fragment;
    }

    public int getIconResId() {
        return iconResId;
    }

    public Fragment getFragment() {
        return fragment;
    }
}
