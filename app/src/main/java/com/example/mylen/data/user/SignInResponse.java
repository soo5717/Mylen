package com.example.mylen.data.user;

import com.google.gson.annotations.SerializedName;

public class SignInResponse {
    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("success")
    private Boolean success;
    @SerializedName("token")
    private String token;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getToken() {
        return token;
    }
}