//정리5 다시보기

package com.example.mylen.feature.notice.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.example.mylen.R;

import java.util.ArrayList;

import static android.graphics.Color.WHITE;

public class NoticeMain extends AppCompatActivity {
    private Toolbar myToolbar;
    private MenuInflater menuInflater;

    //알림
    private ArrayList<NoitceMainItem> noitceArrayList;
    private NoticeMainAdapter noticeMainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_main);


        //툴바 구현
        myToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(""); //ㅣ기존 타이틀 지우기
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        actionBar.setHomeAsUpIndicator(R.drawable.ic_backspace_48dp);

        //알림
        //리사이클러뷰
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_notice);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        noitceArrayList = new ArrayList<>();
        noticeMainAdapter = new NoticeMainAdapter(noitceArrayList);
        recyclerView.setAdapter(noticeMainAdapter);

        NoitceMainItem item = new NoitceMainItem("렌즈를 세척하세요:)", "2020.06.16");

        noitceArrayList.add(item);

        noticeMainAdapter.notifyDataSetChanged();



    }    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_app_bar_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: { // 뒤로가기 버튼 눌렀을 때
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
