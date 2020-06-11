package com.example.mylen.feature.sign;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylen.R;

public class SignUp1Activity extends AppCompatActivity {

    EditText et_email, et_pwd;
    String email, pwd;
    Boolean is_valid_email = false, is_valid_pwd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1);

        //이메일 EditText 입력 변화 이벤트
        et_email = (EditText)findViewById(R.id.et_email);
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
        et_pwd = (EditText)findViewById(R.id.et_pwd);
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

    //이메일 유효성 검사 메소드
    public boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    //다음 버튼 클릭 이벤트
    public void nextButtonClick(View view) {
        //이메일, 비밀번호 유효성 검사 통과
        if(is_valid_email && is_valid_pwd) {
            email = et_email.getText().toString();
            pwd = et_pwd.getText().toString();
            //회원가입2 페이지로 이메일, 비밀번호 전달
            Intent intent = new Intent(this, SignUp2Activity.class);
            intent.putExtra("signUpEmail", email);
            intent.putExtra("signUpPwd", pwd);
            startActivity(intent);
            //전환 애니메이션 없애기
            overridePendingTransition(0, 0);
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

    //back 버튼 클릭 이벤트: 로그인 페이지로 이동
    public void backButtonClick(View view) {
        finish();
    }

    //clear 버튼 클릭 이벤트: 로그인 페이지로 이동
    public void clearButtonClick(View view) {
        Intent intent = new Intent(this, SignInActivity.class);
        //스택 비우고 로그인만 남김
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}

