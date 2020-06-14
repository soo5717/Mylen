package com.example.mylen.feature.home.add;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylen.R;

public class AddLiquid1Activity extends AppCompatActivity{

    //전역변수 선언
    EditText et_brand, et_name;
    String brand, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_liquid1);

        et_brand = findViewById(R.id.et_brand);
        et_name = findViewById(R.id.et_name);
    }

    //next 버튼 클릭 이벤트: 세척액 등록2 페이지로 이동
    public void nextButtonClick(View view){
        brand = et_brand.getText().toString();
        name = et_name.getText().toString();

        //브랜드, 제품명 유효성 검사 통과
        if(brand.length() > 0 && name.length() > 0){
            //렌즈 등록2 페이지로 브랜드, 제품명 전달
            Intent intent = new Intent(this, AddLiquid2Activity.class);
            intent.putExtra("liquidBrand", brand);
            intent.putExtra("liquidName", name);
            startActivity(intent);

            //전환 애니메이션 없애기
            overridePendingTransition(0, 0);
        }
        else if(brand.length() > 0){ //브랜드만 유효성 검사 통과
            Toast.makeText(getApplicationContext(), "제품명을 입력해주세요!", Toast.LENGTH_LONG).show();
            et_name.requestFocus(); //포커스 주기
        }
        else if(name.length() > 0){ //제품명만 유효성 검사 통과
            Toast.makeText(getApplicationContext(), "브랜드를 입력해주세요!", Toast.LENGTH_LONG).show();
            et_brand.requestFocus(); //포커스 주기
        }
        else{
            Toast.makeText(getApplicationContext(), "브랜드와 제품명을 입력해주세요!", Toast.LENGTH_LONG).show();
            et_brand.requestFocus(); //포커스 주기
        }
    }

    //back 버튼 클릭 이벤트: 메인 페이지로 이동
    public void backButtonClick(View view) {
        finish();
    }

    //clear 버튼 클릭 이벤트: 메인 페이지로 이동
    public void clearButtonClick(View view) {
        finish();
    }
}
