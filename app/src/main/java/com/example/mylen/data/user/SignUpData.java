package com.example.mylen.data.user;

import com.google.gson.annotations.SerializedName;

public class SignUpData {
    @SerializedName("userEmail")
    private String userEmail;
    @SerializedName("userPwd")
    private String userPwd;
    @SerializedName("userName")
    private String userName;
    @SerializedName("userBirth")
    private String userBirth;

    public SignUpData(String userEmail, String userPwd, String userName, String userBirth) {
        this.userEmail = userEmail;
        this.userPwd = userPwd;
        this.userName = userName;
        this.userBirth = userBirth;
    }
}
