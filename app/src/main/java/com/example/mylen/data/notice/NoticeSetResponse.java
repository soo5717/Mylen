package com.example.mylen.data.notice;

import com.google.gson.annotations.SerializedName;

public class NoticeSetResponse {
    @SerializedName("userId")
    private int userId;

    @SerializedName("onOffArray")
    private int[] onOffArray;


    public int getUserId() {
        return userId;
    }

    public int[] getOnOffArray() { return onOffArray; }
}
