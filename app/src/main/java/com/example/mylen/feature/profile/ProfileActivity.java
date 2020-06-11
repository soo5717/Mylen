package com.example.mylen.feature.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylen.R;

public class ProfileActivity extends AppCompatActivity {
    TextView tv_name, tv_birth, tv_sph_left, tv_sph_right;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tv_name = (TextView)findViewById(R.id.tv_name);
        tv_birth = (TextView)findViewById(R.id.tv_birth);
        tv_sph_left = (TextView)findViewById(R.id.tv_sph_left);
        tv_sph_right = (TextView)findViewById(R.id.tv_sph_right);
    }


    //프로필 수정 버튼 클릭 이벤트 : 프로필 수정 페이지로 이동
    public void profileModifyButtonClick(View v){
        Intent intent = new Intent(this, ProfileModifyActivity.class);
        startActivity(intent);
        //전환 애니메이션 없애기
    }

    //back 버튼 클릭 이벤트: 이전 페이지로 이동
    public void backButtonClick(View view) {
        finish();
    }
}
