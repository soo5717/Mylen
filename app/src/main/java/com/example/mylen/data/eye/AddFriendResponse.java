package com.example.mylen.data.eye;

import com.google.gson.annotations.SerializedName;

public class AddFriendResponse {
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
