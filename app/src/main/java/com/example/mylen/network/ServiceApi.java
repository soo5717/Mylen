package com.example.mylen.network;

import com.example.mylen.data.exercise.FriendMainData;
import com.example.mylen.data.exercise.FriendMainResponse;
import com.example.mylen.data.exercise.SearchFriendData;
import com.example.mylen.data.exercise.SearchFriendResponse;
import com.example.mylen.data.notice.NoticeData;
import com.example.mylen.data.notice.AddNoticeData;
import com.example.mylen.data.notice.AddNoticeResponse;
import com.example.mylen.data.notice.NoticeResponse;
import com.example.mylen.data.user.SignInData;
import com.example.mylen.data.user.SignInResponse;
import com.example.mylen.data.user.SignUpData;
import com.example.mylen.data.user.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {

    //sign-up
    @POST("/users/sign-up")
    Call<SignUpResponse> userSignUp(@Body SignUpData data);
    //sign-in
    @POST("/users/sign-in")
    Call<SignInResponse> userSignIn(@Body SignInData data);

    //get noticedata
    @POST("/user/notice")
    Call<NoticeResponse> userNotice(@Body NoticeData data);
    //insert noticedata to notices table
    @POST("/user/notice/add")
    Call<AddNoticeResponse> addLensNotice(@Body AddNoticeData data);

    //exercise_friend_rank_data
    @POST("/user/eye/friend/main")
    Call<FriendMainResponse> RankFriendMain(@Body FriendMainData data);

    //exercise_friend_search and add
    @POST("/user/eye/friend/add")
    Call<SearchFriendResponse> SearchFrienAdd(@Body SearchFriendData data);

}
