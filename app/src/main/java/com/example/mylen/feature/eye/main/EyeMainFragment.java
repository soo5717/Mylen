package com.example.mylen.feature.eye.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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
import com.example.mylen.data.eye.FriendMainResponse1;
import com.example.mylen.feature.eye.add.EyeAddFriend;
import com.example.mylen.network.RetrofitClient;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    //db에서 가져오기
    String[] nameArray;
    int[] friendIdArray;
    int[] pointArray;
    int[] sortedPointArray;
    int[] sortFriendIdArray;
    //String[] pictureArray;

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//    }


    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        //뷰페이저

        view_exercise = inflater.inflate(R.layout.fragment_eyemain, container, false);
        //layoutManager = new LinearLayoutManager(getActivity());


        pager = view_exercise.findViewById(R.id.vp_exercise);

        //page갯수 limit정하기
        pager.setOffscreenPageLimit(3);

        adapter_exercise = new ExercisePagerAdapter(getChildFragmentManager(), 3);

        Fragment fragment1 = new Fragment_exercise_vp_1();
        adapter_exercise.addItem(fragment1);

        Fragment fragment2 = new Fragment_exercise_vp_2();
        adapter_exercise.addItem(fragment2);

        Fragment fragment3 = new Fragment_exercise_vp_3();
        adapter_exercise.addItem(fragment3);


        pager.setAdapter(adapter_exercise);
        pager.setActivated(true);


        // 리사이클러뷰에 LinearLayoutManager 객체 지정
        recyclerView = (RecyclerView) view_exercise.findViewById(R.id.rc_friendRank);

        LinearLayoutManager layoutManager = new LinearLayoutManager(inflater.getContext());
        //recyclerView.setHasFixedSize(true);
        //layoutManager_friend = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.scrollToPosition(0);
        friendlist = new ArrayList<EyeMainFriendItem>();
        adapter_friend = new EyeFriendAdapter(getContext());

//        //친구정보 가져오기
//        RetrofitClient.getService().rankFriendMain1().enqueue(new Callback<FriendMainResponse1>() {
//
//            @Override
//            public void onResponse(Call<FriendMainResponse1> call, Response<FriendMainResponse1> response) {
//
//                FriendMainResponse1 result = response.body();
//                assert result != null;
//
//                //이름, 이메일, 포인트, 사진 가져옴
//                friendIdArray = result.getFriendIdArray();
//                pointArray = result.getPointArray();
//                //pictureArray = result.getpictureArray();
//                sortedPointArray = pointArray;
//
//                for (int i = 0; i < sortedPointArray.length; i++) {
//                    for (int j = i + 1; j < sortedPointArray.length; j++) {
//                        if (sortedPointArray[i] < sortedPointArray[j]) {
//                            int temp = sortedPointArray[i];
//                            sortedPointArray[i] = sortedPointArray[j];
//                            sortedPointArray[j] = temp;
//                        }
//                    }
//                }
//                Log.d("포인트ㅡㅡㅡㅡㅡ", String.valueOf(pointArray));
//
//                ArrayList  pointList =  new ArrayList(Arrays.asList(pointArray));
//
//                Log.d("포인트ㅡ리스트ㅡㅡㅡㅡ", String.valueOf(sortedPointArray));
//
//                sortFriendIdArray = new int[sortedPointArray.length];
//                for (int i=0; i<pointList.size(); i++){
//                    int index = pointList.indexOf(sortedPointArray[i]);
//                    sortFriendIdArray[i] = friendIdArray[index];
//                }
//
//                Log.d("정렬된친구id배열=====", String.valueOf(sortFriendIdArray));
//
//            }
//
//            @Override
//            public void onFailure(Call<FriendMainResponse1> call, Throwable t) {
//                Toast.makeText(getContext(), "가져오기 에러 발생", Toast.LENGTH_SHORT).show();
//                Log.e("가져오기 에러 발생", t.getMessage());
//            }
//        });
//
//        for(int i=0; i<sortFriendIdArray.length; i++) {
//
//
//            RetrofitClient.getService().rankFriendMain2().enqueue(new Callback<FriendMainResponse2>() {
//
//                @Override
//                public void onResponse(Call<FriendMainResponse2> call, Response<FriendMainResponse2> response) {
//
//                    FriendMainResponse2 result = response.body();
//                    assert result != null;
//
//                    //이름, 이메일, 포인트, 사진 가져옴
//                    friendIdArray = result.getFriendIdArray();
//                    pointArray = result.getPointArray();
//                    //pictureArray = result.getpictureArray();
//                    sortedPointArray = pointArray;
//
//
//                    if (result.getSuccess()) {
//                        //친구 수만큼 리사이클러뷰에 넣기
//                        for (int i = 0; i < nameArray.length; i++) {
//                            adapter_friend.addItem(new EyeMainFriendItem(i + " 위", nameArray[i], emailArray[i], pointArray[i] + " P"));
//                        }
//
//                        adapter_friend.notifyDataSetChanged();
//
//                        recyclerView.setAdapter(adapter_friend);
//                        recyclerView.setItemAnimator(new DefaultItemAnimator());
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<FriendMainResponse2> call, Throwable t) {
//                    Toast.makeText(getContext(), "가져오기 에러 발생", Toast.LENGTH_SHORT).show();
//                    Log.e("가져오기 에러 발생", t.getMessage());
//                }
//            });
//        }

        adapter_friend.addItem(new EyeMainFriendItem("1", "백서윤", "bsj@naver.com", "120p"));
        adapter_friend.addItem(new EyeMainFriendItem("2", "조수연", "ooo@naver.com", "100p"));
        adapter_friend.addItem(new EyeMainFriendItem("3", "박윤경", "ppp@naver.com", "80p"));
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
