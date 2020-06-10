package com.example.mylen.feature.home.add;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylen.R;
import com.example.mylen.feature.sign.SignInActivity;

public class AddLens extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlens1);
        setContentView(R.layout.activity_addlens2);
        setContentView(R.layout.activity_addlens3);
    }
    public void backButtonClick(View view) {
        Intent intent = new Intent(this, SignInActivity.class);
        //스택에 있는 것을 위로 올리기
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
