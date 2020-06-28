package com.example.mylen.feature.eye.exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mylen.R;
import com.example.mylen.feature.eye.report.EyeReportMain;

public class EyeEnd extends AppCompatActivity {

    TextView tv_get_point;
    TextView tv_end_point;
    Button bt_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eyeexercise_end);

        tv_get_point = findViewById(R.id.tv_get_point);
        tv_end_point = findViewById(R.id.tv_end_point);
        bt_bottom = findViewById(R.id.bt_bottom);

        tv_get_point.setText("30");
        tv_end_point.setText("30");

        bt_bottom.setOnClickListener(this::onClick);


    }

    private void onClick(View view) {
        Intent intent = new Intent(EyeEnd.this, EyeReportMain.class);
        startActivity(intent);
    }
}
