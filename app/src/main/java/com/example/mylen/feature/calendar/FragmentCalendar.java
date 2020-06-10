package com.example.mylen.feature.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylen.R;

import java.util.ArrayList;

public class FragmentCalendar extends Fragment {
    private RecyclerView recyclerView;
    private CalendarItems adapter;
    View view;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calendar, container, false);
        layoutManager = new LinearLayoutManager(getActivity());

        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        recyclerView = view.findViewById(R.id.recycler_calendar);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.scrollToPosition(0);

        ArrayList<String> list = new ArrayList<>();
        for (int i=0; i<100; i++) {
            list.add(String.format("TEXT %d", i)) ;
        }

        adapter = new CalendarItems(list) ;
        recyclerView.setAdapter(adapter);

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        return view;
    }

    public static class CalendarDateItems extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.item_activity_calendar_date);
        }
    }

}
