package com.example.mylen.feature.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mylen.R;

public class MainFragment extends Fragment {
    TextView tv_home;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

//        TextPaint paint = tv_home.getPaint();
//        float width = paint.measureText(tv_home.getText().toString());
//
//        Shader textShader = new LinearGradient(0, 0, width,tv_home.getTextSize(),
//                new int[]{
//                        Color.parseColor("#138bff"),
//                        Color.parseColor("#5edae3")
//                }, null, Shader.TileMode.CLAMP);
//        tv_home.getPaint().setShader(textShader);

}
