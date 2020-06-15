package com.example.mylen.feature.exercise.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mylen.R;

public class EyeMainFriendFragment2 extends Fragment {
    @Nullable
    @Override //리사이클러뷰 fragment
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_eyeaddfriend_2, container, false);
    }
}
