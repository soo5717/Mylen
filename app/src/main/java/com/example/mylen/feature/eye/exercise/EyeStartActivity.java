package com.example.mylen.feature.eye.exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mylen.R;
import com.example.mylen.feature.sign.SignInActivity;

public class EyeStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eye_start);
    }

    //back 버튼 클릭 이벤트: 눈 운동 메인 페이지로 이동
    public void backButtonClick(View view) {
        finish();
    }

    //clear 버튼 클릭 이벤트: 눈 운동 메인 페이지로 이동
    public void clearButtonClick(View view) {
        finish();
    }
}
