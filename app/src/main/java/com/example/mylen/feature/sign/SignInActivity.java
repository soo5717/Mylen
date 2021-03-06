package com.example.mylen.feature.sign;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylen.R;
import com.example.mylen.data.user.SignInData;
import com.example.mylen.data.user.SignInResponse;
import com.example.mylen.feature.notice.GetNoticeData;
import com.example.mylen.feature.notice.notice.AlarmReceiver;
import com.example.mylen.feature.notice.notice.GetNotice;
import com.example.mylen.feature.others.NavigationDrawer;
import com.example.mylen.feature.util.PreferenceManager;
import com.example.mylen.network.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    //전역변수 선언
    EditText et_email, et_pwd;
    String email, pwd;
    Boolean is_valid_email = false, is_valid_pwd = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //이메일 EditText 입력 변화 이벤트
        et_email = findViewById(R.id.et_email);
        et_email.addTextChangedListener(new TextWatcher() {
            @Override //입력 중
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override //입력 완료
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //이메일 유효성 검사 success
                if(isValidEmail(et_email.getText().toString())) {
                    et_email.setBackgroundResource(R.drawable.et_underline_valid_success);
                    is_valid_email = true;
                }
                else { //이메일 유효성 검사 fail
                    et_email.setBackgroundResource(R.drawable.et_underline_valid_fail);
                    is_valid_email = false;
                }
            }
            @Override //입력 전
            public void afterTextChanged(Editable s) { }
        });

        //비밀번호 EditText 입력 변화 이벤트
        et_pwd = findViewById(R.id.et_pwd);
        et_pwd.addTextChangedListener(new TextWatcher() {
            @Override //입력 중
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override  //입력 완료
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //비밀번호 유효성 검사 success
                if(et_pwd.getText().length() >= 6) {
                    et_pwd.setBackgroundResource(R.drawable.et_underline_valid_success);
                    is_valid_pwd = true;
                }
                else { //비밀번호 유효성 검사 fail
                    et_pwd.setBackgroundResource(R.drawable.et_underline_valid_fail);
                    is_valid_pwd = false;
                }
            }
            @Override //입력 전
            public void afterTextChanged(Editable s) { }
        });
    }

    //로그인 요청 - POST : Retrofit2
    private void requestSignIn(SignInData data){
        RetrofitClient.getService().userSignIn(data).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(@NotNull Call<SignInResponse> call, @NotNull Response<SignInResponse> response) {
                SignInResponse result = response.body();
                assert result != null;
                Toast.makeText(SignInActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                //로그인 요청 성공 시
                if(result.getSuccess()) {
                    //Shared Preferences 토큰 저장
                    Log.d("TOKEN    :   ", result.getToken());
                    PreferenceManager.setString("user_token", result.getToken());

                    //알림등록
                    Intent intent_a = new Intent(SignInActivity.this, GetNoticeData.class);
                    intent_a.putExtra("check_start", true);
                    startActivity(intent_a);

                    //메인 페이지로 이동
                    Intent intent = new Intent(SignInActivity.this, NavigationDrawer.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(@NotNull Call<SignInResponse> call, @NotNull Throwable t) {
                Toast.makeText(SignInActivity.this, "로그인 에러가 발생했습니다", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    //이메일 유효성 검사 메소드
    public boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    //로그인 버튼 클릭 이벤트
    public void signInButtonClick(View view) {
        //이메일, 비밀번호 유효성 검사 통과
        if(is_valid_email && is_valid_pwd) {
            email = et_email.getText().toString();
            pwd = et_pwd.getText().toString();

            //비밀번호 암호화
            String encrypt_pwd  = EncryptSHA512.encryptSHA512(pwd);
            Log.d("Password: ", encrypt_pwd);

            //로그인 요청 메소드 호출
            requestSignIn(new SignInData(email, encrypt_pwd));
        }
        else if (is_valid_email) { //이메일만 유효성 검사 통과
            Toast.makeText(getApplicationContext(), "비밀번호를 6자리 이상 입력해주세요!", Toast.LENGTH_LONG).show();
            et_pwd.requestFocus(); //포커스 주기
        }
        else if (is_valid_pwd) { //비밀번호만 유효성 검사 통과
            Toast.makeText(getApplicationContext(), "이메일 형식으로 입력해주세요!", Toast.LENGTH_LONG).show();
            et_email.requestFocus(); //포커스 주기
        }
        else{ //유효성 검사 실패
            Toast.makeText(getApplicationContext(), "이메일과 비밀번호를 형식에 맞춰 입력해주세요!", Toast.LENGTH_LONG).show();
            et_email.requestFocus(); //포커스 주기
        }
    }

    //회원가입 버튼 클릭 이벤트 : 회원가입1 페이지로 이동
    public void signUpButtonClick(View view) {
        Intent intent = new Intent(this, SignUp1Activity.class);
        startActivity(intent);
    }
}
