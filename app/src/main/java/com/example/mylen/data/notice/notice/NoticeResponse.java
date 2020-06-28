package com.example.mylen.data.notice.notice;

import com.google.gson.annotations.SerializedName;

public class NoticeResponse {

    @SerializedName("status")
    private int status;

    @SerializedName("success")
    private Boolean success;

    @SerializedName("msgArray")
    private String[] msgArray;

    @SerializedName("date")
    private String date;

    @SerializedName("time")
    private String time;


    public int getStatus() {
        return status;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String[] getMsgArray() { return msgArray; }

    public String getDate() { return date; }

    public String getTime() { return time; }

}
