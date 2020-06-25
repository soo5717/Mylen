package com.example.mylen.network;

import com.example.mylen.data.eye.AddFriendData;
import com.example.mylen.data.eye.AddFriendResponse;
import com.example.mylen.data.eye.FriendMainData;
import com.example.mylen.data.eye.FriendMainResponse;
import com.example.mylen.data.eye.SearchAddFriendData;
import com.example.mylen.data.eye.SearchAddFriendResponse;
import com.example.mylen.data.notice.NoticeData;
import com.example.mylen.data.notice.AddNoticeData;
import com.example.mylen.data.notice.AddNoticeResponse;
import com.example.mylen.data.notice.NoticeResponse;
import com.example.mylen.data.notice.NoticeSetData;
import com.example.mylen.data.notice.NoticeSetResponse;
import com.example.mylen.data.notice.UpdateNoticeSetData;
import com.example.mylen.data.notice.UpdateNoticeSetResponse;
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

    //insert notice data to notices table
    @POST("/user/notice/add")
    Call<AddNoticeResponse> addLensNotice(@Body AddNoticeData data);

    //eye_friend_main_data
    @POST("/user/eye/friend/main")
    Call<FriendMainResponse> rankFriendMain(@Body FriendMainData data);

    //eye_friend_search add
    //변경했음
    @POST("/user/eye/friend/add/search")
    Call<SearchAddFriendResponse> searchAddFriend(@Body SearchAddFriendData data);

    //eye_friend_add
    @POST("/user/eye/friend/add/add")
    Call<AddFriendResponse> addFriend(@Body AddFriendData data);

    //noticeSet setting
    @POST("/user/notice/set")
    Call<NoticeSetResponse> userNoticeSet(@Body NoticeSetData data);

    //update changes to noticeSet
    @POST("/user/notice/set")
    Call<UpdateNoticeSetResponse> updateNoticeSet(@Body UpdateNoticeSetData data);

}
