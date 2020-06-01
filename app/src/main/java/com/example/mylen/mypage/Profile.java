package com.example.mylen.mypage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylen.R;

public class Profile extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setContentView(R.layout.activity_profile_edit);
    }

}
