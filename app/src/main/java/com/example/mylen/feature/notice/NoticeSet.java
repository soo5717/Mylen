package com.example.mylen.feature.notice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import com.example.mylen.R;

import static com.example.mylen.R.drawable.btn_color_small_checked;

public class NoticeSet extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_set);

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


    } // end onCreate()

    @Override
    public void onClick(View view) {


    }


}
