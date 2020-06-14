package com.example.mylen.feature.home.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mylen.R;
import com.example.mylen.feature.home.add.AddLens1Activity;

public class SearchLensChild3Fragment extends Fragment {

    @Nullable
    @Override //렌즈 직접 추가 프래그먼트
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_lens_child3, container, false);

        //직접 추가 버튼 이벤트 : 렌즈 추가 1 페이지로 이동
        Button btn_add = rootView.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(getActivity(), AddLens1Activity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
