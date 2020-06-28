package com.example.mylen.feature.eye.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mylen.R;
import com.example.mylen.feature.eye.exercise.EyeExercise2;
import com.example.mylen.feature.eye.exercise.EyeExercise3;

public class Fragment_exercise_vp_3 extends Fragment implements View.OnClickListener{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_exercise_3, container, false);

        rootView.setOnClickListener(this);

        return  rootView;
        //xml layout이 inflation되고, 이 java소스와 연결됨
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), EyeExercise3.class);
        startActivity(intent);
    }
}
