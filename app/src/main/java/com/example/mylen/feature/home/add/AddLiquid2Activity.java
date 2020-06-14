package com.example.mylen.feature.home.add;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylen.R;
import com.example.mylen.feature.others.NavigationDrawer;

import java.util.Calendar;

import static android.app.AlertDialog.THEME_HOLO_LIGHT;

public class AddLiquid2Activity extends AppCompatActivity {

    //전역 변수 선언
    Button btn_exp_date, btn_open_date;
    String exp_date, open_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_liquid2);

        btn_exp_date = findViewById(R.id.btn_exp_date);
        btn_open_date = findViewById(R.id.btn_open_date);
    }

    //세척액 등록 요청 - POST : Retrofit
    private void requestAddLiquid(){
        //네트워킹 부분 추가 예정

        //메인 페이지로 이동
        Intent intent = new Intent(this, NavigationDrawer.class);
        //세척액 등록 엑티비티 스택에서 제거하고 메인페이지만 남김
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    //유통 기한, 개봉일 설정 메소드
    public void setDatePicker(Button btn){
        Calendar cal = Calendar.getInstance();
        //DatePicker 사용 : API-24에서는 스피너 작동 안함
        @SuppressLint("SetTextI18n") DatePickerDialog dialog = new DatePickerDialog(this, THEME_HOLO_LIGHT,
                (view, year, monthOfYear, dayOfMonth) -> btn.setText(year + "/" + monthOfYear + "/" + dayOfMonth)
                , cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    //완료 버튼 클릭 이벤트
    public void completeButtonClick(View view) {
        exp_date = btn_exp_date.getText().toString();
        open_date = btn_open_date.getText().toString();

        Intent intent = getIntent();

        //유효성 검사 통과
        if(exp_date.length() > 0 && open_date.length() > 0){ //개봉일 입력
            //세척액 등록1 페이지 데이터 받기
            exp_date = intent.getStringExtra("liquidBrand");
            open_date = intent.getStringExtra("liquidName");

            //세척액 등록 요청 메소드 호출
            requestAddLiquid();
        }
        else if(exp_date.length() > 0){ //개봉일 미입력
            //세척액 등록1 페이지 데이터 받기
            exp_date = intent.getStringExtra("liquidBrand");
            open_date = intent.getStringExtra("liquidName");

            //세척액 등록 요청 메소드 호출
            requestAddLiquid();
        }
        else{ //유효성 검사 실패
            Toast.makeText(getApplicationContext(), "유통기한을 입력해주세요!", Toast.LENGTH_LONG).show();
        }
    }

    //exp_date 버튼 클릭 이벤트: 유통 기한 설정 메소드 호출
    public void expDateButtonClick(View view){
        setDatePicker(btn_exp_date);
    }

    //open_date 버튼 클릭 이벤트: 개봉일 설정 메소드 호출
    public void openDateButtonClick(View view){
        setDatePicker(btn_open_date);
    }

    //back 버튼 클릭 이벤트: 세척액 등록1 페이지로 이동
    public void backButtonClick(View view) {
        finish();
    }

    //clear 버튼 클릭 이벤트: 메인 페이지로 이동
    public void clearButtonClick(View view) {
        Intent intent = new Intent(this, NavigationDrawer.class);
        //스택 비우고 로그인만 남김
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
