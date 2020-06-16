package com.example.mylen.feature.others;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.mylen.feature.eye.main.EyeMainFragment;
import com.example.mylen.feature.home.MainFragment;
import com.example.mylen.feature.calendar.FragmentCalendar;
import com.example.mylen.R;
import com.google.android.material.tabs.TabLayout;

public class BottomNavigation extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_bottom);

        ViewPager vp = findViewById(R.id.viewpager);
        TabLayout tl = findViewById(R.id.tabLayout);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(R.drawable.ic_home_color, new MainFragment());
        adapter.addFragment(R.drawable.ic_calendar_grey, new FragmentCalendar());
        adapter.addFragment(R.drawable.ic_play_grey, new EyeMainFragment());
        vp.setAdapter(adapter);

        tl.setupWithViewPager(vp);

        for (int i = 0; i < vp.getAdapter().getCount(); i++) {
            tl.getTabAt(i).setIcon(adapter.getFragmentInfo(i).getIconResId());
        }
        vp.setAdapter(adapter);
        tl.setupWithViewPager(vp);

        for(int i=0; i<vp.getAdapter().getCount(); i++)
            tl.getTabAt(i).setIcon(adapter.getFragmentInfo(i).getIconResId());

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tl.getTabAt(0).setIcon(R.drawable.ic_home_color);
                        tl.getTabAt(1).setIcon(R.drawable.ic_calendar_grey);
                        tl.getTabAt(2).setIcon(R.drawable.ic_play_grey);
                        break;
                    case 1:
                        tl.getTabAt(0).setIcon(R.drawable.ic_home_grey);
                        tl.getTabAt(1).setIcon(R.drawable.ic_calendar_color);
                        tl.getTabAt(2).setIcon(R.drawable.ic_play_grey);
                        break;
                    case 2:
                        tl.getTabAt(0).setIcon(R.drawable.ic_home_grey);
                        tl.getTabAt(1).setIcon(R.drawable.ic_calendar_grey);
                        tl.getTabAt(2).setIcon(R.drawable.ic_play_color);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

}