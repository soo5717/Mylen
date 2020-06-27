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
import com.example.mylen.data.lens.LensData;
import com.example.mylen.data.liquid.LiquidData;
import com.example.mylen.data.user.StatusResponse;
import com.example.mylen.feature.others.NavigationDrawer;
import com.example.mylen.feature.sign.SignInActivity;
import com.example.mylen.network.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.AlertDialog.THEME_HOLO_LIGHT;

public class AddLens3Activity extends AppCompatActivity {

    //전역변수 선언
    Button btn_exp_date, btn_open_date;
    String exp_date=null, open_date=null, name, brand, quantity, wear_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lens3);

        btn_exp_date = findViewById(R.id.btn_exp_date);
        btn_open_date = findViewById(R.id.btn_open_date);
    }

    //렌즈 등록 요청 - POST : Retrofit
    private void requestAddLens(LensData data){
        RetrofitClient.getService().addLens(data).enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(@NotNull Call<StatusResponse> call, @NotNull Response<StatusResponse> response) {
                StatusResponse result = response.body();
                assert result != null;
                if(result.getSuccess()){
                    //메인 페이지로 이동
                    Intent intent = new Intent(AddLens3Activity.this, NavigationDrawer.class);
                    //세척액 등록 엑티비티 스택에서 제거하고 메인페이지만 남김
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else{
                    //실패 시 처리 코드 추후 추가 예정
                }
            }

            @Override
            public void onFailure(@NotNull Call<StatusResponse> call, @NotNull Throwable t) {
                Log.e("렌즈 등록 에러 발생", Objects.requireNonNull(t.getMessage()));
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
            //렌즈 등록2 페이지 데이터 받기 + 렌즈 검색 페이지 데이터 받기
            Intent intent = getIntent();
            brand = intent.getStringExtra("lensBrand");
            name = intent.getStringExtra("lensName");
            quantity = intent.getStringExtra("lensQuantity");
            wear_date = intent.getStringExtra("lensWearDate");

            //렌즈 등록 요청 메소드 호출 : status 는 소지/비소지를 의미함.
            requestAddLens(new LensData(brand, name, quantity, wear_date, exp_date, open_date, 1));
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

    //back 버튼 클릭 이벤트: 이전 페이지로 이동
    public void backButtonClick(View view) {
        finish();
    }

    //clear 버튼 클릭 이벤트: 메인 페이지로 이동
    public void clearButtonClick(View view) {
        Intent intent = new Intent(this, NavigationDrawer.class);
        //스택 비우고 메인만 남김
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
