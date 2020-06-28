package com.example.mylen.feature.eye.add;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mylen.R;
import com.example.mylen.data.eye.AddFriendData;
import com.example.mylen.data.eye.FriendCheckData;
import com.example.mylen.data.eye.FriendCheckResponse;
import com.example.mylen.data.user.StatusResponse;
import com.example.mylen.network.RetrofitClient;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EyeAddFriendFragment2 extends Fragment implements View.OnClickListener {
    //가져와야하는 건지 확실히 모르겠음
   // private static final String param_user_id = "param1";

    private static final String param_name = "param1";
    private static final String param_email = "param2";
    private static final String param_id = "param3";
   // private static final Image param_picture = ;

    private String mParam1;
    private String mParam2;
    private int mParam3;
    //private Image  mParam4;

    int friendId;


    public EyeAddFriendFragment2() {
    }

    //
    public static EyeAddFriendFragment2 newInstance(String param1, String param2, int param3) {
        EyeAddFriendFragment2 fragment = new EyeAddFriendFragment2();
        Bundle args = new Bundle();
        args.putString(param_name, param1);
        args.putString(param_email, param2);
        args.putInt(param_id, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(param_name);
            mParam2 = getArguments().getString(param_email);
            mParam3 = getArguments().getInt(param_id);
        }
    }

    //
    ImageView iv_picture;
    TextView tv_user_name;
    TextView tv_user_email;
    Button bt_addfriend;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_eyeadd_friend_2, container, false);

        //iv_picture = view.findViewById(R.id.iv_picture);
        tv_user_name = view.findViewById(R.id.tv_user_name);
        tv_user_email = view.findViewById(R.id.tv_user_email);
        bt_addfriend = view.findViewById(R.id.bt_addfriend);

        //iv_picture = userPicture;
        tv_user_name.setText(mParam1);
        tv_user_email.setText(mParam2);
        friendId = mParam3;

        FriendCheckData data = new FriendCheckData(friendId);

        RetrofitClient.getService().checkFriend(data).enqueue(new Callback<FriendCheckResponse>() {
            @Override
            public void onResponse(Call<FriendCheckResponse> call, Response<FriendCheckResponse> response) {
                FriendCheckResponse result = response.body();
                assert result != null;

                Boolean check = result.getCheck();
                if(check){
                    bt_addfriend.setText(R.string.eye_added_friend);
                    bt_addfriend.setTextColor(getResources().getColor(R.color.soft_grey, null));
                    bt_addfriend.setClickable(false);
                }

            }

            @Override
            public void onFailure(Call<FriendCheckResponse> call, Throwable t) {
                Toast.makeText(getContext(), "add friend 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("add friend 에러 발생", Objects.requireNonNull(t.getMessage()));

            }
        });

        bt_addfriend.setOnClickListener(this);

        return view;
    }

    //추가하기버튼 - > db에 추가하기
    @Override
    public void onClick(View view) {

        AddFriendData data = new AddFriendData(friendId);

        RetrofitClient.getService().addFriend(data).enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                StatusResponse result = response.body();
                assert result != null;
                Toast.makeText(getContext(), result.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {
                Toast.makeText(getContext(), "add friend 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("add friend 에러 발생", Objects.requireNonNull(t.getMessage()));

            }
        });

        bt_addfriend.setText(R.string.eye_added_friend);
        bt_addfriend.setTextColor(getResources().getColor(R.color.soft_grey, null));
        bt_addfriend.setClickable(false);
    }
}
