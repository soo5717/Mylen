//정보 notices 테이블에 넣기
/*
[notice테이블이 변해야하는 경우]
1. 등록
렌즈 등록시 (완료)
세척액 등록시 (완료)
렌즈 케이스 시작 어떻게표시?
메인 - 오늘의 세척여부
메인 - 착용시작 -> +8시간으로(db에넣기)

2. 수정
메인 - 보관함 - 사용하기버튼 -> 서버에서
modify 하는 경우 //update 서버만들어야함.

[테이블 변동 시, 해당 알림 따로 생성]
서버에서 register테이블이 변하면 자동으로 변하게 하는 것이 가능한가?
 */
package com.example.mylen.feature.notice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mylen.R;
import com.example.mylen.data.notice.notice.AddNoticeData;
import com.example.mylen.data.notice.notice.AddNoticeResponse;
import com.example.mylen.network.RetrofitClient;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PutNoticeData extends AppCompatActivity {

    private String[] noticeDatetime = new String[8];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_test);

        Intent intent = getIntent();

        //onActivityResult();
        ReadyAddLens();
        ReadyAddLiquid();
    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //AddLens화면에서 넘어온 intent
        if (requestCode == 101){
            ReadyAddLens();
        }
        if (requestCode == 102){
            ReadyAddLiquid();
        }

    }
*/

    private void ReadyAddLens() {

        Intent intent = getIntent();

        if(intent.getBooleanExtra("boolOpenData", false)){

            String[] temp_opendate = intent.getStringExtra("lens_opendate").split("/");
            int weardate = Integer.parseInt(intent.getStringExtra("lens_weardate"));

            //개봉일
            Calendar cal_open = Calendar.getInstance();
            cal_open.setTimeInMillis(System.currentTimeMillis());
            cal_open.set(Calendar.YEAR, Integer.parseInt(temp_opendate[0]));
            cal_open.set(Calendar.MONTH, Integer.parseInt(temp_opendate[1])-1);
            cal_open.set(Calendar.DATE, Integer.parseInt(temp_opendate[2]));

            //사용기한
            cal_open.add(Calendar.DATE, weardate);
            Date useDate = cal_open.getTime();
            String usedate_formed = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(useDate);

            //useDate
            noticeDatetime[3] = usedate_formed + " 09:00:00";
        }

        //유통기한
        String[] temp_expdate = intent.getStringExtra("lens_expdate").split("/");
        Calendar cal_exp = Calendar.getInstance();
        cal_exp.setTimeInMillis(System.currentTimeMillis());
        cal_exp.set(Calendar.YEAR, Integer.parseInt(temp_expdate[0]));
        cal_exp.set(Calendar.MONTH, Integer.parseInt(temp_expdate[1])-1);
        cal_exp.set(Calendar.DATE, Integer.parseInt(temp_expdate[2]));
        Date expDate = cal_exp.getTime();
        String expdate_formed = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(expDate);

        //expDate
        noticeDatetime[4] = expdate_formed + " 09:00:00";

        Toast.makeText(PutNoticeData.this, intent.getStringExtra("lens_expdate"), Toast.LENGTH_SHORT).show();


        int userId = Integer.parseInt(intent.getStringExtra("user_id"));
        //lensAdd(int userId, int setId, String noticeDatetime)
        //서버에서 setId에 맞게 메시지 넣도록 했음
        for (int i=0; i<noticeDatetime.length; i++){
            if (noticeDatetime[i] != null){
                AddData(new AddNoticeData(userId, i, noticeDatetime[i]));
            }
        }
    }

    private void ReadyAddLiquid() {

        Intent intent = getIntent();

        if(intent.getBooleanExtra("boolOpenData", false)){

            String[] temp_opendate = intent.getStringExtra("lens_opendate").split("/");
            int weardate = Integer.parseInt(intent.getStringExtra("lens_weardate"));

            //개봉일
            Calendar cal_open = Calendar.getInstance();
            cal_open.setTimeInMillis(System.currentTimeMillis());
            cal_open.set(Calendar.YEAR, Integer.parseInt(temp_opendate[0]));
            cal_open.set(Calendar.MONTH, Integer.parseInt(temp_opendate[1])-1);
            cal_open.set(Calendar.DATE, Integer.parseInt(temp_opendate[2]));

            //사용기한
            cal_open.add(Calendar.DATE, weardate);
            Date useDate = cal_open.getTime();
            String usedate_formed = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(useDate);

            //useDate
            noticeDatetime[5] = usedate_formed + " 09:00:00";
        }

        //유통기한
        String[] temp_expdate = intent.getStringExtra("lens_expdate").split("/");
        Calendar cal_exp = Calendar.getInstance();
        cal_exp.setTimeInMillis(System.currentTimeMillis());
        cal_exp.set(Calendar.YEAR, Integer.parseInt(temp_expdate[0]));
        cal_exp.set(Calendar.MONTH, Integer.parseInt(temp_expdate[1])-1);
        cal_exp.set(Calendar.DATE, Integer.parseInt(temp_expdate[2]));
        Date expDate = cal_exp.getTime();
        String expdate_formed = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(expDate);

        //expDate
        noticeDatetime[6] = expdate_formed + " 09:00:00";

        Toast.makeText(PutNoticeData.this, intent.getStringExtra("lens_expdate"), Toast.LENGTH_SHORT).show();


        int userId = Integer.parseInt(intent.getStringExtra("user_id"));
        //lensAdd(int userId, int setId, String noticeDatetime)
        //서버에서 setId에 맞게 메시지 넣도록 했음
        for (int i=0; i<noticeDatetime.length; i++){
            if (noticeDatetime[i] != null){
                AddData(new AddNoticeData(userId, i, noticeDatetime[i]));
            }
        }
    }

    //notices 테이블로 넣기
    private void AddData(AddNoticeData data) {

        RetrofitClient.getService().addLensNotice(data).enqueue(new Callback<AddNoticeResponse>() {
            @Override
            public void onResponse(Call<AddNoticeResponse> call, Response<AddNoticeResponse> response) {
                AddNoticeResponse result = response.body();
                Toast.makeText(PutNoticeData.this, result.getMessage(), Toast.LENGTH_SHORT).show();

                //성공
                if (result.getCode() == 200) {
                    //finish();
                }
            }

            @Override
            public void onFailure(Call<AddNoticeResponse> call, Throwable t) {
                Toast.makeText(PutNoticeData.this, "알림정보 put 에러 발생", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
