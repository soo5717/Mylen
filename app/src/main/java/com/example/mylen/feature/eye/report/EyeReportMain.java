package com.example.mylen.feature.eye.report;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;

import com.example.mylen.R;
import com.example.mylen.data.eye.FriendMainResponse;
import com.example.mylen.data.eye.ReportResponse;
import com.example.mylen.feature.eye.main.EyeMainFriendItem;
import com.example.mylen.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EyeReportMain extends AppCompatActivity {

    Fragment EyeReportFragmentPoint, EyeReportFragmentCount;

    FragmentManager fragmentManager;
    FragmentTransaction transaction_point;
    FragmentTransaction transaction_count;

    TextView tv_total_point, tv_average_count, tv_total_count;

    int totalPoint;
    int totalCount;
    float averageDailyCount;

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

        //친구정보 가져오기
        RetrofitClient.getService().eyeReport().enqueue(new Callback<ReportResponse>() {

            @Override
            public void onResponse(Call<ReportResponse> call, Response<ReportResponse> response) {

                ReportResponse result = response.body();
                assert result != null;

                //포인트가져옴
                totalPoint = result.getTotalPoint();
                totalCount = result.getTotalCount();
                averageDailyCount = result.getAverageDailyCount();

                if(result.getSuccess()) {
                    tv_total_point.setText(String.valueOf(totalPoint) + 'P');
                    tv_average_count.setText(String.valueOf(Math.round(averageDailyCount*10)/10.0) + '회');
                    tv_total_count.setText(String.valueOf(totalCount)+ '회');
                }
            }

            @Override
            public void onFailure(Call<ReportResponse> call, Throwable t) {
                Toast.makeText(EyeReportMain.this, "report 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("report 에러 발생", t.getMessage());
            }
        });
    }
}
