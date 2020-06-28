package com.example.mylen.feature.calendar;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.applandeo.materialcalendarview.CalendarView;
import com.example.mylen.R;
import com.example.mylen.data.calendar.CalendarData;
import com.example.mylen.data.calendar.CalendarDateResponse;
import com.example.mylen.data.calendar.CalendarResponse;
import com.example.mylen.network.RetrofitClient;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalendarDate extends AppCompatActivity {
    private Toolbar myToolbar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CalendarDateAdapter calendarDateAdapter;
    String[] wear_date;
    String[] wear_start;
    String[] wear_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_date);
        setCalendarDate();
        //서버에서 가져오기

    }

    public void setCalendarDate(){
        RetrofitClient.getService().addDateCalendar().enqueue(new Callback<CalendarDateResponse>() {

            @Override
            public void onResponse(Call<CalendarDateResponse> call, Response<CalendarDateResponse> response) {
                CalendarDateResponse result = response.body();
                wear_date = result.getWearDate();
                wear_start = result.getWearStart();
                wear_end = result.getWearEnd();
                //Date[] wear_date_d = new Date[wear_date.length];
                DateFormat formatter = new SimpleDateFormat("HH:mm");

                Time start_value[] = new Time[wear_date.length];
                Time end_value[] = new Time[wear_date.length];
                long hours_value[] = new long[wear_date.length];

                try {
                    for(int i=0; i<wear_date.length; i++){
                        start_value[i] = new Time(formatter.parse(wear_start[i]).getTime());
                        end_value[i] = new Time(formatter.parse(wear_end[i]).getTime());
                        hours_value[i] = (end_value[i].getTime() - start_value[i].getTime());
                        hours_value[i] = hours_value[i]/3600000;
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(CalendarDate.class.getName()).log(Level.SEVERE, null, ex);
                }

                for(int i=0; i<wear_date.length; i++)
                {
                    String wear_day[] = wear_date[i].split("-");
                    String wear_day2[] = wear_day[2].split("T");
                    wear_date[i] = wear_day[1]+"월 "+wear_day2[0]+"일";

                    String wear_day3[] = wear_start[i].split(":");
                    wear_start[i] = wear_day3[0]+"시 "+wear_day3[1]+"분";
                    String wear_day4[] = wear_end[i].split(":");
                    wear_end[i] = wear_day4[0]+"시 "+wear_day4[1]+"분";

                }

                layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView = findViewById(R.id.recycler_calendar_date);
                recyclerView.setLayoutManager(layoutManager);
                calendarDateAdapter = new CalendarDateAdapter(getApplicationContext());
                for(int i=0; i<wear_date.length; i++)
                {
                    calendarDateAdapter.addDateItem(new CalendarDateItems(wear_date[i], wear_start[i], wear_end[i], String.valueOf(hours_value[i])+"시간"));
                }
                recyclerView.setAdapter(calendarDateAdapter);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
            }

            @Override
            public void onFailure(Call<CalendarDateResponse> call, Throwable t) {

            }

        });
        //툴바 구현
        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        actionBar.setHomeAsUpIndicator(R.drawable.ic_backspace_48dp);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: { // 뒤로가기 버튼 눌렀을 때
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}