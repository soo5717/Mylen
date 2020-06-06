package com.example.mylen.network;

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
}
