package com.example.mylen.feature.profile;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ListPopupWindow;

import com.example.mylen.R;
import com.example.mylen.feature.util.AdapterSpinner;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class ProfileModifyActivity extends AppCompatActivity {

    //전역변수 선언
    EditText et_name;
    Button btn_birth;
    Spinner spn_sph_left, spn_sph_right;
    AdapterSpinner adapterSpinner;
    String name, birth, sph_left, sph_right;

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
//                Toast.makeText(getApplicationContext(),(String)spn_sph_left.getItemAtPosition(position)+"이 선택되었습니다.",Toast.LENGTH_SHORT).show();
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
//                Toast.makeText(getApplicationContext(),(String)spn_sph_right.getItemAtPosition(position)+"이 선택되었습니다.",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        //프로필 초기값 설정 -> 네트워크 구현 후 활성화!
//        setProfileModify();
    }

    //프로필 수정 요청 - PUT : Retrofit2
    private void requestProfileModify(){
        //네트워킹 부분 추가 예정

        //성공 시 프로필 페이지 종료 후, 현재 페이지 종료 후
        ProfileActivity PA = (ProfileActivity)ProfileActivity._ProfileActivity;
        PA.finish(); finish();
        //프로필 페이지 새로 생성 후 이동
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
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
        DatePickerDialog dialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        btn_birth.setText(year + "/" + monthOfYear + "/" + dayOfMonth);
                    }
                }
                , cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    //프로필 저장 버튼 클릭 이벤트 : 프로필 저장 페이지로 이동
    public void profileSaveButtonClick(View v){
        //프로필 수정 요청 메소드 호출
        requestProfileModify();
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
