package com.example.mylen.feature.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.mylen.R;

public class MainFragment extends Fragment {

    //전역변수 선언
    MainInitialFragment main_initial_fragment;
    MainWearFragment main_wear_fragment2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);

        

    }
}
