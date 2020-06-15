package com.example.mylen.data.notice;

import com.google.gson.annotations.SerializedName;

public class AddNoticeResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
