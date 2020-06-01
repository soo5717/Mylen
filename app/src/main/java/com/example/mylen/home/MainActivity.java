package com.example.mylen.home;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.widget.TextView;

import com.example.mylen.R;

public class MainActivity extends AppCompatActivity {
    TextView tv_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_home = (TextView)findViewById(R.id.tv_home);

        TextPaint paint = tv_home.getPaint();
        float width = paint.measureText(tv_home.getText().toString());

        Shader textShader = new LinearGradient(0, 0, width,tv_home.getTextSize(),
                new int[]{
                        Color.parseColor("#138bff"),
                        Color.parseColor("#5edae3")
                }, null, Shader.TileMode.CLAMP);
        tv_home.getPaint().setShader(textShader);
    }
}
