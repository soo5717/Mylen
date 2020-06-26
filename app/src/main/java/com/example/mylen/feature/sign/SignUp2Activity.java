package com.example.mylen.feature.sign;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylen.R;
import com.example.mylen.data.user.SignUpData;
import com.example.mylen.data.user.StatusResponse;
import com.example.mylen.network.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.AlertDialog.THEME_HOLO_LIGHT;

public class SignUp2Activity  extends AppCompatActivity {

    //전역변수 선언
    EditText et_name;
    Button btn_birth;
    String email, pwd, name, birth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        et_name = findViewById(R.id.et_name);
        btn_birth = findViewById(R.id.btn_birth);
    }

    //회원가입 요청 - POST : Retrofit2
    private void requestSignUp(SignUpData data) {
        RetrofitClient.getService().userSignUp(data).enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(@NotNull Call<StatusResponse> call, @NotNull Response<StatusResponse> response) {
                StatusResponse result = response.body();
                assert result != null;
                Toast.makeText(SignUp2Activity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                //회원가입 요청 성공 시
                if(result.getSuccess()) {
                    //로그인 페이지로 이동
                    Intent intent = new Intent(SignUp2Activity.this, SignInActivity.class);
                    //회원가입 엑티비티 스택에서 제거하고 로그인만 남김
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(@NotNull Call<StatusResponse> call, @NotNull Throwable t) {
                Toast.makeText(SignUp2Activity.this, "회원가입 에러가 발생했습니다", Toast.LENGTH_SHORT).show();
                Log.e("회원가입 에러 발생", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    //생년월일 설정 메소드
    public void setDatePicker(){
        Calendar cal = Calendar.getInstance();
        //DatePicker 사용 : API-24에서는 스피너 작동 안함
        @SuppressLint("SetTextI18n") DatePickerDialog dialog = new DatePickerDialog(this, THEME_HOLO_LIGHT,
                (view, year, monthOfYear, dayOfMonth) ->
                        btn_birth.setText(year + "-" + (monthOfYear+1) + "-" + dayOfMonth)
                , cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    //완료 버튼 클릭 이벤트
    public void completeButtonClick(View view) {
        name = et_name.getText().toString();
        birth = btn_birth.getText().toString();

        //이름, 생년월일 유효성 검사 통과
        if (name.length() != 0 && birth.length() != 0){
            //회원가입1 페이지 데이터 받기
            Intent intent = getIntent();
            email = intent.getStringExtra("signUpEmail");
            pwd = intent.getStringExtra("signUpPwd");

            //비밀번호 암호화
            assert pwd != null;
            String encrypt_pwd  = EncryptSHA512.encryptSHA512(pwd);

            //회원가입 요청 메소드 호출
            requestSignUp(new SignUpData(email, encrypt_pwd, name, birth));
        }
        else if (name.length() != 0) { //이름만 유효성 검사 통과
            Toast.makeText(getApplicationContext(), "생년월일을 입력해주세요!", Toast.LENGTH_LONG).show();
        }
        else if (birth.length() != 0) { //생년월일만 유효성 검사 통과
            Toast.makeText(getApplicationContext(), "이름을 입력해주세요!", Toast.LENGTH_LONG).show();
            et_name.requestFocus(); //포커스 주기
        }
        else { //유효성 검사 실패
            Toast.makeText(getApplicationContext(), "이름과 생년월일을 입력해주세요!", Toast.LENGTH_LONG).show();
            et_name.requestFocus(); //포커스 주기
        }
    }

    //birth 버튼 클릭 이벤트 : 생년월일 설정 메소드 호출
    public void birthButtonClick(View view) {
        setDatePicker();
    }

    //back 버튼 클릭 이벤트: 회원가입1 페이지로 이동
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