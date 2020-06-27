package com.example.mylen.network;

import com.example.mylen.data.eye.AddFriendData;
import com.example.mylen.data.eye.AddFriendResponse;
import com.example.mylen.data.eye.FriendMainData;
import com.example.mylen.data.eye.FriendMainResponse;
import com.example.mylen.data.eye.SearchAddFriendData;
import com.example.mylen.data.eye.SearchAddFriendResponse;
import com.example.mylen.data.liquid.LiquidData;
import com.example.mylen.data.notice.NoticeData;
import com.example.mylen.data.notice.AddNoticeData;
import com.example.mylen.data.notice.AddNoticeResponse;
import com.example.mylen.data.notice.NoticeResponse;
import com.example.mylen.data.user.ProfileData;
import com.example.mylen.data.user.ProfileResponse;
import com.example.mylen.data.notice.NoticeSetData;
import com.example.mylen.data.notice.NoticeSetResponse;
import com.example.mylen.data.notice.UpdateNoticeSetData;
import com.example.mylen.data.notice.UpdateNoticeSetResponse;
import com.example.mylen.data.user.SignInData;
import com.example.mylen.data.user.SignInResponse;
import com.example.mylen.data.user.SignUpData;
import com.example.mylen.data.user.StatusResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ServiceApi {

    //토큰 검증 요청: 자동 로그인 구현 => 완료
    @POST("/auth")
    Call<StatusResponse> userAuth();

    //회원가입 요청 => 완료
    @POST("/auth/sign-up")
    Call<StatusResponse> userSignUp(@Body SignUpData data);
    //로그인 요청 => 완료
    @POST("/auth/sign-in")
    Call<SignInResponse> userSignIn(@Body SignInData data);

    //프로필 조회 요청 => 완료
    @GET("/users")
    Call<ProfileResponse> userProfile();
    //프로필 수정 요청 => 완료
    @PUT("/users")
    Call<StatusResponse> userProfileModify(@Body ProfileData data);

    //세척액 등록 요청 => 완료
    @POST("/liquids")
    Call<StatusResponse> addLiquid(@Body LiquidData data);

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
