package com.example.mylen.feature.notice.set;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mylen.R;
import com.example.mylen.data.notice.set.NoticeSetResponse;
import com.example.mylen.data.notice.set.UpdateNoticeSetData;
import com.example.mylen.data.user.StatusResponse;
import com.example.mylen.feature.notice.main.NoticeMain;
import com.example.mylen.network.RetrofitClient;


import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeSet extends AppCompatActivity implements View.OnClickListener {

    int userId;
    int setId;
    Button btn_lens_wear_over;
    Button btn_case_replace;
    Button btn_lens_clean;
    Button btn_lens_use_date;
    Button btn_lens_exp_date;
    Button btn_liquid_use_date;
    Button btn_liquid_exp_date;
    Button btn_fin_dust;
    int[] onOffArray;
    int[] getOnOffArray;
    Button[] buttonArray;
    Toolbar myToolbar;
    MenuInflater menuInflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_set);
//툴바 구현
        myToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(""); //ㅣ기존 타이틀 지우기
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        actionBar.setHomeAsUpIndicator(R.drawable.ic_backspace_48dp);

        userId = 2;

        btn_lens_wear_over = findViewById(R.id.btn_lens_wear_over);
        btn_case_replace = findViewById(R.id.btn_case_replace);
        btn_lens_clean = findViewById(R.id.btn_lens_clean);
        btn_lens_use_date = findViewById(R.id.btn_lens_use_date);
        btn_lens_exp_date = findViewById(R.id.btn_lens_exp_date);
        btn_liquid_use_date = findViewById(R.id.btn_liquid_use_date);
        btn_liquid_exp_date = findViewById(R.id.btn_liquid_exp_date);
        btn_fin_dust = findViewById(R.id.btn_fin_dust_time);

        buttonArray = new Button[] {btn_lens_wear_over, btn_case_replace, btn_lens_clean, btn_lens_use_date, btn_lens_exp_date, btn_liquid_use_date, btn_liquid_exp_date, btn_fin_dust};


        RetrofitClient.getService().userNoticeSet().enqueue(new Callback<NoticeSetResponse>() {
            @Override
            public void onResponse(Call<NoticeSetResponse> call, Response<NoticeSetResponse> response) {
                NoticeSetResponse result = response.body();
                assert result != null;

                if(result.getSuccess()) {
                    onOffArray = result.getOnOffArray();
                    Log.d("아싸", String.valueOf(onOffArray));

                    //button setting
                    for (int i = 0; i < onOffArray.length; i++) {
                        buttonSet(buttonArray[i], onOffArray[i]);
                    }
                }
            }

            @Override
            public void onFailure(Call<NoticeSetResponse> call, Throwable t) {
                Toast.makeText(NoticeSet.this, "noticeSet button setting 에러 발생", Toast.LENGTH_SHORT).show();

            }
        });

        btn_lens_wear_over.setOnClickListener(this);
        btn_lens_clean.setOnClickListener(this);
        btn_case_replace.setOnClickListener(this);
        btn_lens_use_date.setOnClickListener(this);
        btn_lens_exp_date.setOnClickListener(this);
        btn_liquid_use_date.setOnClickListener(this);
        btn_liquid_exp_date.setOnClickListener(this);
        btn_fin_dust.setOnClickListener(this);

    } // end onCreate()

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { // 뒤로가기 버튼 눌렀을 때
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_lens_wear_over :
                onOffArray[0] = buttonClickSet(btn_lens_wear_over, onOffArray[0]);
                setId = 0;
                break;

            case R.id.btn_lens_clean :
                onOffArray[1] = buttonClickSet(btn_lens_clean, onOffArray[1]);
                setId = 1;
                break;

            case R.id.btn_case_replace :
                onOffArray[2] = buttonClickSet(btn_case_replace, onOffArray[2]);
                setId = 2;
                break;

            case R.id.btn_lens_use_date :
                onOffArray[3] = buttonClickSet(btn_lens_use_date, onOffArray[3]);
                setId = 3;
                break;

            case R.id.btn_lens_exp_date :
                onOffArray[4] = buttonClickSet(btn_lens_exp_date, onOffArray[4]);
                setId = 4;
                break;

            case R.id.btn_liquid_use_date :
                onOffArray[5] = buttonClickSet(btn_liquid_use_date, onOffArray[5]);
                setId = 5;
                break;

            case R.id.btn_liquid_exp_date :
                onOffArray[6] = buttonClickSet(btn_liquid_exp_date, onOffArray[6]);
                setId = 6;
                break;

            case R.id.btn_fin_dust_time :
                onOffArray[7] = buttonClickSet(btn_fin_dust, onOffArray[7]);
                setId = 7;
                break;
        }

        UpdateNoticeSetData data_update = new UpdateNoticeSetData(userId, setId, onOffArray);
        setDB(data_update);
    }

    public int buttonClickSet(Button button, int btnOnOff){
        if(btnOnOff==0) {
            button.setBackground(getResources().getDrawable(R.drawable.btn_color_small_checked, null));
            button.setText(getResources().getText(R.string.notice_set_on));
            btnOnOff=1;
        }else {
            button.setBackground(getResources().getDrawable(R.drawable.btn_color_small_unchecked, null));
            button.setText(getResources().getText(R.string.notice_set_off));
            btnOnOff=0;
        }
        return btnOnOff;
    }

    public void buttonSet(Button button, int btnOnOff) {
        if (btnOnOff == 1) {
            button.setBackground(getResources().getDrawable(R.drawable.btn_color_small_checked, null));
            button.setText(getResources().getText(R.string.notice_set_on));
        } else {
            button.setBackground(getResources().getDrawable(R.drawable.btn_color_small_unchecked, null));
            button.setText(getResources().getText(R.string.notice_set_off));
        }
    }

    //db에 저장
    public void setDB(UpdateNoticeSetData data) {

        RetrofitClient.getService().updateNoticeSet(data).enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                StatusResponse result = response.body();
                assert result != null;
                Toast.makeText(NoticeSet.this, result.getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {
                Toast.makeText(NoticeSet.this, "noticeSet update 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("noticeSet update 에러 발생", Objects.requireNonNull(t.getMessage()));

            }
        });

    }
}
