package com.example.mylen.others;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.mylen.R;
import com.google.android.material.navigation.NavigationView;

public class NavigationDrawer extends AppCompatActivity {
    private Toolbar myToolbar;
    private DrawerLayout mDrawerLayout;
    private Context context = this;
    View headerview;
    LinearLayout header;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navi_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        myToolbar = (Toolbar) findViewById(R.id.toolbar);
/*
        headerview = navigationView.getHeaderView(0);
        mButton = (Button) headerview.findViewById(R.id.btn_navi_pic);
        header = (LinearLayout) headerview.findViewById(R.id.header);
 */
        setSupportActionBar(myToolbar);
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_48dp);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                String title = menuItem.getTitle().toString();

                if (id == R.id.lense_search) {
                    Toast.makeText(context, title + ": 렌즈 검색", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.lense_manage) {
                    Toast.makeText(context, title + ": 렌즈 관리 정보", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.eye_health_manage) {
                    Toast.makeText(context, title + ": 눈 건강 관리 정보", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.lense_glasses_store) {
                    Toast.makeText(context, title + ": 주변 렌즈/안경점", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });
/*
        Button mButton = (Button) findViewById(R.id.btn_navi_pic);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buttonIntent = new Intent(getApplicationContext(), Profile.class);
                startActivity(buttonIntent);
            }
        });

        mButton = (Button) findViewById(R.id.logout_navi_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NavigationDrawer.this, "로그아웃", Toast.LENGTH_SHORT).show();
            }
        });
        */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app_bar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ // 왼쪽 상단 버튼 눌렀을 때
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}