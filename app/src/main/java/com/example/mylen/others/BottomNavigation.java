package com.example.mylen.others;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.mylen.FragmentViewpagerFirst;
import com.example.mylen.FragmentViewpagerSecond;
import com.example.mylen.FragmentViewpagerThird;
import com.example.mylen.R;
import com.google.android.material.tabs.TabLayout;

public class BottomNavigation extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        ViewPager vp = findViewById(R.id.viewpager);
        TabLayout tl = findViewById(R.id.tabLayout);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(R.drawable.ic_home, new FragmentViewpagerFirst());
        adapter.addFragment(R.drawable.ic_calendar, new FragmentViewpagerSecond());
        adapter.addFragment(R.drawable.ic_play, new FragmentViewpagerThird());
        vp.setAdapter(adapter);

        tl.setupWithViewPager(vp);

        for (int i = 0; i < vp.getAdapter().getCount(); i++) {
            tl.getTabAt(i).setIcon(adapter.getFragmentInfo(i).getIconResId());
        }
        vp.setAdapter(adapter);
        tl.setupWithViewPager(vp);

        for(int i=0; i<vp.getAdapter().getCount(); i++)
            tl.getTabAt(i).setIcon(adapter.getFragmentInfo(i).getIconResId());

    }
}