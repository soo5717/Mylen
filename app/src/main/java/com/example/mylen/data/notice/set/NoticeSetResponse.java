package com.example.mylen.data.notice.set;

import com.google.gson.annotations.SerializedName;

public class NoticeSetResponse {

    @SerializedName("status")
    private int status;

    @SerializedName("success")
    private Boolean success;

    @SerializedName("onOffArray")
    private int[] onOffArray;


    public int getStatus() {
        return status;
    }

    public Boolean getSuccess() {
        return success;
    }

    public int[] getOnOffArray() { return onOffArray; }
}
