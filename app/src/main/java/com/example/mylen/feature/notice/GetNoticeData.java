//로그인 시에, notices 테이블 정보 가져오기, 알림으로 저장해두기, 헤드업알림을 띄우기 위해 AlarmReceiver.java로 넘기기
//
package com.example.mylen.feature.notice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mylen.R;
import com.example.mylen.data.notice.NoticeData;
import com.example.mylen.data.notice.NoticeResponse;
import com.example.mylen.network.RetrofitClient;
import com.example.mylen.network.ServiceApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetNoticeData extends AppCompatActivity {
    private Button bt_getdata;
    private TextView tv_1;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_test);


        bt_getdata = findViewById(R.id.bt_data);
        tv_1 = findViewById(R.id.tv_1);


        bt_getdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartGet();
            }
        });
        //StartGet();
    }

    // db에서 알림 정보 가져오기
    public void StartGet () {

        //Intent intent = getIntent();
        //int userId = intent.getIntExtra("user_id");
        int userId = 2;
        NoticeData data = new NoticeData(userId);
        final String[] kk = new String[1];

        //알림 정보 가져오기
        RetrofitClient.getService().userNotice(data).enqueue(new Callback<NoticeResponse>() {

            @Override
            public void onResponse(Call<NoticeResponse> call, Response<NoticeResponse> response) {
                NoticeResponse result = response.body();

                //알림메시지, 알림날짜, 시간 가져옴
                String[] msgArray = result.getmsgArray();
                String[] dateArray = result.getdateArray();
                String[] timeArray = result.gettimeArray();

                Toast.makeText(GetNoticeData.this, "Alarm 예정 " + String.valueOf(timeArray[0]) + "시 "  + "분", Toast.LENGTH_SHORT).show();

                //알림 갯수만큼 날짜 및 시간설정, 알림설정
                for (int i = 0; i < msgArray.length; i++) {

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());

                    //날짜설정
                    String[] temp_date = dateArray[i].split(" ");
                    calendar.set(Calendar.YEAR, Integer.parseInt(temp_date[0]));
                    calendar.set(Calendar.MONTH, Integer.parseInt(temp_date[1])-1);
                    calendar.set(Calendar.DATE, Integer.parseInt(temp_date[2]));

                    //시간설정
                    String[] temp_time = timeArray[i].split(" ");
                    calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(temp_time[0]));
                    calendar.set(Calendar.MINUTE, Integer.parseInt(temp_time[1]));
                    calendar.set(Calendar.SECOND, 0);

                    //현재 시간 이후 알림, 알림설정
                    if(!calendar.before(Calendar.getInstance())){

                        Date noticeTime1 = calendar.getTime();
                        String date_text1 = new SimpleDateFormat("yyyy년 MM월 dd일 EE요일 hh시 mm분\n ", Locale.getDefault()).format(noticeTime1);
                        kk[0] = kk[0] +  date_text1;

                        //알림manager실행
                        StartNotification(calendar, msgArray[i], i);

                    } else{  //현재 시간 이전 알림, 패스

                        Date noticeTime1 = calendar.getTime();
                        String date_text1 = new SimpleDateFormat("yyyy년 MM월 dd일 EE요일 hh시 mm분\n ", Locale.getDefault()).format(noticeTime1);
                        kk[0] = kk[0] + "지난 시간의 알림" +date_text1;
                    }
                }
                tv_1.setText(kk[0]);
            }


            @Override
            public void onFailure(Call<NoticeResponse> call, Throwable t) {
                Toast.makeText(GetNoticeData.this, "가져오기 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("가져오기 에러 발생", t.getMessage());
            }
        });
    }


    //알림manager실행, 헤드업알림(AlarmReceiver)로 넘기기
    void StartNotification(Calendar calendar, String notice_msg, int count) {

        //set에서 가져와야함
        Boolean dailyNotify = true; // 무조건 알람을 사용

        //부팅 후에도 유지 가능하도록 하는 리시버
        PackageManager pm = this.getPackageManager();
        ComponentName receiver = new ComponentName(this, DeviceBootReceiver.class);

        //AlarmReceiver에 알림메시지와 정보 넘기기
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        alarmIntent.putExtra("notice_msg", notice_msg);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, count, alarmIntent, 0);
        //pendingIntent = PendingIntent.getBroadcast(this, count, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);


        // 사용자가 매일 알람을 허용했다면
        if (dailyNotify) {

            //알림manager실행
            if (alarmManager != null) {
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                }
            }

            // 부팅 후 실행되는 리시버 사용가능하게 설정
            pm.setComponentEnabledSetting(receiver,
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP);

        }
//        else { //Disable Daily Notifications
//            if (PendingIntent.getBroadcast(this, 0, alarmIntent, 0) != null && alarmManager != null) {
//                alarmManager.cancel(pendingIntent);
//                //Toast.makeText(this,"Notifications were disabled",Toast.LENGTH_SHORT).show();
//            }
//            pm.setComponentEnabledSetting(receiver,
//                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
//                    PackageManager.DONT_KILL_APP);
//        }
    }
}
