package com.example.mylen.feature.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mylen.R;
import com.example.mylen.feature.home.search.SearchLensActivity;
import com.example.mylen.feature.sign.SignInActivity;
import com.example.mylen.feature.sign.SignUp1Activity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainFragment extends Fragment implements View.OnClickListener{

    //전역변수 선언
    MainChild1Fragment main_child1;
    MainChild2Fragment main_child2;
    Button btn_open, btn_keep, btn_add;

    //프래그먼트가 액티비티에 연결되었을 때 호출
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    //프래그먼트 뷰 계층을 리턴
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        //프래그먼트 구현 : 렌즈 등록 전 초기 화면
        main_child1 = new MainChild1Fragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.container, main_child1).commit();

        //다이얼로그 구현 : 렌즈 등록 / 세척액 등록
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setItems(R.array.lens_add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent;
                switch (which){
                    case 0: //렌즈 검색 페이지로 이동
                        intent = new Intent(getActivity(), SearchLensActivity.class);
                        startActivity(intent);
                        break;
                    case 1: //세척액 등록1 페이지로 이동
                        intent = new Intent(getActivity(), SignUp1Activity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
        //추가 버튼 이벤트 : 선택 다이얼로그
        btn_add = rootView.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        //버튼 리스너 등록
        btn_open = rootView.findViewById(R.id.btn_open);
        btn_keep = rootView.findViewById(R.id.btn_keep);
        btn_keep.setOnClickListener(this);
        btn_open.setOnClickListener(this);

        return rootView;
    }

    //버튼 이벤트
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_open: //개봉함 버튼 클릭 이벤트
                btn_open.setTextColor(getResources().getColor(R.color.soft_black));
                btn_keep.setTextColor(getResources().getColor(R.color.hint_grey));
                break;
            case R.id.btn_keep: //보관함 버튼 클릭 이벤트
                btn_open.setTextColor(getResources().getColor(R.color.hint_grey));
                btn_keep.setTextColor(getResources().getColor(R.color.soft_black));
                break;
            default:
                break;
        }
    }
}
