package com.example.mylen.feature.others;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.mylen.R;
import com.example.mylen.data.user.ProfileResponse;
import com.example.mylen.feature.calendar.FragmentCalendar;
import com.example.mylen.feature.eye.main.EyeMainFragment;
import com.example.mylen.feature.home.MainFragment;
import com.example.mylen.feature.profile.ProfileActivity;
import com.example.mylen.feature.sign.SignInActivity;
import com.example.mylen.feature.sign.SignUp2Activity;
import com.example.mylen.feature.util.PreferenceManager;
import com.example.mylen.network.RetrofitClient;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NavigationDrawer extends AppCompatActivity {
    //필요한 변수 선언
    private Toolbar myToolbar;
    private DrawerLayout mDrawerLayout;
    private Context context = this;
    View headerview;
    private Button mButton_logout;
    private Button mButton_profile;
    NavigationView navigationView;
    LinearLayout container;
    TabLayout tl;
    ViewPager vp;
    ViewPagerAdapter adapter;
    Menu menu_change;
    MenuInflater menuInflater;
    TextView navi_name;
    public int out_position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //뷰페이저 구현
        setContentView(R.layout.activity_navigation_drawer);

        vp = findViewById(R.id.viewpager);
        tl = findViewById(R.id.tabLayout);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

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
        //아이콘 움직임에 따라 아이콘 바꾸기
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                out_position = position;
                switch (position) {
                    case 0:
                        tl.getTabAt(0).setIcon(R.drawable.ic_home_color);
                        tl.getTabAt(1).setIcon(R.drawable.ic_calendar_grey);
                        tl.getTabAt(2).setIcon(R.drawable.ic_play_grey);
                        //앱바 아이콘 바꾸기
                        myToolbar.getMenu().clear();
                        menuInflater.inflate(R.menu.menu_app_bar, menu_change);

                        break;
                    case 1:
                        tl.getTabAt(0).setIcon(R.drawable.ic_home_grey);
                        tl.getTabAt(1).setIcon(R.drawable.ic_calendar_color);
                        tl.getTabAt(2).setIcon(R.drawable.ic_play_grey);
                        myToolbar.getMenu().clear();
                        menuInflater.inflate(R.menu.menu_app_bar, menu_change);

                        break;
                    case 2:
                        tl.getTabAt(0).setIcon(R.drawable.ic_home_grey);
                        tl.getTabAt(1).setIcon(R.drawable.ic_calendar_grey);
                        tl.getTabAt(2).setIcon(R.drawable.ic_play_color);
                        myToolbar.getMenu().clear();
                        menuInflater.inflate(R.menu.menu_app_bar_report, menu_change);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //네비게이션 구현
        navigationView = (NavigationView) findViewById(R.id.navi_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        //툴바 구현
        setSupportActionBar(myToolbar);
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_48dp);
        //네비게이션 동작
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

        //네비게이션 헤더 로그아웃버튼 구현
        headerview = navigationView.getHeaderView(0);
        mButton_logout = headerview.findViewById(R.id.logout_navi_button);
        mButton_logout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                PreferenceManager.removeKey("user_token");
                //로그인 페이지로 이동
                Intent intent = new Intent(NavigationDrawer.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //네비게이션 사용자 이름 설정
        navi_name = headerview.findViewById(R.id.navi_name);
        setHeaderName();

        //네비게이션 이미지 누르면 프로필 화면으로 전환
        mButton_profile = headerview.findViewById(R.id.btn_navi_pic);
        mButton_profile.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent profile_intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(profile_intent);
            }
        });

    }

    //네비게이션 사용자 이름 설정 메소드
    public void setHeaderName(){
        RetrofitClient.getService().userProfile().enqueue(new Callback<ProfileResponse>() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onResponse(@NotNull Call<ProfileResponse> call, @NotNull Response<ProfileResponse> response) {
                ProfileResponse result = response.body();
                assert result != null;
                //프로필 조회 요청 성공 시
                if(result.getSuccess()){
                    navi_name.setText(result.getName());
                }else{
                    //실패 시 처리 코드 추후 추가 예정
                }
            }

            @Override
            public void onFailure(@NotNull Call<ProfileResponse> call, @NotNull Throwable t) {
                Log.e("네비게이션 이름 조회 에러 발생", Objects.requireNonNull(t.getMessage()));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu_change = menu;
        menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_app_bar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ // 왼쪽 상단 버튼 눌렀을 때
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
            case R.id.appbar_right_notice:{
                Toast.makeText(context, "공지",Toast.LENGTH_LONG).show();
                return true;
            }
            case R.id.action_report:{
                Toast.makeText(context, "눈운동",Toast.LENGTH_LONG).show();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}