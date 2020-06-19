package com.example.mylen.feature.eye.report;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mylen.R;

public class EyeReportMain extends AppCompatActivity {

    Fragment EyeReportFragmentPoint, EyeReportFragmentCount;

    FragmentManager fragmentManager;
    FragmentTransaction transaction_point;
    FragmentTransaction transaction_count;

    TextView tv_total_point, tv_average_count, tv_total_count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eyereport);

        EyeReportFragmentPoint = new EyeReportFragmentPoint();
        EyeReportFragmentCount = new EyeReportFragmentCount();

        fragmentManager = getSupportFragmentManager();
        transaction_point = fragmentManager.beginTransaction();
        transaction_count = fragmentManager.beginTransaction();


        transaction_point.replace(R.id.fl_graph_point, EyeReportFragmentPoint).commitNowAllowingStateLoss();
        transaction_count.replace(R.id.fl_graph_count, EyeReportFragmentCount).commitNowAllowingStateLoss();

        tv_total_point = findViewById(R.id.tv_total_point);
        tv_average_count = findViewById(R.id.tv_average_count);
        tv_total_count = findViewById(R.id.tv_total_count);

        tv_total_point.setText("123");
        tv_average_count.setText("234");
        tv_total_count.setText("345");






    }
}
