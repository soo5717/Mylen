package com.example.mylen;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private Object MainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, com.example.mylen.home.MainActivity.class);
        intent.putExtra("state","launch");
        startActivity(intent);
        finish();
    }
}