package com.example.mylen.feature.home.add;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylen.R;
import com.example.mylen.feature.others.NavigationDrawer;
import com.example.mylen.feature.util.AdapterSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.Inflater;

public class AddLens2Activity extends AppCompatActivity {

    //전역변수 선언
    EditText et_quantity;
    Spinner spn_wear_date;
    AdapterSpinner adapterSpinner;

    String brand, name, quantity, wear_date = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lens2);

        et_quantity = findViewById(R.id.et_quantity);

        //착용기한 입력 스피너 구현
        ArrayList<String> arr_wear_date = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.lens_wear_date)));
        adapterSpinner = new AdapterSpinner(this, arr_wear_date);
        spn_wear_date = findViewById(R.id.spn_wear_date);
        spn_wear_date.setAdapter(adapterSpinner);
        spn_wear_date.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //포지션에 맞는 착용 기한 int 형으로 저장
                switch (position){
                    case 0:
                        wear_date = null;
                        break;
                    case 1:
                        wear_date = Integer.toString(1);
                        break;
                    case 2:
                        wear_date = Integer.toString(14);
                        break;
                    case 3:
                        wear_date = Integer.toString(30);
                        break;
                    case 4:
                        wear_date = Integer.toString(60);
                        break;
                    case 5:
                        wear_date = Integer.toString(90);
                        break;
                    case 6:
                        wear_date = Integer.toString(180);
                        break;
                    case 7:
                        wear_date = Integer.toString(365);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    //next 버튼 클릭 이벤트: 렌즈 등록3 페이지로 이동
    public void nextButtonClick(View view) {
        quantity = et_quantity.getText().toString();

        //수량, 착용 기한 유효성 검사 통과
        if(quantity.length() > 0 && wear_date != null){
            //렌즈 등록 1 페이지 데이터 받기
            Intent intent = getIntent();
            brand = intent.getStringExtra("lensBrand");
            name = intent.getStringExtra("lensName");

            Log.d("브랜드", brand);
            Log.d("제품명", name);
            Log.d("수량", quantity);
            Log.d("착용기한", wear_date);

            //렌즈 등록3 페이지로 브랜드, 제품명, 수량, 착용 기한 전달
            Intent intent2 = new Intent(this, AddLens3Activity.class);
            intent.putExtra("lensBrand", brand);
            intent.putExtra("lensName", name);
            intent.putExtra("lensQuantity", quantity);
            intent.putExtra("lensWearDate", wear_date);
            startActivity(intent2);

            //전환 애니메이션 없애기
            overridePendingTransition(0, 0);

        } else if(quantity.length() > 0){ //수량만 유효성 검사 통과
            Toast.makeText(getApplicationContext(), "착용기한을 입력해주세요!", Toast.LENGTH_LONG).show();
        } else if(wear_date != null){ //착용 기한만 유효성 검사 통과
            Toast.makeText(getApplicationContext(), "수량을 입력해주세요!", Toast.LENGTH_LONG).show();
            et_quantity.requestFocus(); //포커스 주기
        } else {
            Toast.makeText(getApplicationContext(), "수량과 착용기한을 입력해주세요!", Toast.LENGTH_LONG).show();
            et_quantity.requestFocus(); //포커스 주기
        }
    }

    //back 버튼 클릭 이벤트: 렌즈 등록1 페이지로 이동
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
