package com.example.mylen.feature.profile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylen.R;
import com.example.mylen.data.user.ProfileResponse;
import com.example.mylen.feature.others.NavigationDrawer;
import com.example.mylen.feature.sign.SignInActivity;
import com.example.mylen.feature.sign.SignUp2Activity;
import com.example.mylen.network.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileActivity extends AppCompatActivity {
    //프로필 엑티비티 다른 엑티비티에서 종료 가능하도록 static 선언 : 다른 방법 고민해보자!
    @SuppressLint("StaticFieldLeak")
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
        RetrofitClient.getService().userProfile().enqueue(new Callback<ProfileResponse>() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onResponse(@NotNull Call<ProfileResponse> call, @NotNull Response<ProfileResponse> response) {
                ProfileResponse result = response.body();
                assert result != null;
                //프로필 조회 요청 성공 시
                if(result.getSuccess()){
                    //프로필 페이지 setText
                    tv_name.setText(result.getName());
                    tv_birth.setText(result.getBirth().substring(0, 10));
                    //SPH 포맷팅 필요 -> 추후 포지션 찾기 위해서
                    if(result.getLeft_sph() != null)
                        tv_sph_left.setText(String.format("%.2f", result.getLeft_sph()));
                    if(result.getRight_sph() != null)
                        tv_sph_right.setText(String.format("%.2f", result.getRight_sph()));
                }else{
                    //실패 시 처리 코드 추후 추가 예정
                }
            }

            @Override
            public void onFailure(@NotNull Call<ProfileResponse> call, @NotNull Throwable t) {
                Log.e("프로필 조회 에러 발생", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    //프로필 수정 버튼 클릭 이벤트 : 프로필 수정 페이지로 이동
    public void profileModifyButtonClick(View v){
        name = tv_name.getText().toString();
        birth = tv_birth.getText().toString();
        sph_left = tv_sph_left.getText().toString();
        sph_right = tv_sph_right.getText().toString();

        //SPH 포지션 찾기 : 요소가 없을 경우 -1로 설정함
        ArrayList<String> arr_sph = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.eye_sph)));
        int left_position = arr_sph.indexOf(sph_left);
        int right_position = arr_sph.indexOf(sph_right);

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
        _ProfileActivity = null;
        //네비게이션 액티비티 새로 호출 후 이전 스택 제거
        Intent intent = new Intent(ProfileActivity.this, NavigationDrawer.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    //뒤로가기 버튼 이벤트 처리: 이전 페이지로 이동
    @Override
    public void onBackPressed() {
        //네비게이션 액티비티 새로 호출 후 이전 스택 제거
        Intent intent = new Intent(ProfileActivity.this, NavigationDrawer.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        super.onBackPressed();
    }
}
