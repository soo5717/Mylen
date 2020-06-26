package com.example.mylen.feature.others;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylen.data.user.StatusResponse;
import com.example.mylen.feature.sign.SignInActivity;
import com.example.mylen.network.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Handler handler = new Handler();
        //토큰 검증 요청 메소드 호출
        handler.postDelayed(this::requestAuth, 500);
    }

    //토큰 검증 요청 - POST : Retrofit2
    private void requestAuth(){
        RetrofitClient.getService().userAuth().enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(@NotNull Call<StatusResponse> call, @NotNull Response<StatusResponse> response) {
                StatusResponse result = response.body();
                //토큰 인증 성공 시
                assert result != null;
                if(result.getSuccess()) {
                    //메인 페이지로 이동
                    Intent intent = new Intent(SplashActivity.this, NavigationDrawer.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    //로그인 페이지로 이동
                    Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(@NotNull Call<StatusResponse> call, @NotNull Throwable t) {
                Log.e("자동 로그인 에러 발생", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}