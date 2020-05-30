package com.example.mylen.calendar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylen.R;

import java.util.ArrayList;

public class Calendar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ArrayList<String> list = new ArrayList<>();
        for (int i=0; i<100; i++) {
            list.add(String.format("TEXT %d", i)) ;
        }

        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView recyclerView = findViewById(R.id.recycler_calendar) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        CalendarItems adapter = new CalendarItems(list) ;
        recyclerView.setAdapter(adapter) ;

    }

    public static class CalendarDateItems extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.item_activity_calendar_date);
        }
    }
}
