package com.example.mylen.feature.eye.report;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.mylen.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class EyeReportFragmentCount extends Fragment {

    private LineChart chart;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_eyereport_graph_count, container, false);

        chart = view.findViewById(R.id.linechart);

        //chart.setDrawGridBackground(true);
        //chart.setGridBackgroundColor(getResources().getColor(R.color.coral_red, null));
        chart.getXAxis().setDrawGridLines(false);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getXAxis().setTextColor(getResources().getColor(R.color.hint_grey, null));
        chart.getXAxis().setTextSize(15);
        chart.getXAxis().setDrawAxisLine(false);
        chart.getAxisLeft().setDrawLabels(false);
        chart.getAxisLeft().setTextColor(getResources().getColor(R.color.hint_grey, null));
        chart.getAxisLeft().setTextSize(15);
        chart.getAxisRight().setDrawLabels(false);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisRight().setDrawGridLines(false);
        chart.getAxisLeft().setEnabled(true);
        chart.getLegend().setEnabled(false);
        chart.getDescription().setEnabled(false);
        //chart.getDraw
        //chart.setNoDataTextColor();


        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            float val = (float) (Math.random() * 3);
            values.add(new Entry(i, val));
        }

        LineDataSet set;
        set = new LineDataSet(values, "eyeExercise_count");

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set);

        LineData data = new LineData(dataSets);

        XAxis xAxis = chart.getXAxis();
        //xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(true);
        xAxis.setGridColor(getResources().getColor(R.color.white, null));
        xAxis.setGridLineWidth(3);

        //YAxis yAxis = chart.getRendererLeftYAxis();

        chart.setData(data);
        chart.setBackgroundColor(getResources().getColor(R.color.white, null));
        //차트 선
        set.setDrawVerticalHighlightIndicator(false);
        set.setDrawHorizontalHighlightIndicator(false);
        set.setDrawValues(false);
        set.setHighlightEnabled(true);
        set.setHighLightColor(getResources().getColor(R.color.clear_blue, null));
        set.setLineWidth(2);
        set.setColor(getResources().getColor(R.color.clear_blue, null));
        set.setCircleColor(getResources().getColor(R.color.clear_blue, null));
        set.setCircleRadius(6);
        set.setCircleHoleRadius(4);
        set.setDrawFilled(true);
        //set.setValueTextColor(getColor(R.color.deep_grey));
        set.setFillDrawable(ContextCompat.getDrawable(getContext(), R.drawable.graph_gradient));
        //set.setValueTextSize(6);

        return view;

    }
}
