package com.example.mylen.network;

import com.example.mylen.data.eye.AddFriendData;
import com.example.mylen.data.eye.FriendCheckData;
import com.example.mylen.data.eye.FriendCheckResponse;
import com.example.mylen.data.eye.FriendMainResponse;
import com.example.mylen.data.eye.ReportGraphResponse;
import com.example.mylen.data.eye.ReportResponse;
import com.example.mylen.data.eye.SearchAddFriendData;
import com.example.mylen.data.eye.SearchAddFriendResponse;
import com.example.mylen.data.notice.notice.AddNoticeData;
import com.example.mylen.data.notice.notice.AddNoticeResponse;
import com.example.mylen.data.notice.notice.NoticeResponse;
import com.example.mylen.data.lens.LensData;
import com.example.mylen.data.lens.LensKeepResponse;
import com.example.mylen.data.lens.SearchResponse;
import com.example.mylen.data.liquid.LiquidData;
import com.example.mylen.data.liquid.LiquidResponse;
import com.example.mylen.data.user.ProfileData;
import com.example.mylen.data.user.ProfileResponse;
import com.example.mylen.data.notice.set.NoticeSetResponse;
import com.example.mylen.data.notice.set.UpdateNoticeSetData;
import com.example.mylen.data.user.SignInData;
import com.example.mylen.data.user.SignInResponse;
import com.example.mylen.data.user.SignUpData;
import com.example.mylen.data.user.StatusResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

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
    //렌즈 등록 요청 => 완료
    @POST("/lenses")
    Call<StatusResponse> addLens(@Body LensData data);
    //세척액 삭제 요청
    @DELETE("/liquids")
    Call<StatusResponse> deleteLiquid(@Query("id") int id);
    //세척액 수정 요청
    //세척액 사용 시작 요청
    @PATCH("/liquids/open")
    Call<StatusResponse> openLiquid(@Query("id") int id);
    //세척액 사용 종료 요청

    //렌즈 검색 요청 => 완료
    @GET("/search")
    Call<SearchResponse> searchLens(@Query("name") String name);

    //보관함 세척액 조회 요청
    @GET("/liquids/keep")
    Call<LiquidResponse> liquidKeep();

    //보관함 렌즈 조회 요청
    @GET("/lenses/keep")
    Call<LensKeepResponse> lensKeep();




    //get noticedata
    @GET("/notice/main")
    Call<NoticeResponse> userNotice();

    //insert notice data to notices table
    @POST("/notice/add")
    Call<AddNoticeResponse> addLensNotice(@Body AddNoticeData data);


    //noticeSet setting => 완료
    @GET("/notice/set")
    Call<NoticeSetResponse> userNoticeSet();

    //update changes to noticeSet => 완료
    @PUT("/notice/set/update")
    Call<StatusResponse> updateNoticeSet(@Body UpdateNoticeSetData data);

    //eye_friend_main_data
    @GET("/eye/friend/main")
    Call<FriendMainResponse> rankFriendMain();

    //eye_report => 완료
    @GET("/eye/report")
    Call<ReportResponse> eyeReport();

    //eye_report_graph
    @GET("/eye/report/graph")
    Call<ReportGraphResponse> eyeReportGraph();

    //친구추가검색 => 완료
    @POST("/eye/friend/add/search")
    Call<SearchAddFriendResponse> searchAddFriend(@Body SearchAddFriendData data);

    //친구추가여부 확인 => 완료
    @POST("/eye/friend/add/check")
    Call<FriendCheckResponse> checkFriend(@Body FriendCheckData data);

    //친구추가 => 완료
    @PUT("/eye/friend/add")
    Call<StatusResponse> addFriend(@Body AddFriendData data);


}
