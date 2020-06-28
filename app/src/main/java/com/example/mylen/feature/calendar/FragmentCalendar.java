package com.example.mylen.feature.calendar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.mylen.R;
import com.example.mylen.data.calendar.CalendarData;
import com.example.mylen.data.calendar.CalendarResponse;
import com.example.mylen.data.user.ProfileResponse;
import com.example.mylen.feature.notice.GetNoticeData;
import com.example.mylen.network.RetrofitClient;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCalendar extends Fragment {
    private RecyclerView recyclerView;
    private CalendarItems adapter;
    View view;
    private CalendarView calendarView;
    private RecyclerView.LayoutManager layoutManager;
    Calendar clickedDayCalendar;
    String clickedstr;
    CalendarAdapter adapter_calendar;
    String[] lens_exp_date;
    String[] lens_open_date;
    String[] lens_wear_date;
    String[] liquid_exp_date;
    String[] liquid_open_date;
    String result_date;
    public Date[] convert_date;
    Date lens_exp_d[];
    Date lens_open_d[];
    Date liquid_exp_d[];
    Date liquid_open_d[];
    Boolean status = false;
    Boolean wear_status = false;
    Boolean liquid_status = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calendar, container, false);

        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView = view.findViewById(R.id.recycler_calendar);
        recyclerView.setLayoutManager(layoutManager);

        //캘린더 날짜 가져오기
        calendarView = view.findViewById(R.id.calendarView);
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                clickedDayCalendar = eventDay.getCalendar();
                clickedstr = clickedDayCalendar.toString();
                //Toast.makeText(getActivity(), clickedstr, Toast.LENGTH_LONG).show();

                String target_year = "YEAR";
                int target_num_year = clickedstr.indexOf(target_year);
                String result_year = clickedstr.substring(target_num_year + clickedstr.substring(target_num_year).indexOf("=") + 1, (clickedstr.substring(target_num_year).indexOf(",") + target_num_year));
                //Toast.makeText(getActivity(), result_year, Toast.LENGTH_LONG).show();

                String target_month = "MONTH";
                int target_num_month = clickedstr.indexOf(target_month);
                String result_month = clickedstr.substring(target_num_month + clickedstr.substring(target_num_month).indexOf("=") + 1, (clickedstr.substring(target_num_month).indexOf(",") + target_num_month));
                int result_month_int = Integer.parseInt(result_month) + 1;
                result_month = Integer.toString(result_month_int);
                //Toast.makeText(getActivity(), result_month, Toast.LENGTH_LONG).show();

                String target_day = "DAY_OF_MONTH";
                int target_num_day = clickedstr.indexOf(target_day);
                String result_day = clickedstr.substring(target_num_day + clickedstr.substring(target_num_day).indexOf("=") + 1, (clickedstr.substring(target_num_day).indexOf(",") + target_num_day));

                result_date = result_year+"-"+result_month+"-"+result_day;
                //Toast.makeText(getActivity(), result_date, Toast.LENGTH_LONG).show();
                convert_date = new Date[4];
                //String -> Date

                ParsePosition pos = new ParsePosition(0);
                ParsePosition pos_wear = new ParsePosition(1);
                ParsePosition pos_open = new ParsePosition(2);
                ParsePosition pos_liquid_exp = new ParsePosition(3);


                SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                convert_date[0] = dateFormat1.parse(result_date, pos);
                convert_date[1] = dateFormat1.parse(result_date, pos_open);
                convert_date[2] = dateFormat1.parse(result_date, pos_liquid_exp);
                //Toast.makeText(getActivity(), String.valueOf(result_d), Toast.LENGTH_LONG).show();

                //Retrofit 가져오기
                recyclerView.setLayoutManager(layoutManager);
                setCalendar(convert_date, result_date);

            }
        });
        return view;
    }
    //서버에 있는 DB 가져오기
    private void setCalendar(Date result_d[], String result_s){
        //서버에서 가져오기
        int userId = 10;
        CalendarData calendarData = new CalendarData(userId);

        RetrofitClient.getService().addCalendar(calendarData).enqueue(new Callback<CalendarResponse>() {

            @Override
            public void onResponse(Call<CalendarResponse> call, Response<CalendarResponse> response) {
                CalendarResponse result = response.body();

                lens_exp_date = result.getLensExp();
                lens_open_date = result.getLensOpen();
                lens_wear_date = result.getLensWear();
                liquid_exp_date = result.getLiquidExp();
                liquid_open_date = result.getLiquidOpen();
                SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");

                lens_exp_d = new Date[lens_exp_date.length];
                lens_open_d = new Date[lens_open_date.length];
                liquid_exp_d = new Date[lens_exp_date.length];
                liquid_open_d = new Date[lens_open_date.length];

                ParsePosition pos = new ParsePosition(0);
                ParsePosition pos_open = new ParsePosition(1);
                ParsePosition pos_wear = new ParsePosition(2);
                ParsePosition pos_liquid_exp = new ParsePosition(3);

                for (int i = 0; i < lens_exp_date.length; i++) {
                    lens_exp_d[i] = dateFormat1.parse(lens_exp_date[i], pos);
                    lens_open_d[i] = dateFormat1.parse(lens_open_date[i], pos_wear);
                    liquid_exp_d[i] = dateFormat1.parse(lens_open_date[i], pos_liquid_exp);
                }

                //Toast.makeText(getActivity(), String.valueOf(lens_exp_d[0])+ String.valueOf(lens_exp_d[3]), Toast.LENGTH_LONG).show();

                //wearDate 계산
                Calendar calendar_wear = Calendar.getInstance();
                calendar_wear.setTime(lens_open_d[0]);
                calendar_wear.add(Calendar.DATE, Integer.parseInt(lens_wear_date[0]));
                String calendar_wear_str = calendar_wear.toString();

                String wear_year = "YEAR";
                int wear_num_year = calendar_wear_str.indexOf(wear_year);
                String wear_result_year = "20" + calendar_wear_str.substring(wear_num_year + calendar_wear_str.substring(wear_num_year).indexOf("=") + 1, (calendar_wear_str.substring(wear_num_year).indexOf(",") + wear_num_year));
                //Toast.makeText(getActivity(), result_year, Toast.LENGTH_LONG).show();

                String wear_month = "MONTH";
                int wear_num_month = calendar_wear_str.indexOf(wear_month);
                String wear_result_month = calendar_wear_str.substring(wear_num_month + calendar_wear_str.substring(wear_num_month).indexOf("=") + 1, (calendar_wear_str.substring(wear_num_month).indexOf(",") + wear_num_month));
                int wear_month_int = Integer.parseInt(wear_result_month) + 1;
                wear_result_month = Integer.toString(wear_month_int);
                //Toast.makeText(getActivity(), result_month, Toast.LENGTH_LONG).show();

                String wear_day = "DAY_OF_MONTH";
                int wear_num_day = calendar_wear_str.indexOf(wear_day);
                String wear_result_day = calendar_wear_str.substring(wear_num_day + calendar_wear_str.substring(wear_num_day).indexOf("=") + 1, (calendar_wear_str.substring(wear_num_day).indexOf(",") + wear_num_day));

                String result_wear = wear_result_year + "-" + wear_result_month + "-" + wear_result_day;
                //Toast.makeText(getActivity(), result_wear, Toast.LENGTH_LONG).show();
                lens_open_d[0] = dateFormat1.parse(result_wear, pos_open);

                String[] result_split = result_s.split("-");


                CalendarItems expItems = new CalendarItems("렌즈 유통기한 만료", result_split[1]+"월 "+result_split[2]+"일 ", "D-DAY");
                CalendarItems openItems = new CalendarItems("렌즈 사용기한 만료", result_split[1]+"월 "+result_split[2]+"일 ", "D-DAY");
                CalendarItems liquidexpItems = new CalendarItems("세척액 유통기한 만료", result_split[1]+"월 "+result_split[2]+"일 ", "D-DAY");
                if (lens_exp_d[0].equals(result_d[0]) == true) {
                    //Toast.makeText(getActivity(), String.valueOf(lens_exp_d[0]), Toast.LENGTH_LONG).show();
                    status = true;
                    adapter_calendar = new CalendarAdapter(getContext());
                    adapter_calendar.addItem(expItems);
                    recyclerView.setAdapter(adapter_calendar);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                } else {
                    if (status == true) {
                        adapter_calendar.deleteItem(0);
                        adapter_calendar.notifyItemRemoved(0);
                        adapter_calendar.notifyItemRangeChanged(0, adapter_calendar.getItemCount());
                        status = false;
                    }
                }
                if (lens_open_d[0].equals(result_d[1]) == true) {
                    //Toast.makeText(getActivity(), String.valueOf(lens_open_d[0]), Toast.LENGTH_LONG).show();
                    wear_status = true;
                    adapter_calendar = new CalendarAdapter(getContext());
                    adapter_calendar.addItem(openItems);
                    recyclerView.setAdapter(adapter_calendar);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                } else {
                    if (wear_status == true) {
                        adapter_calendar.deleteItem(0);
                        adapter_calendar.notifyItemRemoved(0);
                        adapter_calendar.notifyItemRangeChanged(0, adapter_calendar.getItemCount());
                        wear_status = false;
                    }
                }
                if (liquid_exp_d[0].equals(result_d[2])) {
                    Toast.makeText(getActivity(), String.valueOf(lens_open_d[0]), Toast.LENGTH_LONG).show();
                    liquid_status = true;
                    adapter_calendar = new CalendarAdapter(getContext());
                    adapter_calendar.addItem(liquidexpItems);
                    recyclerView.setAdapter(adapter_calendar);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                } else {
                    if (liquid_status == true) {
                        adapter_calendar.deleteItem(0);
                        adapter_calendar.notifyItemRemoved(0);
                        adapter_calendar.notifyItemRangeChanged(0, adapter_calendar.getItemCount());
                        liquid_status = false;
                    }
                }
            }

            @Override
            public void onFailure(Call<CalendarResponse> call, Throwable t) {

            }


        });
    }
}
