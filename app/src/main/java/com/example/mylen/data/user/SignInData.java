package com.example.mylen.data.user;

import com.google.gson.annotations.SerializedName;

public class SignInData {
    @SerializedName("email")
    String email;
    @SerializedName("pwd")
    String pwd;

    public SignInData(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }
}