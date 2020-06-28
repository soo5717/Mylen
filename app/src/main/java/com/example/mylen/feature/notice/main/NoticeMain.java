//정리5 다시보기

package com.example.mylen.feature.notice.main;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylen.R;
import com.example.mylen.data.notice.notice.NoticeResponse;
import com.example.mylen.data.notice.set.NoticeSetResponse;
import com.example.mylen.feature.notice.set.NoticeSet;
import com.example.mylen.feature.others.NavigationDrawer;
import com.example.mylen.network.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeMain extends AppCompatActivity implements View.OnClickListener {
    private Toolbar myToolbar;
    private MenuInflater menuInflater;
    String[] msgArray;
    String time;
    String date;
    RecyclerView recyclerView;
    Button btn_all_delete ;



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
        recyclerView = (RecyclerView) findViewById(R.id.rv_notice);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        noitceArrayList = new ArrayList<>();
        noticeMainAdapter = new NoticeMainAdapter(noitceArrayList);
        recyclerView.setAdapter(noticeMainAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        RetrofitClient.getService().userNotice().enqueue(new Callback<NoticeResponse>() {
            @Override
            public void onResponse(Call<NoticeResponse> call, Response<NoticeResponse> response) {
                NoticeResponse result = response.body();
                assert result != null;

                if(result.getSuccess()) {
                    time = result.getTime();
                    date = result.getDate();
                    msgArray = result.getMsgArray();
                    Log.d("아싸", String.valueOf(msgArray));

                    for(int i=0; i<msgArray.length; i++){
                        NoitceMainItem item = new NoitceMainItem(msgArray[i], date);
                        noitceArrayList.add(item);
                    }

                    noticeMainAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<NoticeResponse> call, Throwable t) {
                Toast.makeText(NoticeMain.this, "notice main 에러 발생", Toast.LENGTH_SHORT).show();

            }
        });

        btn_all_delete = findViewById(R.id.btn_all_delete);
        btn_all_delete.setOnClickListener(this::onClick);

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

    @Override
    public void onClick(View v) {
       // recyclerView.setBackground(getDrawable(R.layout.fragment_notice_emtpy));
//        Intent intent = new Intent(NoticeMain.this, NotcieEmptyFragment.class);
//        startActivity(intent);
//        noitceArrayList.remove(0);
//        noticeMainAdapter.notifyDataSetChanged();
//
//        //db에도 표시해주어야함
//        Toast.makeText(NoticeMain.this, "안녕",  Toast.LENGTH_SHORT).show();

    }
}
