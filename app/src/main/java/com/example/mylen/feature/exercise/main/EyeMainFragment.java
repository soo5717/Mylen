package com.example.mylen.feature.exercise.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.mylen.R;
import com.example.mylen.data.exercise.FriendMainData;
import com.example.mylen.feature.exercise.add.EyeAddFriend;

import java.util.ArrayList;

public class EyeMainFragment extends Fragment implements View.OnClickListener{

    //리사이클러
    private RecyclerView recyclerView;
    private EyeFriendAdapter adapter_friend;
    //private RecyclerView.LayoutManager layoutManager_friend;
    private ArrayList<EyeMainFriendItem> friendlist;

    //뷰페이저
    private ViewPager pager;
    private View view_exercise;
    ExercisePagerAdapter adapter_exercise;

    private Button bt_add;
    private View empty_view;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }


    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        //뷰페이저

        view_exercise = inflater.inflate(R.layout.fragment_eyemain, container, false);
        //layoutManager = new LinearLayoutManager(getActivity());


        pager = view_exercise.findViewById(R.id.vp_exercise);

        //page갯수 limit정하기
        pager.setOffscreenPageLimit(3);

        adapter_exercise = new ExercisePagerAdapter(getFragmentManager(), 3);

        Fragment fragment1 = new Fragment_exercise_vp_1();
        adapter_exercise.addItem(fragment1);

        Fragment fragment2 = new Fragment_exercise_vp_2();
        adapter_exercise.addItem(fragment2);

        Fragment fragment3 = new Fragment_exercise_vp_3();
        adapter_exercise.addItem(fragment3);


        pager.setAdapter(adapter_exercise);


        // 리사이클러뷰에 LinearLayoutManager 객체 지정
        recyclerView = (RecyclerView) view_exercise.findViewById(R.id.rc_friendRank);

        LinearLayoutManager layoutManager = new LinearLayoutManager(inflater.getContext());
        //recyclerView.setHasFixedSize(true);
        //layoutManager_friend = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.scrollToPosition(0);
        friendlist = new ArrayList<EyeMainFriendItem>();
        adapter_friend = new EyeFriendAdapter(getContext());

        //db에서 가져오기
        int userId = 2;
        FriendMainData data = new FriendMainData(userId);
        final String[] nameArray;
        final String[] emailArray;
        final String[] pointArray;
        //final String[] pictureArray;

//        //친구정보 가져오기
//        RetrofitClient.service.RankFriendMain(data).enqueue(new Callback<FriendMainResponse>() {
//
//            @Override
//            public void onResponse(Call<FriendMainResponse> call, Response<FriendMainResponse> response) {
//                FriendMainResponse result = response.body();
//
//                //이름, 이메일, 포인트, 사진 가져옴
//                String[] nameArray = result.getnameArray();
//                String[] emailArray = result.getemailArray();
//                String[] pointArray = result.getpointArray();
//                //String[] pictureArray = result.getpictureArray();
//
//                //알림 갯수만큼 날짜 및 시간설정, 알림설정
//                for (int i = 0; i < nameArray.length; i++) {
//                    adapter_friend.addItem(new EyeMainFriendItem(String.valueOf(i)+" 위", nameArray[i], emailArray[i], pointArray[i]+" P"));
//                }
//            }
//
//
//            @Override
//            public void onFailure(Call<FriendMainResponse> call, Throwable t) {
//                Toast.makeText(getContext(), "가져오기 에러 발생", Toast.LENGTH_SHORT).show();
//                Log.e("가져오기 에러 발생", t.getMessage());
//            }
//        });

        adapter_friend.addItem(new EyeMainFriendItem("1", "서윤", "12@naver.com", "120p"));
        adapter_friend.addItem(new EyeMainFriendItem("2", "수연", "34@naver.com", "100p"));
        adapter_friend.addItem(new EyeMainFriendItem("3", "윤경", "56@naver.com", "80p"));

        adapter_friend.notifyDataSetChanged();

        recyclerView.setAdapter(adapter_friend);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        // empty_view = view_exercise.findViewById(R.id.empty_view);


        bt_add = view_exercise.findViewById(R.id.bt_add);
        bt_add.setOnClickListener(this);


        return view_exercise;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), EyeAddFriend.class);
        //스택에 있는 것을 위로 올리기
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

    }
/*
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), EyeAddFriend.class);
        //스택에 있는 것을 위로 올리기
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
*/



    class ExercisePagerAdapter extends FragmentStatePagerAdapter{

        ArrayList<Fragment> exerciseItems = new ArrayList<Fragment>();

        public ExercisePagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addItem(Fragment item){
            exerciseItems.add(item);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return exerciseItems.get(position);
        }

        @Override
        public int getCount() {
            return exerciseItems.size();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }






}
