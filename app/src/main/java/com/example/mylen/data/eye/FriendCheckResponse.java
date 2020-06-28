package com.example.mylen.data.eye;

import com.google.gson.annotations.SerializedName;

public class FriendCheckResponse {

    @SerializedName("status")
    private int status;
    @SerializedName("check")
    private Boolean check;
    @SerializedName("success")
    private Boolean success;

    public int getStatus() {
        return status;
    }

    public Boolean getCheck() {
        return check;
    }

    public Boolean getSuccess() {
        return success;
    }
}
