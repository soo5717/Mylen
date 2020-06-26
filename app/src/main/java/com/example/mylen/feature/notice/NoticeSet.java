package com.example.mylen.feature.notice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mylen.R;
import com.example.mylen.data.notice.AddNoticeResponse;
import com.example.mylen.data.notice.NoticeSetData;
import com.example.mylen.data.notice.NoticeSetResponse;
import com.example.mylen.data.notice.UpdateNoticeSetData;
import com.example.mylen.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeSet extends AppCompatActivity implements View.OnClickListener {

    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_set);

        userId = 2;

        Button btn_lens_wear_over;
        Button btn_case_replace;
        Button btn_lens_clean;
        Button btn_lens_use_date;
        Button btn_lens_exp_date;
        Button btn_liquid_use_date;
        Button btn_liquid_exp_date;
        Button btn_fin_dust_time;

        btn_lens_wear_over = findViewById(R.id.btn_lens_wear_over);
        btn_case_replace = findViewById(R.id.btn_case_replace);
        btn_lens_clean = findViewById(R.id.btn_lens_clean);
        btn_lens_use_date = findViewById(R.id.btn_lens_use_date);
        btn_lens_exp_date = findViewById(R.id.btn_lens_exp_date);
        btn_liquid_use_date = findViewById(R.id.btn_liquid_use_date);
        btn_liquid_exp_date = findViewById(R.id.btn_liquid_exp_date);
        btn_fin_dust_time = findViewById(R.id.btn_fin_dust_time);

        btn_lens_wear_over.setOnClickListener(this);
        btn_case_replace.setOnClickListener(this);
        btn_lens_clean.setOnClickListener(this);
        btn_lens_use_date.setOnClickListener(this);
        btn_lens_exp_date.setOnClickListener(this);
        btn_liquid_use_date.setOnClickListener(this);
        btn_liquid_exp_date.setOnClickListener(this);
        btn_fin_dust_time.setOnClickListener(this);

        NoticeSetData data = new NoticeSetData(userId);

        RetrofitClient.getService().userNoticeSet(data).enqueue(new Callback<NoticeSetResponse>() {
            @Override
            public void onResponse(Call<NoticeSetResponse> call, Response<NoticeSetResponse> response) {
                NoticeSetResponse result = response.body();
                Toast.makeText(NoticeSet.this, String.valueOf(result.getOnOffArray()), Toast.LENGTH_SHORT).show();

                //0,1에 따라 button setting구현
            }

            @Override
            public void onFailure(Call<NoticeSetResponse> call, Throwable t) {
                Toast.makeText(NoticeSet.this, "noticeSet button setting 에러 발생", Toast.LENGTH_SHORT).show();

            }
        });

    } // end onCreate()

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_lens_wear_over :
                int setId = 0;


        }
    }
}
