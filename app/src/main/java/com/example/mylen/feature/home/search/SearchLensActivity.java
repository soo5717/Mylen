package com.example.mylen.feature.home.search;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mylen.R;

import java.util.ArrayList;

public class SearchLensActivity extends AppCompatActivity {
    Menu menu_change;
    MenuInflater menuInflater;
    Spinner spn_brand, spn_type;
    AdapterSpinner adapterSpinner;
    private Toolbar myToolbar;
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

        setContentView(R.layout.activity_action_bar);
        //툴바 구현
        myToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(""); //ㅣ기존 타이틀 지우기
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        actionBar.setHomeAsUpIndicator(R.drawable.ic_backspace_48dp);


    }

    //앱바에 있는 돋보기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu_change = menu;
        menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_app_bar_search, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: { // 뒤로가기 버튼 눌렀을 때
                finish();
                return true;
            }

            //검색기능 구현
            case R.id.action_search:{
                //Intent intent = new Intent(getApplicationContext(), Profile.class);
                //startActivity(intent);
                //overridePendingTransition(0, 0);
                Toast.makeText(this, "검색기능을 구현하세요", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
