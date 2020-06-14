package com.example.mylen.feature.profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylen.R;

import java.util.Arrays;


public class ProfileActivity extends AppCompatActivity {
    //다른 방법 고민해보자!
    public static Activity _ProfileActivity;

    //전역변수 선언
    TextView tv_name, tv_birth, tv_sph_left, tv_sph_right;
    String name, birth, sph_left, sph_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        _ProfileActivity = ProfileActivity.this;

        tv_name = findViewById(R.id.tv_name);
        tv_birth = findViewById(R.id.tv_birth);
        tv_sph_left = findViewById(R.id.tv_sph_left);
        tv_sph_right = findViewById(R.id.tv_sph_right);

        //프로필 조회 요청 메소드 호출
        requestProfile();
    }

    //프로필 조회 요청 - GET : Retrofit2
    private void requestProfile(){
        //네트워킹 부분 추가 예정
    }

    //프로필 수정 버튼 클릭 이벤트 : 프로필 수정 페이지로 이동
    public void profileModifyButtonClick(View v){
        name = tv_name.getText().toString();
        birth = tv_birth.getText().toString();
        sph_left = tv_sph_left.getText().toString();
        sph_right = tv_sph_right.getText().toString();

        //SPH 포지션 찾기 : 요소가 없을 경우 -1로 설정함
        String[] str_sph = getResources().getStringArray(R.array.eye_sph);
        int left_position = Arrays.binarySearch(str_sph, sph_left);
        int right_position = Arrays.binarySearch(str_sph, sph_right);

        //프로필 수정 페이지로 이름, 생년월일, SPH 왼쪽, 오른쪽 값 전달
        Intent intent = new Intent(this, ProfileModifyActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("birth", birth);
        intent.putExtra("sphLeft", left_position);
        intent.putExtra("sphRight", right_position);
        startActivity(intent);
    }

    //back 버튼 클릭 이벤트: 이전 페이지로 이동
    public void backButtonClick(View view) {
        finish();
    }
}
