package com.example.mylen.feature.profile;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylen.R;

import java.util.Calendar;

public class ProfileModifyActivity extends AppCompatActivity {
    EditText et_name;
    Button btn_birth;
    Spinner spn_sph_left, spn_sph_right;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_modify);

        et_name = (EditText)findViewById(R.id.et_name);
        btn_birth = (Button)findViewById(R.id.btn_birth);
//        spn_sph_left = (Spinner)findViewById(R.id.spn_sph_left);
//        spn_sph_right = (Spinner)findViewById(R.id.spn_sph_right);
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
        finish();
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
