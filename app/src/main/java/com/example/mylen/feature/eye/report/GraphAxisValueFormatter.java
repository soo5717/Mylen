package com.example.mylen.feature.eye.report;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

public class GraphAxisValueFormatter extends ValueFormatter {
    private String[] mValues;
    // 생성자 초기화
    GraphAxisValueFormatter(String[] values){
        this.mValues = values;
    }

    @Override
    public String getFormattedValue(float value) {
        return mValues[(int) value];
    }
}