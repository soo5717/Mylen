package com.example.mylen.home.search;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mylen.R;

import java.util.ArrayList;

public class SearchLensActivity extends AppCompatActivity {

    Spinner spn_brand, spn_type;
    AdapterSpinner adapterSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_lens);

        spn_brand = findViewById(R.id.spn_brand);
        spn_type = findViewById(R.id.spn_type);

        //브랜드 스피너
        String[] brand = getResources().getStringArray(R.array.lens_brand);
        ArrayList<String> arr_brand = new ArrayList<>();
        for(int i=0; i<brand.length; i++) {
            arr_brand.add(brand[i]);
        }
        adapterSpinner = new AdapterSpinner(this, arr_brand);
        spn_brand.setAdapter(adapterSpinner);
        spn_brand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(),(String)spn_brand.getItemAtPosition(position)+"이 선택되었습니다.",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        //타입 스피너
        String[] type = getResources().getStringArray(R.array.lens_type);
        ArrayList<String> arr_type = new ArrayList<>();
        for(int i=0; i<type.length; i++) {
            arr_type.add(type[i]);
        }
        adapterSpinner = new AdapterSpinner(this, arr_type);
        spn_type.setAdapter(adapterSpinner);
        spn_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(),(String)spn_type.getItemAtPosition(position)+"이 선택되었습니다.",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

}
