package com.example.mylen.feature.profile;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylen.R;
import com.example.mylen.data.user.ProfileData;
import com.example.mylen.data.user.StatusResponse;
import com.example.mylen.feature.sign.SignUp2Activity;
import com.example.mylen.feature.util.AdapterSpinner;
import com.example.mylen.network.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileModifyActivity extends AppCompatActivity {

    //전역변수 선언
    EditText et_name;
    Button btn_birth;
    Spinner spn_sph_left, spn_sph_right;
    AdapterSpinner adapterSpinner;
    String name, birth, sph_left=null, sph_right=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_modify);

        et_name = findViewById(R.id.et_name);
        btn_birth = findViewById(R.id.btn_birth);

        //SPH 왼쪽 스피너 구현
        ArrayList<String> arr_sph = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.eye_sph)));
        adapterSpinner = new AdapterSpinner(this, arr_sph);
        spn_sph_left = findViewById(R.id.spn_sph_left);
        spn_sph_left.setAdapter(adapterSpinner);
        spn_sph_left.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sph_left = (String)spn_sph_left.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //SPH 오른쪽 스피너 구현
        spn_sph_right = findViewById(R.id.spn_sph_right);
        spn_sph_right.setAdapter(adapterSpinner);
        spn_sph_right.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sph_right = (String)spn_sph_right.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        //프로필 초기값 설정 -> 네트워크 구현 후 활성화!
        setProfileModify();
    }

    //프로필 수정 요청 - PUT : Retrofit2
    private void requestProfileModify(ProfileData data){
        RetrofitClient.getService().userProfileModify(data).enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(@NotNull Call<StatusResponse> call, @NotNull Response<StatusResponse> response) {
                StatusResponse result = response.body();
                assert result != null;
                if(result.getSuccess()){
                    //성공 시 프로필 페이지 종료 후, 현재 페이지 종료 후
                    ProfileActivity PA = (ProfileActivity)ProfileActivity._ProfileActivity;
                    PA.finish(); finish();
                    //프로필 페이지 새로 생성 후 이동
                    Intent intent = new Intent(ProfileModifyActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }else{
                    //실패 시 처리 코드 추후 추가 예정
                }
            }

            @Override
            public void onFailure(@NotNull Call<StatusResponse> call, @NotNull Throwable t) {
                Log.e("프로필 수정 에러 발생", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    //프로필 수정 초기값 설정 메소드
    public void setProfileModify(){
        int left_position, right_position;

        //프로필 페이지 데이터 받기
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        birth = intent.getStringExtra("birth");

        left_position = intent.getIntExtra("sphLeft", 0);
        right_position = intent.getIntExtra("sphRight", 0);

        //sph 가 null 인 경우 0으로 초기화
        if(left_position == -1)
            left_position = 0;
        if(right_position == -1)
            right_position = 0;

        Log.d("왼쪽 포지션 확인", Integer.toString(left_position));
        Log.d("오른쪽 포지션 확인", Integer.toString(right_position));

        //초기값 설정
        et_name.setText(name);
        btn_birth.setText(birth);
        spn_sph_left.setSelection(left_position);
        spn_sph_right.setSelection(right_position);
    }

    //생년월일 설정 메소드
    public void setDatePicker(){
        Calendar cal = Calendar.getInstance();
        //DatePicker 사용 : API24에서는 스피너 작동 안함
        @SuppressLint("SetTextI18n") DatePickerDialog dialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT,
                (view, year, monthOfYear, dayOfMonth) -> btn_birth.setText(year + "-" + (monthOfYear+1) + "-" + dayOfMonth)
                , cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    //프로필 저장 버튼 클릭 이벤트 : 프로필 저장 페이지로 이동
    public void profileSaveButtonClick(View v){
        name = et_name.getText().toString();
        birth = btn_birth.getText().toString();

        //이름, 생년월일 유효성 검사 통과
        if (name.length() != 0 && birth.length() != 0){
            //프로필 수정 요청 메소드 호출
            requestProfileModify(new ProfileData(name, birth, sph_left, sph_right));
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

    //back 버튼 클릭 이벤트: 프로필 페이지로 이동
    public void backButtonClick(View view) {
        finish();
    }
}
