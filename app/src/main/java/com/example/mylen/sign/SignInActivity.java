package com.example.mylen.sign;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylen.R;

public class SignInActivity extends AppCompatActivity {

    EditText et_email, et_pwd;
    String email, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //이메일 EditText 입력 변화 이벤트
        et_email = (EditText)findViewById(R.id.et_email);
//        et_email.addTextChangedListener(new TextWatcher() {
//            //입력 중
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                //이메일 유효성 검사 success
//                if(isValidEmail(et_email.getText().toString())) {
//                    et_email.setTextColor(getResources().getColor(R.color.soft_black));
//                }
//                else { //이메일 유효성 검사 fail
//                    et_email.setTextColor(getResources().getColor(R.color.coral_red));
//                    Log.d("이메일 유효성", "fail");
//                }
//            }
//            //입력 완료
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//            }
//            //입력 전
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });
//        et_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(hasFocus) {
//                    et_email.setTextColor(getResources().getColor(R.color.soft_black));
//                }
//                else {
//                    //이메일 유효성 검사 success
//                    if(isValidEmail(et_email.getText().toString())) {
//
//                    }
//                    else { //이메일 유효성 검사 fail
//                        et_email.setTextColor(getResources().getColor(R.color.coral_red));
//                    }
//                }
//            }
//        });

        //비밀번호 EditText 입력 변화 이벤트
        et_pwd = (EditText)findViewById(R.id.et_pwd);
    }

    //로그인 버튼 클릭 이벤트
    public void signInButtonClick(View view) {
        email = et_email.getText().toString();
        pwd = et_pwd.getText().toString();

    }

    //회원가입 버튼 클릭 이벤트 : 회원가입 창으로 이동
    public void signUpButtonClick(View view) {
        Intent intent = new Intent(this, SignUp1Activity.class);
        startActivity(intent);
    }

    //이메일 유효성 검사 메소드
    public boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
