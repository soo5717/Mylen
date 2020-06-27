package com.example.mylen.feature.home.add;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylen.R;
import com.example.mylen.data.liquid.LiquidData;
import com.example.mylen.data.user.StatusResponse;
import com.example.mylen.feature.others.NavigationDrawer;
import com.example.mylen.network.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.AlertDialog.THEME_HOLO_LIGHT;

public class AddLiquid2Activity extends AppCompatActivity {

    //전역 변수 선언
    Button btn_exp_date, btn_open_date;
    String exp_date=null, open_date=null, name, brand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_liquid2);

        btn_exp_date = findViewById(R.id.btn_exp_date);
        btn_open_date = findViewById(R.id.btn_open_date);
    }

    //세척액 등록 요청 - POST : Retrofit
    private void requestAddLiquid(LiquidData data){
        RetrofitClient.getService().addLiquid(data).enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(@NotNull Call<StatusResponse> call, @NotNull Response<StatusResponse> response) {
                StatusResponse result = response.body();
                assert result != null;
                if(result.getSuccess()){
                    //메인 페이지로 이동
                    Intent intent = new Intent(AddLiquid2Activity.this, NavigationDrawer.class);
                    //세척액 등록 엑티비티 스택에서 제거하고 메인페이지만 남김
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else{
                    //실패 시 처리 코드 추후 추가 예정
                }
            }

            @Override
            public void onFailure(@NotNull Call<StatusResponse> call, @NotNull Throwable t) {
                Log.e("세척액 등록 에러 발생", Objects.requireNonNull(t.getMessage()));
            }
        });
    }


    //유통 기한, 개봉일 설정 메소드
    public void setDatePicker(Button btn){
        Calendar cal = Calendar.getInstance();
        //DatePicker 사용 : API-24에서는 스피너 작동 안함
        @SuppressLint("SetTextI18n") DatePickerDialog dialog = new DatePickerDialog(this, THEME_HOLO_LIGHT,
                (view, year, monthOfYear, dayOfMonth) -> btn.setText(year + "-" + (monthOfYear+1) + "-" + dayOfMonth)
                , cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    //완료 버튼 클릭 이벤트
    public void completeButtonClick(View view) {
        exp_date = btn_exp_date.getText().toString();
        open_date = btn_open_date.getText().toString();

        //유효성 검사 통과
        if(exp_date.length() > 0){ //개봉일 입력
            //세척액 등록1 페이지 데이터 받기
            Intent intent = getIntent();
            brand = intent.getStringExtra("liquidBrand");
            name = intent.getStringExtra("liquidName");

            //세척액 등록 요청 메소드 호출 : status 는 소지/비소지를 의미함.
            requestAddLiquid(new LiquidData(brand, name, exp_date, open_date, 1));
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

    //*서윤*
    //등록완료버튼 누를 때
    //유통기한, 개봉일 정보 notice로 넘기기
    /*
    Intent addNoticeIntent = new Intent(this, AddNoticeData.class);
    //expdate : 0000/00/00(String)
    addNoticeIntent.putExtra("lens_expdate", lens_expdate);
    if (liquid_opendate != null){
        Boolean boolOpenData = true;
        addNoticeIntent.putExtra("boolOpenData", boolOpenData);
        //opendate : 0000/00/00(String)
        addNoticeIntent.putExtra("liquid_opendate", liquid_opendate);
        //weardate : String
        addNoticeIntent.putExtra("liquid_weardate", liquid_weardate);
    } else{
        Boolean boolOpenData = false;
        addNoticeIntent.putExtra("boolOpenData", boolOpenData);
    }
    startActivityForResult(addNoticeIntent, 102);
    */
}
