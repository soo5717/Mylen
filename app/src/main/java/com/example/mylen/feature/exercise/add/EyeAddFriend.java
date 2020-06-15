package com.example.mylen.feature.exercise.add;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylen.R;
import com.example.mylen.data.exercise.FriendMainData;
import com.example.mylen.data.exercise.FriendMainResponse;
import com.example.mylen.data.exercise.SearchFriendData;
import com.example.mylen.data.exercise.SearchFriendResponse;
import com.example.mylen.feature.exercise.main.EyeFriendAdapter;
import com.example.mylen.feature.exercise.main.EyeMainFriendItem;
import com.example.mylen.network.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.graphics.Color.*;

public class EyeAddFriend extends AppCompatActivity {

    //Fragment클래스 인스턴스 생성
    Fragment EyeAddFriendFragment1, EyeAddFriendFragment2;

    //FragmentManager 객체 생성
    //fragment 관리, activity 뷰 계층에 추가 가능하도록 함
    //getSupportFragmentManager로 객체화하여 fragment 변경가능하도록 함
    FragmentManager fragmentManager;

    //FragmentTransaction 객체(변경하고자 하는 내용 세트) 생성
    //fragment add, remove, replace 가능하도록
    FragmentTransaction transaction;

    private Toolbar myToolbar;
    SearchView searchView;
    MenuInflater menuInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eyeaddfriend);

        EyeAddFriendFragment1 = new EyeAddFriendFragment1();
        EyeAddFriendFragment2 = new EyeAddFriendFragment2();

        //getSupportFragmentManager : fragment를 액티비티에 추가, 교체, 삭제 가능하도록 함
        fragmentManager = getSupportFragmentManager();

        transaction = fragmentManager.beginTransaction();

        //fragment를 fragmert list에 replace
        //commit() : 해야 반영됨, 작업의 무결성 보장
        transaction.replace(R.id.fl_container, EyeAddFriendFragment1).commit();

        //툴바 구현
        myToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(""); //ㅣ기존 타이틀 지우기
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        actionBar.setHomeAsUpIndicator(R.drawable.ic_backspace_48dp);
    }

    //앱바에 있는 돋보기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_app_bar_search, menu);
        //search 돋보기 아이콘 누르면 검색
        searchView = (SearchView)menu.findItem(R.id.action_search).getActionView();
        //확인버튼활성화
        searchView.setSubmitButtonEnabled(true);
        //serchview 검색 이벤트
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchFriend(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });
        //검색 버튼 클릭했을 때 searchview에 꽉차게
        //searchView.setMaxWidth(Integer.MAX_VALUE);
        //검색 버튼 클릭했을 때 searchview에 대한 힌트 추가
        //searchView.setQueryHint("검색어를 입력해주세요");
        //검색 버튼 백그라운드 컬러
        searchView.setBackgroundColor(WHITE);
        //검색 시 밑줄 없애기
        int searchPlateId = searchView.getContext().getResources()
                .getIdentifier("android:id/search_plate", null, null);
        View searchPlateView = searchView.findViewById(searchPlateId);
        if (searchPlateView != null) {
            searchPlateView.setBackgroundColor(WHITE); //depand you can set
        }
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


    private void searchFriend(String search){
        String userEmail = search;
        SearchFriendData data = new SearchFriendData(userEmail);

        RetrofitClient.service.SearchFrienAdd(data).enqueue(new Callback<SearchFriendResponse>() {

            @Override
            public void onResponse(Call<SearchFriendResponse> call, Response<SearchFriendResponse> response) {
                SearchFriendResponse result = response.body();

                //이름, 이메일, 포인트, 사진 가져옴
                int userId = result.getUserId();
                String userName = result.getuserName();
                String userEmail = result.getuserEmail();
                //String userPicture = result.getuserPicture();

            }

            @Override
            public void onFailure(Call<SearchFriendResponse> call, Throwable t) {
                Toast.makeText(EyeAddFriend.this, "가져오기 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("가져오기 에러 발생", t.getMessage());
            }
        });
    }



}
